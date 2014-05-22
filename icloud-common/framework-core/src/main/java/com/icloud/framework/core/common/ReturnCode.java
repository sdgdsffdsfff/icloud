package com.icloud.framework.core.common;

public enum ReturnCode {
	SUCCESS("S00200"),

	ERROR("E00500"),

	E_DUPKEY("E00501"),

	E_DUPNAME("E00502"),

	E_NOTEXISTS("E00503"),
	// 数据校验失败
	E_DATA_VALIDATION_ERROR("E00504"),
	// 返回了多条记录
	E_MORE_THAN_ONE_RECORD_FOUND("E00505"),
	// 页号越界
	E_PAGEINDEX_OUT_OF_BOUNDS("E00506"),

	E_LOGIN("E00400"),

	E_INVALID_AMOUNT("E00601"),

	E_STATUS_ERROR("E00602"),

	S_REDIRECT("S00000"),

	E_TIMEOUT("E00600"),

	E_IS_NULL("E00410"),

	// 股票基础信息补全
	E_STOCK_LOCATION_ERROR("E10100"),

	// 导数据出错
	E_IMPORT_STOCK_DATA_HISTORY_ERROR("E20100"),

	;

	private String errorCode;

	ReturnCode(String code) {
		errorCode = code;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
