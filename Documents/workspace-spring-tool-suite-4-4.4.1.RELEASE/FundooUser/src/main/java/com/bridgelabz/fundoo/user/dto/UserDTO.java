
/**
 * Purpose :Implementation Of UserDTO to reduce the number of calls when working with remote interface.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019  
 */
package com.bridgelabz.fundoo.user.dto;

public class UserDTO {

	private String email;
	private String password;
	//constructor 
	public UserDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	//getter and setter
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", password=" + password + "]";
	}
	
	
}

