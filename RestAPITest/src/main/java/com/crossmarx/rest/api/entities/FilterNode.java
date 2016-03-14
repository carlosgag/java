package com.crossmarx.rest.api.entities;

import java.util.List;

public class FilterNode implements Filter {
	
	private String operator;
	private List<Filter> children;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<Filter> getChildren() {
		return children;
	}

	public void setChildren(List<Filter> children) {
		this.children = children;
	}
}
