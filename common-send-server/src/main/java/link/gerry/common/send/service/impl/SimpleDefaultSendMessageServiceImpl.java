package link.gerry.common.send.service.impl;

import java.io.UnsupportedEncodingException;

import link.gerry.common.send.entity.CommonSendMessage;
import link.gerry.common.send.result.SmsResult;
import link.gerry.common.send.result.SmsResultForJYT;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.gerry.common.framework.constants.CommonConstants;
import com.gerry.common.framework.redis.RedisManager;
import com.gerry.common.framework.utils.EmptyUtils;
import com.gerry.common.framework.utils.xml.JaxbXmlUtils;

/**
 * 一个简单默认
 * 
 *
 * @author gerry
 * @version 1.0, 2016年8月4日
 * @since com.nise 1.0.0
 */
@Slf4j
public class SimpleDefaultSendMessageServiceImpl extends AbstractSendMessageService<SmsResultForJYT> {

	@Value("${sms.juyitong.userId}")
	private String userId;

	@Value("${sms.juyitong.account}")
	private String account;

	@Value("${sms.juyitong.password}")
	private String password;

	@Value("${sms.juyitong.url}")
	private String url;

	@Autowired
	private RedisManager<String, String> redisManager;

	@Override
	public String buildUrl(String phone, String content) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder(url).append(CommonConstants.SYMBOL_AMPERSAND).append("userid=" + userId);
		sb.append(CommonConstants.SYMBOL_AMPERSAND).append("account=" + account).append(CommonConstants.SYMBOL_AMPERSAND).append("password=" + password).append(CommonConstants.SYMBOL_AMPERSAND)
				.append("mobile=" + phone).append(CommonConstants.SYMBOL_AMPERSAND).append("content=" + content).append(CommonConstants.SYMBOL_AMPERSAND).append("sendTime=")
				.append(CommonConstants.SYMBOL_AMPERSAND).append("extno=");
		return sb.toString();
	}

	@Override
	public SmsResultForJYT convertResponse(String response) {
		return JaxbXmlUtils.convert2Bean(response, SmsResultForJYT.class);
	}

	@Override
	public CommonSendMessage convertToCommonSendMessage(SmsResultForJYT t) {
		CommonSendMessage commonSendMessage = new CommonSendMessage();
		commonSendMessage.setRemark(t.getMessage());
		commonSendMessage.setSupplier("聚义通");
		if (SmsResult.STATUS_SUCC.equals(t.getReturnstatus())) {
			commonSendMessage.setStatus((byte) SmsResult.Status.SUCC.getCode());
		} else {
			commonSendMessage.setStatus((byte) SmsResult.Status.FAIL.getCode());
		}
		return commonSendMessage;
	}

	@Override
	public boolean canSend(String phone, String key) {
		String code = redisManager.getObjectByKey(phone + key);
		if (EmptyUtils.isNotEmpty(code)) {
			log.error("手机号[" + phone + "]验证的模板[" + key + "]的验证码已存在");
			return false;
		}
		return true;
	}

}
