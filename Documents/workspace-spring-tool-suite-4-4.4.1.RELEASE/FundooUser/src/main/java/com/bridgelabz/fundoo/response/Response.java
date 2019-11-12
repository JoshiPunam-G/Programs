package com.bridgelabz.fundoo.response;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Response {
	private String  statusMessage;
	private int statuscode;
	private String token;
	
	//private String token;

	public String getStatusMessage() {
		return statusMessage;
	}
	public Response(String statusMessage, int statuscode,String token) {
		super();
		this.statusMessage = statusMessage;
		this.statuscode = statuscode;
		this.token=token;
	}
	public Response(int i, String string) {
		super();
		// TODO Auto-generated constructor stub
	}
	public Response() {
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "Response [statusMessage=" + statusMessage + ", statuscode=" + statuscode + ", token=" + token + "]";
	}
	public void setToken(String token) {
		this.token = token;
	}
	

	
	
}
