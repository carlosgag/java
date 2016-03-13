package com.crossmarx.rest.api.entities;

public class Login {
	private String loginname;
	private String password;

	public Login(String loginname, String password) {
		this.loginname = loginname;
		this.password = password;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginName: " + this.loginname + "\nPassword: " + this.password;
	}
}
