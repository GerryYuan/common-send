package link.gerry.common.send.helper;

import com.gerry.common.framework.constants.send.SendConstants;
import com.gerry.common.framework.redis.RedisManager;
import com.gerry.common.framework.utils.GenerationRandomUtils;

/**
 * 发送验证码短信帮助类
 * 
 *
 * @author gerry
 * @version 1.0, 2016年8月3日
 * @since com.nise 1.0.0
 */
public class CommonSendMesageHelper {

	public static SendConstants generationSmsCode(String phone, String key,RedisManager<String, String> redisManager) {
		SendConstants sc = new SendConstants();
		sc.setPhone(phone);
		sc.setSendMessageKey(key);
		String code = GenerationRandomUtils.createMobileVerifyCode(6);
		sc.setParams(new String[] { code });
		redisManager.saveObjectBySeconds(phone + key, code);
		return sc;
	}

}
