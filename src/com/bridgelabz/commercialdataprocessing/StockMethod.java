package com.bridgelabz.commercialdataprocessing;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.ParseException;

import com.bridgelabz.commercial.model.Company;
import com.bridgelabz.commercial.model.Customer;
import com.bridgelabz.commercial.model.StockModel;
import com.bridgelabz.commercial.model.transaction;

public class StockMethod {
static StockModel list;

LocalDateTime now = LocalDateTime.now();
 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  Date date=new Date();
/**
 * Purpose :Setting date for transaction .
 */

public void setDate()
{
	
    System.out.println("Day : " + now.getDayOfWeek());
   
    String formatDateTime = now.format(formatter);
    System.out.println("Teansaction Date & Time : " + formatDateTime);
    
//    for(int i=0;i<2;i++)
//    {
//       String t_id=list.getTransaction().get(i).getTransaction_id()+formatDateTime;
//       System.out.println(t_id);
//    }
//	
}


/**
 * Purpose : Method for reading json file and store file into list.
 * @return
 * @throws JsonParseException
 * @throws JsonMappingException
 * @throws IOException
 */
	public  StockModel read() throws Exception{

		ObjectMapper mapper=new ObjectMapper();
		File file = new File("/home/admin106/Documents/jsonfile/stockaccount.json" );
		StockModel list = mapper.readValue(file, StockModel.class);
	    //System.out.println(list); 
	        
	return list;
		
	}
	
	
	
	public void buy1(long user_share ,String symbol,int cust_id) throws Exception
	{
		list=read();
		List<Customer> customer=new ArrayList<>();
		list.getCustomer();
      //  List<String> result=customer.stream().filter((cust)symbol.equals(symbol));
		
	}
	
/**
 * Purpose: Method For Buying shares from comapny 
 * @param company
 * @param customer
 * @throws Exception 
 * @throws  
 * @throws JsonMappingException 
 */
	public void  buy(long user_share,String symbol,int cust_id) throws Exception
	{
	      list=read();
	      
          for(int i=0;i<2;i++)
          {
        	  if(symbol.equals(list.getCompany().get(i).getComp_symbol()))
        	  { 
        		  //if(cust_id==list.getCustomer().get(i).getCust_id())
        		  
        		  /* get the customer id  who buying shares  */
        		  int customer_id=list.getCustomer().get(i).getCust_id();
        		  System.out.println("Customer id Who Buying Shares From Company: "+customer_id);
        		  
        		  /* get the customer name who buying shares  */
        		  String customer_name=list.getCustomer().get(i).getCust_name();
        		  System.out.println("Customer name Who Buying Shares From Company: "+customer_name);
        		  
        		  int id=list.getCustomer().get(i).getCust_id();
        		  list.getCustomer().get(i).setCust_id(id);
        		  
        		  /* calculating how many shares buy customer */
        		  long numberOfShare=list.getCustomer().get(i).getShares()+user_share;
        		  System.out.println("UPDATED" +list);
        		  System.out.println("Buying shares  "+numberOfShare);
        		  
        		  /*  set the available share of customer */
        		  list.getCustomer().get(i).setShares(numberOfShare);
        		  
        		 System.out.println();
        		 /* Calculating initial balance of customer */
        		 long initial_balance=list.getCustomer().get(i).getBalance();
        		 System.out.println(initial_balance);
       		  
        		  /* calculating shares price of company */
        		  long Total_compShare_Price=list.getCompany().get(i).getPrice_per_shares()*list.getCompany().get(i).getAvailable_shares();
        		  System.out.println("Total Share Price of Company :"+Total_compShare_Price);
        		 
        		  
        		  /* set price of share of company */
        		  list.getCompany().get(i).setPrice_per_shares(Total_compShare_Price);
        		  
        		 
    	       }       
          }
          //setDate();
          
          List<transaction> transaction=new ArrayList<transaction>();
          transaction=list.getTransaction();
          list.getTransaction().get(0).getTransaction_id();
          list.getCustomer().get(0).setCust_id(cust_id);
          
          
          transaction  t=transaction.stream().findAny().orElse(null);
          String d= now.format(formatter);
          System.out.println(d);
          t.setFormatDateTime(d);
          
         
         
	       save(); 
	      
      	}
     
          
      
	/**
	 * Purpose :Method for selling shares to customer 
	 * @param company
	 * @param customer
	 * @throws Exception 
	 */
	public  void sell(long user_share,String symbol,int cust_id) throws Exception {
		 
	      list=read();
	      
	      
	      for(int i=0;i<2;i++)
          {
        	  if(symbol.equals(list.getCompany().get(i).getComp_symbol()))
        	  {
        		  if(cust_id==list.getCustomer().get(i).getCust_id())
        		  {
        		  /* get the customer id  who buying shares  */
            	  int customer_id=list.getCustomer().get(i).getCust_id();
            	  System.out.println("Customer id Who Buying Shares From Company: "+customer_id);
            		  
            	  /* get the customer name who buying shares  */
            	  String customer_name=list.getCustomer().get(i).getCust_name();
            	  System.out.println("Customer name Who Buying Shares From Company: "+customer_name);
            	  
            	  /* calculation of selling shares */
        		  long numberOfShare=list.getCustomer().get(i).getShares()-user_share;
        		  System.out.println("Selling shares  "+numberOfShare);
        		  
        		  /* updated list of */
        		  list.getCustomer().get(i).setShares(numberOfShare);
        		  
        		  System.out.println("--Updated Comapny Shares Price --");
        		  
        		  /* calculating total share price of company */
        		  long Total_compShare_Price=list.getCompany().get(i).getPrice_per_shares()*list.getCompany().get(i).getAvailable_shares();
        		  System.out.println("Total Share Price of Company :"+Total_compShare_Price);
        		  
        		  /*set the updated total price of company */
        		  list.getCompany().get(i).setPrice_per_shares(Total_compShare_Price);
        		  }
        	  }
          }
	      setDate();
	     save();
	}
	
	/**
	 * Purpose : Method for save details of Stock Report .
	 * @param filename
	 * @param name
	 */
	
	public void save() {

		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("/home/admin106/Documents/jsonfile/stockaccount.json" ), list);
			System.out.println("\n Report Saved");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		       
	}
	
	
	public  void printReport() throws Exception {
	list=read();

	}
}
