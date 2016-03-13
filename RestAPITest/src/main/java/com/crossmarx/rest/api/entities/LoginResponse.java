package com.crossmarx.rest.api.entities;

public class LoginResponse {

	private StatusMessage statusMessage;
	private String timestamp;
	private String authhash;

	public StatusMessage getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(StatusMessage statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getAuthHash() {
		return authhash;
	}

	public void setAuthhash(String authhash) {
		this.authhash = authhash;
	}

	@Override
	public String toString() {
		return "StatusMessage: " + statusMessage + "\nTimeStamp: " + timestamp + "\nAuthHash: " + authhash;
	}
}
