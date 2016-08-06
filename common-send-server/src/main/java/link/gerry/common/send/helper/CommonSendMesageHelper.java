package link.gerry.common.send.helper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import com.gerry.common.framework.constants.send.SendConstants;
import com.gerry.common.framework.redis.RedisManager;
import com.gerry.common.framework.utils.EmptyUtils;
import com.gerry.common.framework.utils.GenerationRandomUtils;

/**
 * 发送验证码短信帮助类
 * 
 *
 * @author gerry
 * @version 1.0, 2016年8月3日
 * @since com.nise 1.0.0
 */
@Slf4j
public class CommonSendMesageHelper {

	@Autowired
	private static RedisManager<String, String> redisManager;

	public static SendConstants generationSmsCode(String phone, String key) {
		SendConstants sc = new SendConstants();
		sc.setPhone(phone);
		sc.setSendMessageKey(key);
		String code = GenerationRandomUtils.createMobileVerifyCode(6);
		sc.setParams(new String[] { code });
		redisManager.saveObjectBySeconds(phone + key, code);
		return sc;
	}

	public static boolean checkCode(String phone, String smsCode, String key) {
		String code = redisManager.getObjectByKey(phone + key);
		if (EmptyUtils.isEmpty(code)) {
			log.error("手机号[" + phone + "]验证的验证码[" + smsCode + "]不存在或已过去");
			return false;
		}
		if (!code.equals(smsCode)) {
			log.error("手机号[" + phone + "]验证的验证码[" + smsCode + "]错误");
		}
		redisManager.deleteObjectByKey(phone + key);
		return true;
	}

}
