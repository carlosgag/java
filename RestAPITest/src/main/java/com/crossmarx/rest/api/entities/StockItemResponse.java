package com.crossmarx.rest.api.entities;

import com.crossmarx.rest.api.entities.records.StockItemRecord;

public class StockItemResponse {

	private StatusMessage statusMessage;
	private String timestamp;
	private StockItemRecord record;

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

	public StockItemRecord getStockItemRecord() {
		return record;
	}

	public void setStockItemRecord(StockItemRecord record) {
		this.record = record;
	}

	@Override
	public String toString() {
		return "statusMessage:" + statusMessage + "\n"+
				"timestamp:" + timestamp + "\n"+
				"record:" + record + "\n";
	}
}
