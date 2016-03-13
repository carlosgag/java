package com.crossmarx.rest.api.entities;

import java.util.Map;

@Deprecated
public class ResponseHeaders {
	
	private Map<String, String> headers;	

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public String toString() {
		return null;
	}
}
