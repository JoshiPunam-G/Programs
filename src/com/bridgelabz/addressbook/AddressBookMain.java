package com.bridgelabz.addressbook;

import java.io.IOException;


import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;


import Utility.Utility;

public class AddressBookMain {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		AddressUtility add=new AddressUtility();
		
		 //add.addPerson();
		//add.removePerson(fName);

		 int counter=0;
		 while(counter>=0)
		 {
			 System.out.println("1. Add Person To AddressBook ");
			 System.out.println("2. Edit Person Information ");
			 System.out.println("3. Remove Person From AddressBook");
			// System.out.println("4. Sort Entries in AddressBook By FirstName");
			 System.out.println("4. Sort Entries in AddressBook By LastName");
			 System.out.println("5. Create New File");
			 System.out.println();
			 System.out.println("Enter Your Choice ");
			 int choice=Utility.isInteger();
		 switch(choice)
		 {
		 case 1:
			 add.readfile();
			 add.addPerson();
			 break;
			 
		 case 2:
			 //System.out.println();
			 Utility.isString();
			 System.out.println("***To Edit Person Information Enter Person's First Name*** ");
			 String fname=Utility.isString();
			 add.edit(fname);
			 break;
			 
		 case 3:
			 Utility.isString();
			 System.out.println("Enter Person First Name to Remove From AddressBook ");
			 String fName=Utility.isString();
			 add.removePerson(fName);
			 break;
		
		 case 4:
			 System.out.println("***Sort Entries In AddressBook by LastName*** ");
			 add.sortPersonByLastName();
			 break;
		
		 case 5:
			 System.out.println("***Create New File***");
			 add.createFile();
			 break;
			 
		 case 6:
			 System.out.println("EXIT");
			 counter=1;
			 return;
			 
	    default:
	    	counter=1;
	    	System.out.println("You Enterd Wrong Choice");
	    	return;
		 }
		 }
		 

	}

}
