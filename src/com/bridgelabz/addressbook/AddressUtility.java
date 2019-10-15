package com.bridgelabz.addressbook;

import java.io.File;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
 static AddressBookModel address=new AddressBookModel();
 
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
	
	public Person addPerson() throws JsonParseException, JsonMappingException, IOException 
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
	 Utility.isString();
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
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public Person edit(String fname) throws JsonParseException, JsonMappingException, IOException
	{
		address=readfile();
		List<Person> person1=address.getPerson();
		Person p1=person1.stream().filter((p) -> fname.equalsIgnoreCase(p.getFirstname())).findAny().orElse(null);
     	System.out.println(p1);
	
		System.out.println();
		Utility.isString();
		System.out.println("Enter New Address ");
		p1.setAddress(Utility.isString());
		
		System.out.println("Enter New City ");
		p1.setCity(Utility.isString());

		System.out.println("Enter New State ");
		p1.setState(Utility.isString());
		
		System.out.println("Enter New Zip");
		p1.setZip(Utility.isLong());
		
		System.out.println("Enter New Mobile No ");
		p1.setPhoneno(Utility.isLong());
		
		System.out.println(p1);
	
		save();
		return p1;
		
	}
	
	/**
	 * Purpose :Method For Sorting ListByFirstName .
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void sortPersonByFirstName() throws JsonParseException, JsonMappingException, IOException
	{
		address=readfile();
		List<Person> person2=address.getPerson();
		List<Person> sortlist=person2.stream().sorted(Comparator.comparing(Person :: getFirstname)).collect(Collectors.toList());
		 sortlist.forEach(System.out::println);
	}
	
	/**
	 * Purpose :Method For Sorting ListByLastName.
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void sortPersonByLastName() throws JsonParseException, JsonMappingException, IOException
	{
		address=readfile();
       List<Person> person3=address.getPerson();
       List<Person> sortlist=person3.stream().sorted(Comparator.comparing(Person :: getLastname)).collect(Collectors.toList());
	}
	
	/**
	 * Purpose : Method for Display Person Address Book .
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	
	public void display() throws JsonParseException, JsonMappingException, IOException
	{
		address=readfile();
		List<Person> person3=address.getPerson();
		person3.forEach(p -> System.out.println(p));
	}
	
	/**
	 * Purpose :Method for checking whether name is found or not.
	 * @param fname
	 * @return
	 */
	public static boolean isFound(String fname)
	{
		List<Person> p=address.getPerson();
		p.stream().filter((p1)->fname.equalsIgnoreCase(p1.getFirstname())).findAny().orElse(null);
		return true;
	}
	
	/**
	 * Purpose :Method for remove person from address book.
	 * @param fName
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void removePerson(String fName) throws JsonParseException, JsonMappingException, IOException
	{
		address=readfile();
		List<Person> person2=address.getPerson();
		Person p2= person2.stream().filter((p)->fName.equalsIgnoreCase(p.getFirstname())).findAny().orElse(null);
		System.out.println(p2);
		if(isFound(fName)==true)
		{
			person2.remove(p2);
		}
	  save();
		
	}
	
	/**
	 * Purpose : Method for Creating New File.
	 */
  public  void createFile() 
  {
	try
	{
		Utility.isString();
		System.out.println("Enter filename ");
		String filename=Utility.isString();
		System.out.println("Enter File Extension");
		String ext=Utility.isString();
		File file=new File("/home/admin106/Documents/jsonfile/"+filename+ext);
		//Scanner sc=new Scanner(file);
		boolean create=file.createNewFile();
		if(create)
		{
			System.out.println("File Created Successfully ...!");
			System.out.println();
		}
		else
		{
			System.out.println("File Already Exist..");
		}
	//	sc.close();
	}catch(IOException e)
	{
		System.out.println("IOException Occured");
		e.printStackTrace();
	}
	
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
