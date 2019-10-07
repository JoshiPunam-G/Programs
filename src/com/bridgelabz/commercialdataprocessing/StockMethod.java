package com.bridgelabz.commercialdataprocessing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import Utility.Utility;

import com.bridgelabz.commercial.model.Company;
import com.bridgelabz.commercial.model.Customer;
import com.bridgelabz.commercial.model.StockModel;

public class StockMethod {
static StockModel list;
/**
 * Purpose : Method for reading json file and store file into list.
 * @return
 * @throws JsonParseException
 * @throws JsonMappingException
 * @throws IOException
 */
	public static StockModel read() throws JsonParseException, JsonMappingException, IOException
	{

		ObjectMapper mapper=new ObjectMapper();
		File file = new File("/home/bridgeit/Desktop/account.json" );
		StockModel list = mapper.readValue(file, StockModel.class);
	    System.out.println(list); 
	    list.getCustomer(); 
	    list.getCompany();
		return list;
		
	}
	
	
	
/**
 * Purpose: Method For Buying shares from comapny 
 * @param company
 * @param customer
 * @throws IOException 
 * @throws  
 * @throws JsonMappingException 
 */
	public static void buy(long cust_amount,String symbol) throws IOException  {
	      StockAccount stock=new StockAccount();
	      StockModel list;
	      list=read();
          //int flag=0;
	     // long company_available_amount;
	    
        for(int i=0;i<2;i++)
       {
           if(symbol.equals(list.getCompany().get(i).getComp_symbol()))
           {
        	/* calculating price of company shares */
        	long Company_Amount=list.getCompany().get(i).getAvailable_shares()*list.getCompany().get(i).getPrice_per_shares();
           	System.out.println("Company Initial Balance :"+Company_Amount);
           
           	/*customer buying shares from company*/
           	long company_available_amount=Company_Amount+cust_amount;
           	System.out.println("Add stock to account :"+company_available_amount);
           	
        	System.out.println("----");
        	
        	/*calcute initial balance of customer*/
      	    long initial_balance=list.getCustomer().get(i).getBalance();
       	    System.out.println("Initial Balance of Customer "+initial_balance);
       	    
       	    /*calculate available balance of */
       	    long  cust_availablebalance=list.getCustomer().get(i).getBalance()-cust_amount;
            System.out.println("Available Balance Of Customer After Buying Shares from Company :"+ cust_availablebalance);  
     
           	}
           }     
//            String choice1="yes";
//            System.out.println("Do You Want to Save this Transaction");
//            String choice=Utility.isString();
//            if(choice==choice1)
//            {
//            	StockMethod.save(choice, choice1);
//            }
//            
//        
         }
           
          
      	
	/**
	 * Purpose :Method for selling shares to customer 
	 * @param company
	 * @param customer
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void sell(long cust_amount,String symbol) throws JsonParseException, JsonMappingException, IOException {
		 StockModel list;
	      list=read();
		for(int i=0;i<2;i++)
		{

	           if(symbol.equals(list.getCompany().get(i).getComp_symbol()))
	           {
	        	/* calculating price of company shares */
	        	long Company_Amount=list.getCompany().get(i).getAvailable_shares()*list.getCompany().get(i).getPrice_per_shares();
	           	System.out.println("Company Initial Balance :"+Company_Amount);
	           
	           	/*customer buying shares from company*/
	           	long company_available_amount=Company_Amount-cust_amount;
	           	System.out.println("Substract stock from Account :"+company_available_amount);
	           	
	        	System.out.println("----");
	        	
	        	/*calcute initial balance of customer*/
	      	    long initial_balance=list.getCustomer().get(i).getBalance();
	       	    System.out.println("Initial Balance of Customer "+initial_balance);
	       	    
	       	    /*calculate available balance of */
	       	    long  cust_availablebalance=list.getCustomer().get(i).getBalance()+cust_amount;
	            System.out.println("Available Balance Of Customer After buying Shares from Company :"+ cust_availablebalance);  
	     
	           	}
		}
		
//		  String choice1="yes";
//          System.out.println("Do You Want to Save this Transaction");
//          String choice=Utility.isString();
//          if(choice==choice1)
//          {
//          	StockMethod.save(choice, choice1);
//          }
          
		
	}
	
	/**
	 * Purpose : Method for save details of Stock Report .
	 * @param filename
	 * @param name
	 */
	
	public static void save(String stockaccount,String name) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("/home/bridgeit/Desktop/account.json" ), list);
			System.out.println("\n Saved");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		
//		 String save="YES";
//	     String cancel="NO";
//	     
	      
	}
	
	
	public static void printReport() {
	
		
		
		
	}
	
	
	public static long valueOf(List<Company> company) {
		return 0;
	}

	
}
