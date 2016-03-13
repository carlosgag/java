package com.crossmarx.rest.api.entities;

import java.util.List;

public class Filter {

	private Elem elem;
	private List<Filter> children;

	public Elem getElem() {
		return elem;
	}

	public void setElem(Elem elem) {
		this.elem = elem;
	}

	public List<Filter> getChildren() {
		return children;
	}

	public void setChildren(List<Filter> children) {
		this.children = children;
	}
}
