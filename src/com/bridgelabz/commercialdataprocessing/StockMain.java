package com.bridgelabz.commercialdataprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.bridgelabz.commercial.model.Company;
import com.bridgelabz.commercial.model.Customer;
import com.bridgelabz.commercial.model.StockModel;
import com.bridgelabz.commercial.model.Transaction;
import com.bridgelabz.inventory.Inventory;

import Utility.Utility;

public class StockMain {
 
	public static List<Inventory> inventoryList = new ArrayList<Inventory>();
	private static String stockaccount;
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
    
	List<Customer> customer=new ArrayList<Customer>();
	List<Company> company=new ArrayList<Company>();
	List<Transaction> transaction=new ArrayList<Transaction>();
  
     StockMethod stockmethod=new StockMethod();
     stockmethod.read();
     System.out.println("--Buying Shares--");
     System.out.println("Enter symbol");
	 String symbol=Utility.isString();
	 System.out.println("Enter Amount");
     long cust_amount=Utility.isLong();
   	 //stockmethod.buy(cust_amount,symbol);
     StockMethod.sell(cust_amount, symbol);
     StockMethod.save(symbol, stockaccount);
//     
//     int count=0;
//     System.out.println("1. Buy");
//     System.out.println("2. Sell");
//     System.out.println("3. Save");
//     System.out.println("4. Print Report");
//     System.out.println("5. Exit");
//     System.out.println("Enter Your Choice");
//     int choice=Utility.isInteger();
//     while(count>0)
//     {
//    	
//    	 switch(choice)
//    	 {
//    	 case 1:
//    		 stockmethod.buy(cust_amount, symbol);
//    		 break;	 
//    	 case 2:
//    		 stockmethod.sell(cust_amount, symbol);
//    		 break;
//    	 case 3:
//    		 stockmethod.save(stockaccount, symbol);
//    		 break;
//    	 case 4:
//    		 stockmethod.printReport();
//    		 break;
//    	 case 5:
//    		System.out.println("Exit");
//    		 count=1;
//    		 return;
//    	default:
//    		System.out.println("You Entered Wrong Choice");
//    		count=1;
//    		return;
//	}
    	 }
     }	 
    


