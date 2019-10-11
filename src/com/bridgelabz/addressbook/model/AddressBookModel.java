package com.bridgelabz.addressbook.model;

import java.util.List;

public class AddressBookModel {
   List<Person> person;
   //List<Address> address;

public List<Person> getPerson() {
	return person;
}

public void setPerson(List<Person> person) {
	this.person = person;
}

@Override
public String toString() {
	return "AddressBookModel [person=" + person + "]";
}




}
