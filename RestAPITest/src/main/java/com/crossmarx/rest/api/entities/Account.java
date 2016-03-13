package com.crossmarx.rest.api.entities;

public class Account {
	private String LoginName;
	private String Password;
	private Integer Usergroup;
	private String Firstname;
	private String Lastname;
	private Integer Id;
	private String Email;
	
	public String getLoginName() {
		return LoginName;
	}
	
	public void setLoginName(String LoginName) {
		this.LoginName = LoginName;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String Password) {
		this.Password = Password;
	}
	
	public Integer getUsergroup() {
		return Usergroup;
	}
	
	public void setUsergroup(Integer Usergroup) {
		this.Usergroup = Usergroup;
	}
	
	public String getFirstname() {
		return Firstname;
	}
	
	public void setFirstname(String Firstname) {
		this.Firstname = Firstname;
	}
	
	public String getLastname() {
		return Lastname;
	}
	
	public void setLastname(String Lastname) {
		this.Lastname = Lastname;
	}
	
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer Id) {
		this.Id = Id;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String Email) {
		this.Email = Email;
	}
	
	@Override
	public String toString() {
		return this.Id + "\n" + 
			this.Firstname + this.Lastname + "\n" + 
			this.LoginName + "\n" + 
			this.Password + "\n" + 
			this.Email + "\n" +
			this.Usergroup;
	}
}
