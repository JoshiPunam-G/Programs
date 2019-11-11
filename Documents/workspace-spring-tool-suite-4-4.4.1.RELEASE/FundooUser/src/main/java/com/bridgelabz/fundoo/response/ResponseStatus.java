package com.bridgelabz.fundoo.response;

public class ResponseStatus {
	
	public static Response statusInformation(String statusmessage,int statuscode )
	{
		Response response=new Response();
		response.setStatuscode(statuscode);
		response.setStatusMessage(statusmessage);
		return response;
	}

	public static Response tokenstatusInformation(String statusmessage,int statuscode,String token) {
		Response response=new Response();
		response.setStatusMessage(statusmessage);
		response.setStatuscode(statuscode);
		return response;
		
	}

}
