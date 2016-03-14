package com.crossmarx.rest.api.entities;

import java.util.Date;

public class StockItem {
	
	private Integer Id;
	private String Name;
	private String Date_in_stock;
	private Integer Account;
	private String Image;
	private Double Costs;
	private Integer Number_in_stock;
	private Double Weight;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDate_in_stock() {
		return Date_in_stock;
	}

	public void setDate_in_stock(String date_in_stock) {
		Date_in_stock = date_in_stock;
	}

	public Integer getAccount() {
		return Account;
	}

	public void setAccount(Integer account) {
		Account = account;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public Double getCosts() {
		return Costs;
	}

	public void setCosts(Double costs) {
		Costs = costs;
	}

	public Integer getNumber_in_stock() {
		return Number_in_stock;
	}

	public void setNumber_in_stock(Integer number_in_stock) {
		Number_in_stock = number_in_stock;
	}

	public Double getWeight() {
		return Weight;
	}

	public void setWeight(Double weight) {
		Weight = weight;
	}
}
