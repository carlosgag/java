package com.crossmarx.rest.api.entities;

public class AccountResponse {
	
	private StatusMessage statusMessage;
	private String timestamp;
	private AccountRecord record;
	
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
	
	public AccountRecord getRecord() {
		return record;
	}
	
	public void setRecord(AccountRecord record) {
		this.record = record;
	}
	
	@Override
	public String toString(){
		return "statusMessage:" + this.statusMessage + "\n" + 
				"timestamp:" + this.timestamp + "\n" +
				"record:" + this.record + "\n";
	}
}
