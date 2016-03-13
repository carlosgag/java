package com.crossmarx.rest.api.entities;

public class StatusMessage {

	private String errcode;
	private String message;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Errcode: " + errcode + "\nMessage: " + message;
	}
}
