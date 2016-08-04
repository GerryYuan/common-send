package link.gerry.common.send.entity;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.gerry.common.framework.model.BaseModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonSendMessage extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3726438424720584700L;

	private Integer id;

	private String phone;

	private String content;

	private String remark;

	private String supplier;

	private Byte type;

	private Byte status;

	private Date createTime;
}
