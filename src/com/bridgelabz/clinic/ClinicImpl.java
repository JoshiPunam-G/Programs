package com.bridgelabz.clinic;


import java.io.File;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;


import com.bridgelaz.clinic.model.Appointment;
import com.bridgelaz.clinic.model.Clinic;
import com.bridgelaz.clinic.model.Doctor;
import com.bridgelaz.clinic.model.Patient;
import com.bridgelaz.clinic.model.User;
import Utility.Utility;


public class ClinicImpl implements ClinicInterface{


	public static void main(String[] args) {
		
    ObjectMapper mapper=new ObjectMapper();
   
    
	try {
//		List<User> userlist=mapper.readValue(new File(userfile), new TypeReference<List<User>>() {});
//		
//		List<Clinic> cliniclist=mapper.readValue(new File(clinicfile), new TypeReference<List<Clinic>>() {});
		
		List<Doctor> doctorlist=mapper.readValue(new File(doctorfile), new TypeReference<List<Doctor>>() {});
		
		List<Patient> patientlist=mapper.readValue(new File(patientfile), new TypeReference<List<Patient>>() {});
		
		List<Appointment> appointmentlist=mapper.readValue(new File(appointmentfile), new TypeReference<List<Appointment>>() {});
			
		
		ClinicUtility utill=new ClinicUtility();
	
		
      System.out.println("----CLINIC MANAGEMENT SYSTEM-----");
		
        int count=0;
        
        while(count>=0)
        {
        	System.out.println();
        	System.out.println("1.Add doctor");
            System.out.println("2.Add Patient");
            System.out.println("3.Take Appointment ");
            System.out.println("4.Search Doctor");
            System.out.println("5.Search Patient");
            System.out.println("6.Popular Doctors");
            System.out.println("7.EXIT");
           
            System.out.println();
            System.out.println("Enter Your Choice");
            
        int choice=Utility.isInteger();
        	switch(choice)
        	{
        	case 1:
        		utill.addDoctor(doctorlist);
        		break;
        	
        	case 2:
        		utill.addPatient(patientlist);
        		break;
        		
        	case 3:
        	    utill.fixAppointment(doctorlist, patientlist);
        		break;
        		
        	case 4:
        		
        		System.out.println("****Search Doctor****");
        		int i=0;
        		while(i>=0)
        		{
        			System.out.println("1. Search by name");
        			System.out.println("2. Search by id");
        			System.out.println("3. Search by specialization");
        			System.out.println("4. Search by availability");
        			System.out.println("5.EXIT");
        			System.out.println("Enter Your Choice");
        			int ch=Utility.isInteger();
        			
        			switch(ch)
        			{
        			case 1:
        				System.out.println("Enter Doctor Name");
        				String name=Utility.isString();
        				utill.searchDoctorByName(doctorlist, name);
        				break;
        				
        			case 2:
        				System.out.println("Enter Doctor id");
        				int id=Utility.isInteger();
        				utill.searchDoctorById(doctorlist, id);
        				break;
        				
        			case 3:
        				System.out.println("Enter Doctor Specialization");
        				String specialization=Utility.isString();
        				utill.searchDoctorBySpecialization(doctorlist, specialization);
        				break;
        				
        			case 4:
        				System.out.println("Enter Doctor Availability");
        				String availability=Utility.isString();
        				utill.searchDoctorByAvailability(doctorlist, availability);
        				break;
        				
        			case 5:
        				i=1;
        				System.out.println("EXIT");
        				return;
        			default:
        				i=1;
        				System.out.println("You Entered Wrong Chooice");
        				return;
        			}
        		}
        		
        	case 5:
        		
        		System.out.println("****Search Patient****");
        		int loop=0;
        		while(loop>=0)
        		{
        			System.out.println("1. Search by name");
        			System.out.println("2. Search by id");
        			System.out.println("3. Search by mobile number");
        			System.out.println("4. Search by age");
        			System.out.println("5.EXIT");
        			System.out.println("Enter Your Choice");
        			int ch=Utility.isInteger();
        			
        			switch(ch)
        			{
        			case 1:
        				System.out.println("Enter Patient name");
        				String name=Utility.isString();
        				utill.searchPatientByName(patientlist, name);
        				break;
        				
        			case 2:
        				System.out.println("Enter Patient id");
        				int id=Utility.isInteger();
        				utill.searchPatientById(patientlist, id);
        				break;
        				
        			case 3:
        				System.out.println("Enter Patient Mobile Number");
        				long mobile=Utility.isLong();
        				utill.searchPatientByMobile(patientlist, mobile);
        				break;
        				
        			case 4:
        				System.out.println("Enter Patient Age");
        				int age=Utility.isInteger();
        				utill.searchPatientByAge(patientlist, age);
        				break;
        				
        			case 5:
        				i=1;
        				System.out.println("EXIT");
        				return;
        				
        			default:
        				i=1;
        				System.out.println("You Entered Wrong Chooice");
        				return;
        			}
        		}
        		
        	case 6:
        		utill.popular(doctorlist, appointmentlist);
        		break;
        		
        	case 7:
        		System.out.println("EXIT");
        		count=1;
        		return;
        	
        		
        		default:
        			count=1;
        			System.out.println("You Entred Wrong Choice");
        			return;
        	}
        }
		
		
		}
	 catch (IOException e) {
		e.printStackTrace();
	}
	
	}


	
}
