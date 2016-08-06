package link.gerry.common.send.service.impl;

import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;

import link.gerry.common.send.entity.CommonSendMessage;
import link.gerry.common.send.helper.CommonSendMesageHelper;
import link.gerry.common.send.initialization.InitializationSendProperties;
import link.gerry.common.send.service.CommonSendMessageService;
import link.gerry.common.send.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.gerry.common.framework.constants.send.SendConstants;
import com.gerry.common.framework.helper.ViewModelHelper;
import com.gerry.common.framework.redis.RedisManager;
import com.gerry.common.framework.result.ViewModelResult;
import com.gerry.common.framework.utils.EmptyUtils;

@Slf4j
public abstract class AbstractSendMessageService<T> implements SendMessageService<T> {

	public static final ConcurrentHashMap<String, Integer> types = new ConcurrentHashMap<>();

	static {
		types.put("register", 0);
	}

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RedisManager<String, String> redisManager;

	@Autowired
	private CommonSendMessageService commonSendMessageService;

	@Override
	public void sendSms(String phone, String key) throws Exception {
		SendConstants sc = CommonSendMesageHelper.generationSmsCode(phone, key, redisManager);
		String templetMessage = InitializationSendProperties.getValue(sc.getSendMessageKey());
		if (EmptyUtils.isEmpty(templetMessage)) {
			log.warn("当前短信模板[" + sc.getSendMessageKey() + "]不支持");
			return;
		}

		String content = "";
		if (EmptyUtils.isNotEmpty(sc.getParams())) {
			content = MessageFormat.format(templetMessage, sc.getParams());
		} else {
			content = templetMessage;
		}
		String url = buildUrl(phone, content);
		log.info("准备向地址：" + url + "发送请求");
		String response = restTemplate.postForObject(url, null, String.class);
		log.info("接收到地址:" + url + "响应\n" + response);
		T resultMessage = convertResponse(response);
		if (EmptyUtils.isEmpty(resultMessage)) {
			log.error("向手机号[" + sc.getPhone() + "]发送短信失败");
			return;
		}
		CommonSendMessage csm = convertToCommonSendMessage(resultMessage);
		csm.setContent(content);
		csm.setPhone(phone);
		csm.setType(types.get(sc.getSendMessageKey()).byteValue());
		saveMessageContent(csm);
	}

	@Override
	public ViewModelResult<?> checkSmsCode(String phone, String smsCode, String key) {
		String code = redisManager.getObjectByKey(phone + key);
		if (EmptyUtils.isEmpty(code)) {
			String message = "手机号[" + phone + "]验证的验证码[" + smsCode + "]不存在或已过去";
			log.error(message);
			return ViewModelHelper.NOViewModelResult(message);
		}
		if (!code.equals(smsCode)) {
			String message = "手机号[" + phone + "]验证的验证码[" + smsCode + "]错误";
			log.error(message);
			return ViewModelHelper.NOViewModelResult(message);
		}
		redisManager.deleteObjectByKey(phone + key);
		return ViewModelHelper.OKViewModelResult();
	}

	protected void saveMessageContent(CommonSendMessage commonSendMessage) {
		commonSendMessageService.save(commonSendMessage);
	}

	public abstract String buildUrl(String phone, String content) throws Exception;

	public abstract T convertResponse(String response) throws Exception;

	public abstract CommonSendMessage convertToCommonSendMessage(T t) throws Exception;

}
