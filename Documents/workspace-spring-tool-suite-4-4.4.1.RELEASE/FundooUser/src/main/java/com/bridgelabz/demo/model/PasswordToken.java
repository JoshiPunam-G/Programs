package com.bridgelabz.demo.model;

import org.springframework.data.annotation.Id;

public class PasswordToken {
	@Id
	private String token;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "PasswordToken [token=" + token + ", user=" + user + "]";
	}
}
