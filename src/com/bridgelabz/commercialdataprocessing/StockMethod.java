package com.bridgelabz.commercialdataprocessing;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.bridgelabz.commercial.model.Company;
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
	public  StockModel read() throws Exception{

		ObjectMapper mapper=new ObjectMapper();
		File file = new File("/home/bridgeit/Desktop/account.json" );
		StockModel list = mapper.readValue(file, StockModel.class);
	    System.out.println(list); 
//	    list.getCustomer(); 
//	    list.getCompany();
		return list;
		
	}
	
	
	
/**
 * Purpose: Method For Buying shares from comapny 
 * @param company
 * @param customer
 * @throws Exception 
 * @throws  
 * @throws JsonMappingException 
 */
	public void  buy(long user_share,String symbol) throws Exception
	{
	      list=read();
          for(int i=0;i<2;i++)
          {
        	  if(symbol.equals(list.getCompany().get(i).getComp_symbol()))
        	  { 
        		  long numberOfShare=list.getCompany().get(i).getAvailable_shares()+user_share;
        		  System.out.println("UPDATED" +list);
        		  System.out.println("Buying shares  "+numberOfShare);
        		  list.getCompany().get(i).setAvailable_shares(numberOfShare);
        		  
        		  System.out.println("--Updated Company Shares Price--");
        		  long Total_compShare_Price=list.getCompany().get(i).getAvailable_shares()*list.getCompany().get(i).getPrice_per_shares();
        		  System.out.println("Total Share Price of Company :"+Total_compShare_Price);
        		  list.getCompany().get(i).setPrice_per_shares(Total_compShare_Price);
        		  
        		  
        	  }
          }
	      save();
	      
      	}
     
          
      	
	/**
	 * Purpose :Method for selling shares to customer 
	 * @param company
	 * @param customer
	 * @throws Exception 
	 */
	public  void sell(long user_share,String symbol) throws Exception {
		 
	      list=read();
	      
	      
	      for(int i=0;i<2;i++)
          {
        	  if(symbol.equals(list.getCompany().get(i).getComp_symbol()))
        	  {
        		  long numberOfShare=list.getCompany().get(i).getAvailable_shares()-user_share;
        		  System.out.println("Buying shares  "+numberOfShare);
        		  list.getCompany().get(i).setAvailable_shares(numberOfShare);
        		  
        		  System.out.println("--Updated Comapny Shares Price --");
        		  long Total_compShare_Price=list.getCompany().get(i).getAvailable_shares()*list.getCompany().get(i).getPrice_per_shares();
        		  System.out.println("Total Share Price of Company :"+Total_compShare_Price);
        		  list.getCompany().get(i).setPrice_per_shares(Total_compShare_Price);
        		  
        	  }
          }
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
			mapper.writeValue(new File("/home/bridgeit/Desktop/account.json" ), list);
			System.out.println("\n Saved");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		       
	}
	
	
	public static void printReport() {
	
		
		
		
	}
	
	
	public static long valueOf(List<Company> company) {
		return 0;
	}

	
}
