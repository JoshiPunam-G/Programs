package com.bridgelabz.addressbook;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;


import Utility.Utility;

public class AddressBookMain {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		AddressUtility add=new AddressUtility();
		System.out.println("Enter first name");
		String fname=Utility.isString();
		 add.readfile();
		 add.addPerson();
		// add.save();

	}

}
