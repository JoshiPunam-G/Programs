package com.bridgelabz.clinic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.bridgelabz.addressbook.model.Person;
import com.bridgelaz.clinic.model.Appointment;
import com.bridgelaz.clinic.model.Clinic;
import com.bridgelaz.clinic.model.Doctor;
import com.bridgelaz.clinic.model.Patient;
import com.bridgelaz.clinic.model.User;
import Utility.Utility;
import jdk.nashorn.internal.runtime.Specialization;

public class ClinicUtility implements ClinicInterface {
	
	
	 public static ObjectMapper mapper=new ObjectMapper(); 
	    
	/**
	 * Purpose :Method For Adding Users.
	 * @param userlist
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void addUser(List<User> userlist) throws JsonGenerationException, JsonMappingException, IOException
	{
		String userfile="/home/admin106/Documents/jsonfile/clinic/user.json";	
		System.out.println("*** ADD USER ***");
		
		System.out.println("Enter User id");
		int id=Utility.isInteger();
		Utility.isString();
		System.out.println("Enter  User name");
		String name=Utility.isString();
		System.out.println("Enter User Email");
		String email=Utility.isString();
		System.out.println("Enter User Address");
		String address=Utility.isString();
		User user=new User(id , name,email,address);
		userlist.add(user);
		System.out.println(user.toString());
		mapper.defaultPrettyPrintingWriter().writeValue(new File(userfile), userlist);
		//mapper.writeValue(new File(userfile), userlist);
	}
	/**
	 * Purpose :Method for Adding clinic .
	 * @param cliniclist
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public void addClinic(List<Clinic> cliniclist) throws JsonGenerationException, JsonMappingException, IOException
	{
		String clinicfile="/home/admin106/Documents/jsonfile/clinic/clinic.json";
		System.out.println("*** ADD CLINIC ***");
		
		System.out.println("Enter Clinic id");
		int id=Utility.isInteger();
		Utility.isString();
		System.out.println("Enter Clinic Name");
		String name=Utility.isString();
		System.out.println("Enter Clinic Address");
		String address=Utility.isString();
		Clinic clinic=new Clinic(id,name,address);
		cliniclist.add(clinic);
		System.out.println(clinic.toString());
		mapper.defaultPrettyPrintingWriter().writeValue(new File(clinicfile), cliniclist);
		
	}
	
	/**
	 * Purpose :Method for adding doctor.
	 * @param doctorlist
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	public void addDoctor(List<Doctor> doctorlist) throws JsonGenerationException, JsonMappingException, IOException
	{
		String clinicfile="/home/admin106/Documents/jsonfile/clinic/doctor.json";
		System.out.println("*** ADD DOCTOR ***");
		
		System.out.println("Enter Doctor id");
		int id=Utility.isInteger();
		Utility.isString();
		System.out.println("Enter Doctor Name");
		String name=Utility.isString();
		System.out.println("Enter Doctor Specialization");
		String specialization=Utility.isString();
		System.out.println("Enter Doctor Avaialability");
		String availability=Utility.isString();
		Doctor doctor=new Doctor(name,id,specialization,availability);
		doctorlist.add(doctor);
		System.out.println(doctor.toString());
		mapper.defaultPrettyPrintingWriter().writeValue(new File(clinicfile), doctorlist);
		
	}
	
	/**
	 * Purpose:Method For adding patient .
	 * @param patientlist
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	public void addPatient(List<Patient> patientlist) throws JsonGenerationException, JsonMappingException, IOException
	{
		String patientfile="/home/admin106/Documents/jsonfile/clinic/patient.json";
		System.out.println("*** ADD PATIENT ***");
		
		System.out.println("Enter Patient id");
		int id=Utility.isInteger();
		Utility.isString();
		System.out.println("Enter Patient Name");
		String name=Utility.isString();
		System.out.println("Enter Patient Mobile Number");
		long mobile=Utility.isLong();
		System.out.println("Enter Patient Age");
		int age=Utility.isInteger();
		Patient  patient=new Patient(name,id,mobile,age);
		patientlist.add(patient);
		System.out.println(patient.toString());
		mapper.defaultPrettyPrintingWriter().writeValue(new File(patientfile), patientlist);
		
	}
	
	/**
	 * Purpose :Method for Adding appointment.
	 * @param appointmentlist
	 * @return 
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
//	public int addAppointment(List<Appointment> appointmentlist) throws JsonGenerationException, JsonMappingException, IOException
//	{
//       System.out.println("*** ADD APPOINTMENT ***");
//       System.out.println("Enter Appointment id");
//       int id=Utility.isInteger();
//       Utility.isString();
//       System.out.println("Enter Appointment Date");
//       String date=Utility.isString();
//       System.out.println("Enter Appointment Number");
//       int number=Utility.isInteger();
//       System.out.println("Enter Appointment Timing");
//       String time=Utility.isString();
//     //  System.out.println("Enter Appointment count");
//     
//
//       Appointment appoint=new Appointment(id,date,number,time);
//       appointmentlist.add(appoint);
//       
//       System.out.println(appoint.toString());
//       
//       mapper.defaultPrettyPrintingWriter().writeValue(new File(appointmentfile), appointmentlist);
//	return number;
//		
//	}
	/**
	 * Purpose:Method for search patient by name
	 * @param patientlist
	 * @param name
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	public boolean searchPatientByName(List<Patient> patientlist,String name) throws JsonParseException, JsonMappingException, IOException
	{
		Patient patient1= patientlist.stream().filter((p1)->name.equalsIgnoreCase(p1.getPatientName())).findAny().orElse(null);
		System.out.println();
		return true;	
	}
	/**
	 * Purpose:Methodfor search patient by id
	 * @param patientlist
	 * @param id
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void searchPatientById(List<Patient>patientlist,int id) throws JsonParseException, JsonMappingException, IOException
	{
		Patient patient2= patientlist.stream().filter((p2)->id==p2.getPatientId()).findAny().get();
		System.out.println(patient2);
	
	}
	/**
	 * Purpose :Method for search patient by mobile number
	 * @param patientlist
	 * @param mobileno
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void searchPatientByMobile(List<Patient> patientlist,long mobileno) throws JsonParseException, JsonMappingException, IOException
	{
		Patient patient3= patientlist.stream().filter((p3)->mobileno==p3.getMobileNo()).findAny().get();
		System.out.println(patient3);
	
	}
	/**
	 * Purpose :Method for search patient by age.
	 * @param patientlist
	 * @param age
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void searchPatientByAge(List<Patient> patientlist,int age) throws JsonParseException, JsonMappingException, IOException
	{
		Patient patient4= patientlist.stream().filter((p4)->age==p4.getMobileNo()).findAny().get();
		System.out.println(patient4);
	
	}
	
	/**
	 * Purpose :Method for search doctor by name
	 * @param doctorlist
	 * @param name
	 * @return 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public boolean searchDoctorByName(List<Doctor> doctorlist,String name) throws JsonParseException, JsonMappingException, IOException
	{
		Doctor doctor= doctorlist.stream().filter((d)->name.equalsIgnoreCase(d.getDoctorName())).findAny().orElse(null);
		System.out.println(doctor);
		return true;
	
	}
	
	/**
	 * Purpose :Method for search doctor by id.
	 * @param doctorlist
	 * @param id
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void searchDoctorById(List<Doctor> doctorlist,int id) throws JsonParseException, JsonMappingException, IOException
	{
		Doctor doctor1= doctorlist.stream().filter((d1)->id==d1.getDoctorId()).findAny().get();
		System.out.println(doctor1);
	
	}
	/**
	 * Purpose :Method for Search doctor bye speacialization.
	 * @param doctorlist
	 * @param specialization
	 * @return 
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public List<Specialization> searchDoctorBySpecialization(List<Doctor> doctorlist,String specialization) throws JsonParseException, JsonMappingException, IOException
	{
		Doctor doctor2= doctorlist.stream().filter((d2)->specialization.equalsIgnoreCase(d2.getSpecialization())).findAny().orElse(null);
		System.out.println(doctor2);
		return null;
	   
	}
	
	/**
	 * Purpose :Method for Search doctor by availability .
	 * @param doctorlist
	 * @param availability
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void searchDoctorByAvailability(List<Doctor> doctorlist,String availability) throws JsonParseException, JsonMappingException, IOException
	{
		Doctor doctor3= doctorlist.stream().filter((d3)->availability.equalsIgnoreCase(d3.getAvailability())).findAny().orElse(null);
		System.out.println(doctor3);
	
	}
	
	
	public  int countAppointment(List<Appointment> appointmentlist,String name )
	{
		int index=-1;
		for(int i=appointmentlist.size()-1;i>=0;i--)
		{
			String dname=appointmentlist.get(i).getDname();
			if(name.compareTo(dname)==0)
			{
				index= i;
				break;
			}
			
		}
		return index;
	}
	
   public void fixAppointment(List<Doctor> doctorlist, List<Patient> patientlist) throws JsonGenerationException, JsonMappingException, IOException
   {
	   
	   String appointmentfile="/home/admin106/Documents/jsonfile/clinic/appointment.json";
	try {
	    List<Appointment> appointmentlist=mapper.readValue(new File(appointmentfile), new TypeReference<List<Appointment>>() {});
	Utility.isString();
	   System.out.println("Enter Doctor Name");
	   String name=Utility.isString();
	   Doctor doctors=new Doctor();
	   Patient patient=new Patient();
	 //  boolean status=true;
	   
	  if( searchDoctorByName(doctorlist,name)==true)
	  {
		  Utility.isString();
		  System.out.println("Enter Patient Name");
		   String pname=Utility.isString();
		   if(searchPatientByName(patientlist,pname)==true)
		   {
			   System.out.println("Enter Appointment Date");
			   String date=Utility.isString();
			   int index=countAppointment(appointmentlist,name);
			   int count;
			   if(index==-1)
			   {
				   count=1;
			   }
			   else
			   {
				   count=appointmentlist.get(index).getCount();
				   count=count+1;
			   }
			   
			   if(count<=5)
			   {
				   Appointment appoint=new  Appointment(name,pname,date ,count);
				   appointmentlist.add(appoint);
				   mapper.defaultPrettyPrintingWriter().writeValue(new File(appointmentfile),  appointmentlist);
				   
				   System.out.println("Appointment added");
				   System.out.println(appoint.toString());
				   
			   }
			   else
			   {
				   System.out.println("Doctor not available");
			   }
		   }
		 
		   
	  }
	   
   }
	catch(Exception e)
	  {
		  System.out.println(e);
	  }
   }
   
   
   public void popular(List<Doctor> doctorlist, List<Appointment> appointmentlist ) throws JsonParseException, JsonMappingException, IOException
   {
	   appointmentlist=mapper.readValue(new File(appointmentfile), new TypeReference<List<Appointment>>() {});
	   Comparator.reverseOrder();
	   
	   Optional<Appointment> appoint=appointmentlist.stream().sorted(Comparator.comparing(Appointment::getCount).reversed()).findFirst();
	   System.out.println();
	   String name=appoint.get().getDname();
	   System.out.println();
	   System.out.println(name);
	   
   }
   
  
	
}

	


	
