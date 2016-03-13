package com.crossmarx.rest.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Operator {
	@JsonProperty("and")
	AND,
	@JsonProperty("or")
	OR,
	@JsonProperty("not")
	NOT
}
