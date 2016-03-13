package com.crossmarx.rest.api.entities;

import java.util.List;

public class FilterNode extends Elem {
	
	private String operator;
	private List<Elem> children;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public List<Elem> getChildren() {
		return children;
	}

	public void setChildren(List<Elem> children) {
		this.children = children;
	}
}
