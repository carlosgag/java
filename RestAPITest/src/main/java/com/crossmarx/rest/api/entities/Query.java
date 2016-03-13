package com.crossmarx.rest.api.entities;

import java.util.List;

public class Query {
	
	private String className;
	private List<Filter> resultFields;
	private String filter;
	private List<Spec> sortSpecs;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Filter> getResultFields() {
		return resultFields;
	}

	public void setResultFields(List<Filter> resultFields) {
		this.resultFields = resultFields;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public List<Spec> getSortSpecs() {
		return sortSpecs;
	}

	public void setSortSpecs(List<Spec> sortSpecs) {
		this.sortSpecs = sortSpecs;
	}
}
