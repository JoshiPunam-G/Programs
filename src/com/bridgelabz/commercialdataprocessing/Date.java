package com.bridgelabz.commercialdataprocessing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

	public static void main(String[] args) 
	{
		 LocalDateTime now = LocalDateTime.now();

	        System.out.println("Before : " + now.getDayOfWeek());

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        String formatDateTime = now.format(formatter);

	        System.out.println("After : " + formatDateTime);
	      
	}

}
