package com.crossmarx.rest.api.entities.records;

import com.crossmarx.rest.api.entities.StockItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "class" })
public class RecordStockItem {
	
	private String key;
	private StockItem data;

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
