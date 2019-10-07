package com.bridgelabz.commercialdataprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import com.bridgelabz.commercial.model.Company;
import com.bridgelabz.commercial.model.Customer;
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

    	 }
     }	 
    


