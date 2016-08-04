package link.gerry.common.send.dao;

import link.gerry.common.send.entity.CommonSendMessage;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.ReturnGeneratedId;
import org.jfaster.mango.annotation.SQL;

@DB(table = "send_sms")
public interface CommonSendMessageDAO {
	String COLUMNS = "phone, content, remark, supplier, type, status, create_time";

	String ALL_COLUMNS = "id" + "," + COLUMNS;

	@ReturnGeneratedId
	@SQL("insert into #table(" + COLUMNS + ") values(:phone, :content, :remark, :supplier, :type, :status, :createTime)")
	int addCommonSendMessage(CommonSendMessage commonSendMessage);

	@SQL("select " + ALL_COLUMNS + " from #table where id = :1")
	CommonSendMessage getCommonSendMessage(int id);

	@SQL("update #table set phone=:phone, content=:content, remark=:remark, supplier=:supplier, type=:type, status=:status, create_time=:createTime where id = :id")
	boolean updateCommonSendMessage(CommonSendMessage commonSendMessage);

	@SQL("delete from #table where id = :1")
	boolean deleteCommonSendMessage(int id);
}
