package com.crossmarx.rest.api.entities;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class AccountResponse {
	
	private StatusMessage statusMessage;
	private String timestamp;
	private ObjectNode record;
	
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
	
	public ObjectNode getRecord() {
		return record;
	}
	
	public void setRecord(ObjectNode record) {
		this.record = record;
	}
}
