package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterDTO {
	
	@NotBlank(message = "username must not be empty")
	private String username;
	@NotNull
    @Size(min=10 ,message="password must not be empty")
	private String password;
	@NotNull
	@Size(min=20, message="email must not be empty")
	private String email;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public RegisterDTO(@NotBlank(message = "username must not be empty") String username,
			@NotNull @Size(min = 10, message = "password must not be empty") String password,
			@NotNull @Size(min = 20, message = "email must not be empty") String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public RegisterDTO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RegisterDTO [username=" + username + ", password=" + password + ", email=" + email + "]";
	}
}
