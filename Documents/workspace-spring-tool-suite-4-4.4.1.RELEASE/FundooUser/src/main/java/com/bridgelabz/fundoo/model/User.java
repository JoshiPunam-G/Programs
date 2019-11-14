
/**
 * Purpose : Model Class For User Login.

 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019
 */

package com.bridgelabz.fundoo.model;
import java.util.List;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.bridgelabz.fundoo.label.model.Label;

@Document
public class User {
	@Id
	private String id;
	@NotBlank(message = "username must not be empty")
	private String username;
	@NotBlank(message="password must not be empty")
	private String password;
	@NotBlank(message="email must not be empty")
	private String email;
	
	private String note;
  
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	//constructor 
	public User(String id ,String username, String password, String email) {
	    this.id=id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User() {
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
	public boolean isPresent() {
		return false;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", note="
				+ note + "]";
	}
	public void setResetToken(Object object) {
	}
	public List<Label> getLabel() {
		
		return null;
	}
}
