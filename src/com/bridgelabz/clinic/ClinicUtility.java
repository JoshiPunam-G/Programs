package com.bridgelabz.clinic;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.bridgelaz.clinic.model.ClinicModel;

public class ClinicUtility {
	
	public  ClinicModel readFile() throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper=new ObjectMapper();
		File doctorFile=new File("/home/admin106/Documents/jsonfile/doctor.json");
	    ClinicModel doctorlist=mapper.readValue(doctorFile, ClinicModel.class);
		System.out.println(doctorlist);
		
		File patientFile=new File("/home/admin106/Documents/jsonfile/patient.json");
		ClinicModel patientlist=mapper.readValue(patientFile, ClinicModel.class);
		System.out.println(patientlist);
		//return cliniclist;
		return patientlist;
	}
}
