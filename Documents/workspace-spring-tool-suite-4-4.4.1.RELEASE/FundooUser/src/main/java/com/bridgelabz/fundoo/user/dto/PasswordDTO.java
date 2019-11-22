package com.bridgelabz.fundoo.user.dto;

public class PasswordDTO {
	private String newpassword;
	private String confirmpassword;
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public PasswordDTO(String newpassword, String confirmpassword) {
		super();
		this.newpassword = newpassword;
		this.confirmpassword = confirmpassword;
	}
	public PasswordDTO() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PasswordDTO [newpassword=" + newpassword + ", confirmpassword=" + confirmpassword + "]";
	}
}
