package com.crossmarx.rest.api;

public enum ErrorCode {
	OK(0, "Ok"), 
	SERVER_ERROR(5, "Server error"), 
	ILLEGAL_API_VERSION(6, "Illegal api version"), 
	UNKNOW_APPLICATION_VERSION(7, "Unknown application version"), 
	REQUEST_ERROR(10, "Error in request"),
	LOGON_FAILED(20, "Authentication failed"), 
	NO_USER(21, "Not logged on"), 
	NO_PERMISSION(25, "No permission"),
	SQL_ERROR(30, "SQL Error"),
	LOCKED_BY_OTHER_USER(31, "Locked by other user"), 
	UPLICATE_LOCK(32, "Duplicate lock"), 
	LOCKED_BY_OTHER_SESSION(33, "Locked by other session"), 
	INTEGRITY(34, "Data integrity violation"),
	NO_SUCH_RECORD(35, "Data integrity violation"),
	UPLOAD_FAILED(50, "File upload failed"), 
	IMAGEIO_ERROR(51, "Image can not be stored");
	
	private Integer code;
	private String message;

	ErrorCode(Integer code, String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
