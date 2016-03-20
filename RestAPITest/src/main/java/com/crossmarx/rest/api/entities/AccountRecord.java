package com.crossmarx.rest.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRecord {
	
	private String className;
	private String key;
	private Account data;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Account getData() {
		return data;
	}

	public void setData(Account data) {
		this.data = data;
	}

	@JsonProperty("class")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public String toString(){
		return "class:" + this.className + "\n" + 
				"key:" + this.key + "\n" + 
				"data:" + this.data + "\n";
	}
}
