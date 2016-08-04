package link.gerry.common.send.result;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import com.gerry.common.framework.dto.BaseDto;

public class SmsResult extends BaseDto {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7853490468990500349L;

	public static final String STATUS_SUCC = "Success";

	public static final String STATUS_FAIL = "Faild";

	public enum Status {
		
		SUCC(1, STATUS_SUCC), FAIL(2, STATUS_FAIL);

		private Status(int code, String value) {
			this.code = code;
			this.value = value;
		}

		/**
		 * 编码
		 */
		@Getter
		private final int code;

		/**
		 * 描述
		 */
		@Getter
		private final String value;

		@Getter
		private final static Map<Integer, String> param = new HashMap<Integer, String>();

		static {
			for (Status status : Status.values()) {
				param.put(status.getCode(), status.getValue());
			}
		}

	}
}
