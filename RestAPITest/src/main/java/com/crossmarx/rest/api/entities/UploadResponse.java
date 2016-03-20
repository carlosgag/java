package com.crossmarx.rest.api.entities;

public class UploadResponse {
	
	private StatusMessage statusMessage;
	private String timestamp;
	private String key;
	
	public StatusMessage getStatusMessage(){
		return this.statusMessage;
	}
	
	public void setStatusMessage(StatusMessage statusMessage){
		this.statusMessage = statusMessage;
	}
	
	public String getTimestamp(){
		return this.timestamp;
	}
	
	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public void setKey(String key){
		this.key = key;
	}
	
	@Override
	public String toString(){
		return "statusMessage:" + this.statusMessage + "\n" +
				"timestamp:" + this.timestamp + "\n" + 
				"key:" + this.key + "\n";
	}
}
