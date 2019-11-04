package com.bridgelabz.demo.config;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Response {
	
	private String  statusMessage;
	private int statuscode;
	private String token;
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	

}
