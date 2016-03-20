package com.crossmarx.rest.api.entities.records;

import com.crossmarx.rest.api.entities.StockItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StockItemRecord {
	
	private String className;
	private String key;
	private StockItem data;

	@JsonProperty("class")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public StockItem getData() {
		return data;
	}

	public void setData(StockItem data) {
		this.data = data;
	}
	
	@Override
	public String toString(){
		return 	"key: " + key +"\n" +
				"data:" + data + "\n";
	}
}
