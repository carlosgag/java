package com.crossmarx.rest.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockItem {
	
	private Integer Id;
	private String Name;
	private String Date_in_stock;
	private Integer Account;
	private String Image;
	private Integer Costs;
	private Integer Number_in_stock;
	private Double Weight;

	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	@JsonProperty("Name")
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@JsonProperty("Date_in_stock")
	public String getDate_in_stock() {
		return Date_in_stock;
	}

	public void setDate_in_stock(String date_in_stock) {
		Date_in_stock = date_in_stock;
	}

	@JsonProperty("Account")
	public Integer getAccount() {
		return Account;
	}

	public void setAccount(Integer account) {
		Account = account;
	}

	@JsonProperty("Image")
	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	@JsonProperty("Costs")
	public Integer getCosts() {
		return Costs;
	}

	public void setCosts(Integer costs) {
		Costs = costs;
	}

	@JsonProperty("Number_in_stock")
	public Integer getNumber_in_stock() {
		return Number_in_stock;
	}

	public void setNumber_in_stock(Integer number_in_stock) {
		Number_in_stock = number_in_stock;
	}

	@JsonProperty("Weight")
	public Double getWeight() {
		return Weight;
	}

	public void setWeight(Double weight) {
		Weight = weight;
	}

	@Override
	public String toString(){
		return 	"Id: " + Id +"\n" +
				"Name: " + Name +"\n" +
				"Date_in_stock: " + Date_in_stock +"\n" +
				"Account: " + Account +"\n" +
				"Image:" + Image + "\n" + 
				"Costs:" + Costs + "\n" + 
				"Number_in_stock:" + Number_in_stock + "\n" + 
				"Weight:" + Weight + "\n";
	}
}
