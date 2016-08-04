package link.gerry.common.send.result;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 聚义通短信返回结果
 * 
 *
 * @author gerry
 * @version 1.0, 2015年9月9日
 * @since com.nise 1.0.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "returnsms")
@XmlType(name = "returnsms")
public class SmsResultForJYT extends SmsResult {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -592983531547671039L;

	public SmsResultForJYT() {
		super();
	}

	// 返回状态值
	@XmlElement(name = "returnstatus")
	private String returnstatus;

	// 返回信息
	@XmlElement(name = "message")
	private String message;

	// 返回余额
	@XmlElement(name = "remainpoint")
	private Integer remainpoint;

	// 本次任务序列ID
	@XmlElement(name = "taskID")
	private String taskID;

	// 成功短信数
	@XmlElement(name = "successCounts")
	private Integer successCounts;

	public SmsResultForJYT(String returnstatus, String message, Integer remainpoint, String taskID, Integer successCounts) {
		super();
		this.returnstatus = returnstatus;
		this.message = message;
		this.remainpoint = remainpoint;
		this.taskID = taskID;
		this.successCounts = successCounts;
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getRemainpoint() {
		return remainpoint;
	}

	public void setRemainpoint(Integer remainpoint) {
		this.remainpoint = remainpoint;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public Integer getSuccessCounts() {
		return successCounts;
	}

	public void setSuccessCounts(Integer successCounts) {
		this.successCounts = successCounts;
	}

	@Override
	public String toString() {
		return "SmsResultForJYT [returnstatus=" + returnstatus + ", message=" + message + ", remainpoint=" + remainpoint + ", taskID=" + taskID + ", successCounts=" + successCounts + "]";
	}
}
