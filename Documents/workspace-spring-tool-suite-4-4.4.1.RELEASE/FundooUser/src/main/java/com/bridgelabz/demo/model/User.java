
/**
 * Purpose : Model Class For User Login.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019
 */

package com.bridgelabz.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Document

public class User {
	@Id
	private String id;
	private String username;
	private String password;
	private String email;


	//constructor 
	public User(String username, String password, String email ,List<SimpleGrantedAuthority> authorities) {
	
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	//getter and setter method 
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
	//to string method
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + "]";
	}

	public boolean isPresent() {
		return false;
	}
	
	
	

}
