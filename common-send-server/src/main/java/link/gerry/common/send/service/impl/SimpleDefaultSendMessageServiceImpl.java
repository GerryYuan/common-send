package link.gerry.common.send.service.impl;

import java.io.UnsupportedEncodingException;

import link.gerry.common.send.entity.CommonSendMessage;
import link.gerry.common.send.result.SmsResult;
import link.gerry.common.send.result.SmsResultForJYT;

import org.springframework.beans.factory.annotation.Value;

import com.gerry.common.framework.constants.CommonConstants;
import com.gerry.common.framework.utils.xml.JaxbXmlUtils;

/**
 * 一个简单默认
 * 
 *
 * @author gerry
 * @version 1.0, 2016年8月4日
 * @since com.nise 1.0.0
 */
public class SimpleDefaultSendMessageServiceImpl extends AbstractSendMessageService<SmsResultForJYT> {

	@Value("${sms.juyitong.userId}")
	private String userId;

	@Value("${sms.juyitong.account}")
	private String account;

	@Value("${sms.juyitong.password}")
	private String password;

	@Value("${sms.juyitong.url}")
	private String url;

	@Override
	public String buildUrl(String phone, String content) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder(url).append(CommonConstants.SYMBOL_AMPERSAND).append("userid=" + userId);
		sb.append(CommonConstants.SYMBOL_AMPERSAND).append("account=" + account).append(CommonConstants.SYMBOL_AMPERSAND).append("password=" + password).append(CommonConstants.SYMBOL_AMPERSAND)
				.append("mobile=" + phone).append(CommonConstants.SYMBOL_AMPERSAND).append("content=" + content)
				.append(CommonConstants.SYMBOL_AMPERSAND).append("sendTime=").append(CommonConstants.SYMBOL_AMPERSAND).append("extno=");
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

}
