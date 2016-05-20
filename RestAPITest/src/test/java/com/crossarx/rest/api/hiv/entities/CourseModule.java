package com.crossarx.rest.api.hiv.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseModule {
	private Integer Id;
	private Integer Course;
	private Integer Module;
	private Double Order;

	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@JsonProperty("Course")
	public Integer getCourse() {
		return Course;
	}

	public void setCourse(Integer course) {
		Course = course;
	}

	@JsonProperty("Module")
	public Integer getModule() {
		return Module;
	}

	public void setModule(Integer module) {
		Module = module;
	}

	@JsonProperty("Order")
	public Double getOrder() {
		return Order;
	}

	public void setOrder(Double order) {
		Order = order;
	}
	
	@Override
	public String toString(){
		return "Id: " + this.Id + "\n"
				+ "Course: " + this.Course + "\n"
				+ "Module: " + this.Module + "\n"
				+ "Order: " + this.Order + "\n";
	}
}
