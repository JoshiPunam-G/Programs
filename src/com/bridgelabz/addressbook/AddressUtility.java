package com.bridgelabz.addressbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.bridgelabz.addressbook.model.AddressBookModel;
import com.bridgelabz.addressbook.model.Person;

import Utility.Utility;

public class AddressUtility {
 static	AddressBookModel list;
 static Person p;
 static List<Person> person=new ArrayList<>();
 ObjectMapper mapper=new ObjectMapper();
 AddressBookModel address=new AddressBookModel();
 
	/**
	 * Purpose : Method to reading file .
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	public  AddressBookModel readfile() throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper=new ObjectMapper();
		File file=new File("/home/admin106/Documents/jsonfile/address.json");
		AddressBookModel list=mapper.readValue(file, AddressBookModel.class);
		System.out.println(list);
		return list;
		
	}
	/**
	 * Purpose : Method for adding persons into addressbook .
	 * @param firstname
	 * @param lastname
	 * @param phoneno
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	public   Person addPerson() throws JsonParseException, JsonMappingException, IOException 
	{
	 address=readfile();
    // System.out.println(address);
	 

		if(!address.getPerson().isEmpty())
		{
			person.addAll(address.getPerson());
		//	System.out.println(person);
			save();
		}
		
		for(Person p1:person)
		{
			System.out.println(p1.toString());
		}
		
	 //List<Person> person;
	
	 Person p=new Person(); 
	 System.out.println("Enter First name ");
	 p.setFirstname(Utility.isString());
	 System.out.println("Enter Last name ");
	 p.setLastname(Utility.isString());
	 System.out.println("Enter Address ");
	 p.setAddress(Utility.isString());
	 System.out.println("Enter City ");
	 p.setCity(Utility.isString());
	 System.out.println("Enter  State ");
	 p.setState(Utility.isString());
	 System.out.println("Enter zip code");
	 p.setZip(Utility.isLong());
	 System.out.println("Enter Mobile Number");
	 p.setPhoneno(Utility.isLong());
	 System.out.println(p);
   	 person.add(p);
   
   	address.setPerson(person);
   	save(); 
	return p;
	}
	
	
	/**
	 * Purpose: Method for Editing Person Information .
	 * @param fname
	 */
	public void edit(String fname)
	{
		List<Person> person1=address.getPerson();
		Person p1=person1.stream().filter((p) -> fname.equalsIgnoreCase(p.getFirstname())).findAny().orElse(null);
		System.out.println(p1);
		
		System.out.println("Here Edit Your Information Without First Name ");
		System.out.println("Enter New Address ");
		String newadd=Utility.isString();
		p.setAddress(newadd);
		
		System.out.println("Enter New City ");
		String newcity=Utility.isString();
		p.setCity(newcity);

		System.out.println("Enter New State ");
		String newstate=Utility.isString();
		p.setState(newstate);
		
		System.out.println("Enter New Zip");
		long newzip=Utility.isInteger();
		p.setZip(newzip);
		
		System.out.println("Enter New Mobile No ");
		long newmob=Utility.isLong();
		p.setPhoneno(newmob);
		
	}
	
	/**
	 * Purpose :Method for save data .
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	public void save() throws JsonParseException, JsonMappingException, IOException
	{
	  	
		ObjectMapper mapper=new ObjectMapper();
		try {
			mapper.writeValue(new  File("/home/admin106/Documents/jsonfile/address.json"),address);
			System.out.println("Saved");
		}  catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
