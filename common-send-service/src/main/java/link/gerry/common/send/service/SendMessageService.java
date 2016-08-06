package link.gerry.common.send.service;

import org.springframework.scheduling.annotation.Async;

import com.gerry.common.framework.result.ViewModelResult;

public interface SendMessageService<T> {

	/**
	 * 发送异步短信（模板）
	 * 
	 * @param phone
	 * @param type
	 * @return
	 * @see
	 */
	@Async
	void sendSms(String phone, String key) throws Exception;

	/**
	 * 验证短信验证码
	 * 
	 * @param phone
	 * @param smsCode
	 * @param key
	 * @return
	 * @see
	 */
	ViewModelResult<?> checkSmsCode(String phone, String smsCode, String key);

	/**
	 * 可以发送验证码
	 * 
	 * @param phone
	 * @param key
	 * @return
	 * @see
	 */
	boolean canSend(String phone, String key);
}
