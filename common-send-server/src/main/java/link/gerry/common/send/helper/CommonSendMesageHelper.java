package link.gerry.common.send.helper;

import com.gerry.common.framework.constants.send.SendConstants;
import com.gerry.common.framework.utils.GenerationRandomUtils;

/**
 * 发送验证码短信帮助类
 * 
 *
 * @author gerry
 * @version  1.0, 2016年8月3日
 * @since   com.nise 1.0.0
 */
public class CommonSendMesageHelper {

	public static SendConstants generationSmsCode(String phone, String key){
		SendConstants sc = new SendConstants();
		sc.setPhone(phone);
		sc.setSendMessageKey(key);
		sc.setParams(new String[] { GenerationRandomUtils.createMobileVerifyCode(6) });
		return sc;
	}
}
