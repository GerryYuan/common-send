package link.gerry.common.send.service.impl;

import java.util.Date;

import link.gerry.common.send.dao.CommonSendMessageDAO;
import link.gerry.common.send.entity.CommonSendMessage;
import link.gerry.common.send.service.CommonSendMessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发送短信操作数据库
 * 
 *
 * @author gerry
 * @version  1.0, 2016年8月3日
 * @since   com.nise 1.0.0
 */
@Service
public class CommonSendMessageServiceImpl implements CommonSendMessageService {

	@Autowired
	private CommonSendMessageDAO commonSendMessageDAO;

	@Override
	public CommonSendMessage getById(Integer id) {
		return commonSendMessageDAO.getCommonSendMessage(id);
	}

	@Override
	public int save(CommonSendMessage t) {
		t.setCreateTime(new Date());
		return commonSendMessageDAO.addCommonSendMessage(t);
	}

	@Override
	public int delete(Integer id) {
		return commonSendMessageDAO.deleteCommonSendMessage(id) ? 1 : 0;
	}

	@Override
	public int update(CommonSendMessage t) {
		return 0;
	}

}
