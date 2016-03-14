package com.crossmarx.rest.api.entities;

import java.util.List;

public class Query {
	
	private String className;
	private List<String> resultFields;
	private Filter filter;
	private List<Spec> sortSpecs;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getResultFields() {
		return resultFields;
	}

	public void setResultFields(List<String> resultFields) {
		this.resultFields = resultFields;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public List<Spec> getSortSpecs() {
		return sortSpecs;
	}

	public void setSortSpecs(List<Spec> sortSpecs) {
		this.sortSpecs = sortSpecs;
	}
}
