package com.crossmarx.rest.api.entities;

import com.crossmarx.rest.api.entities.records.RecordStockItem;

public class StockItemResponse {

	private StatusMessage statusMessage;
	private String timestamp;
	private String authhash;
	private RecordStockItem record;

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

	public RecordStockItem getRecord() {
		return record;
	}

	public void setRecordStockItem(RecordStockItem record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "statusMessage:" + statusMessage + "\n"+
				"timestamp:" + timestamp + "\n"+
				"authhash:" + authhash + "\n"+
				"record:" + record + "\n";
	}
}
