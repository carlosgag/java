package com.crossmarx.rest.api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	
	private String LoginName;
	private String Password;
	private Integer Usergroup;
	private String Firstname;
	private String Lastname;
	private Integer Id;
	private String Email;

	@JsonProperty("LoginName")
	public String getLoginName() {
		return LoginName;
	}
	
	public void setLoginName(String LoginName) {
		this.LoginName = LoginName;
	}

	@JsonProperty("Password")
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String Password) {
		this.Password = Password;
	}

	@JsonProperty("Usergroup")
	public Integer getUsergroup() {
		return Usergroup;
	}
	
	public void setUsergroup(Integer Usergroup) {
		this.Usergroup = Usergroup;
	}

	@JsonProperty("Firstname")
	public String getFirstname() {
		return Firstname;
	}
	
	public void setFirstname(String Firstname) {
		this.Firstname = Firstname;
	}

	@JsonProperty("Lastname")
	public String getLastname() {
		return Lastname;
	}
	
	public void setLastname(String Lastname) {
		this.Lastname = Lastname;
	}

	@JsonProperty("Id")
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer Id) {
		this.Id = Id;
	}

	@JsonProperty("Email")
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	@Override
	public String toString() {
		return "Id:" + this.Id + "\n" + 
				"Firstname:" + this.Firstname + this.Lastname + "\n" + 
				"LoginName:" + this.LoginName + "\n" + 
				"Password:" + this.Password + "\n" + 
				"Email:" + this.Email + "\n" +
				"Usergroup:" + this.Usergroup;
	}
}
