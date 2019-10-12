package com.bridgelabz.clinic;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public class ClinicMain {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException  {
		ClinicUtility clinic=new ClinicUtility();
		clinic.readFile();

		
	}

}
