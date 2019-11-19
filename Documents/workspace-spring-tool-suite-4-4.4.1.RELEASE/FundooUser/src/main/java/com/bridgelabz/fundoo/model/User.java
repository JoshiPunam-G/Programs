
/**
 * Purpose : Model Class For User Login.

 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019
 */

package com.bridgelabz.fundoo.model;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.notes.model.Note;

@Document
public class User implements Serializable  {
	@Id
	private String id;
	@NotBlank(message = "username must not be empty")
	private String username;
	@NotBlank(message="password must not be empty")
	private String password;
	@NotBlank(message="email must not be empty")
	private String email;
	
	private boolean isUpload;

	
	
	private List<Note> notelist;
	private List<Label> labellist;
	public List<Label> getLabellist() {
		return labellist;
	}
	public void setLabellist(List<Label> labellist) {
		this.labellist = labellist;
	}
	public List<Note> getNotelist() {
		return notelist;
	}
	public void setNotelist(List<Note> notelist) {
		this.notelist = notelist;
	}
	//constructor 
	public User(String id ,String username, String password, String email,boolean isUpload) {
	    this.id=id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.isUpload=isUpload;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User() {
	}
	public User(String key, String value) {

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

	public boolean isUpload() {
		return isUpload;
	}
	public void setUpload(boolean isUpload) {
		this.isUpload = isUpload;
	}
	public void setResetToken(Object object) {
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", isUpload=" + isUpload + "]";
	}
	
	
//	public List<Note> getNotes() {
//		return null;
//	}
//	
}
