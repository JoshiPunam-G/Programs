package com.bridgelabz.opps;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtill {
	private static ObjectMapper mapper;
	
	static
	{
		mapper=new ObjectMapper();
	}
	
	public static String convertJavaToJson(Object object)
	{
		String jsonResult="";
		try {
			jsonResult=mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
    System.out.println("Exception occured while convrting java object into json"+e.getMessage());
		} catch (JsonMappingException e) {
			 System.out.println("Exception occured while convrting java object into json"+e.getMessage());
		} catch (IOException e) {
			 System.out.println("Exception occured while convrting java object into json"+e.getMessage());
		}
		
		return jsonResult;
		
	}
	
	

}
