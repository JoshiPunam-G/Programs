/**  Purpose: Reading data from JSON file and calculate weight and price for each inventory.
 *  @author  Punam Joshi
 *  @version 1.0
 *  @since   06-10-2019
 *
 ******************************************************************************/


package com.bridgelabz.stockreport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Utility.Utility;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class StockPortfolio {
	
	public static List<Stock> stock = new ArrayList<Stock>();
	
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
	ObjectMapper mapper=new ObjectMapper();
	BufferedReader reader = new BufferedReader(new FileReader("/home/bridgeit/Desktop/stock.json" ));
	System.out.println("---Stock Report---");
	List<Stock1> stock1=new ArrayList<Stock1>();
	List<Stock2> stock2=new ArrayList<Stock2>();
	List<Stock3> stock3=new ArrayList<Stock3>();
	
	StockModel list=mapper.readValue(reader,StockModel.class );  
    System.out.println(list);
    
    stock1=list.getStock1();
    System.out.println(stock1);
    
    stock2=list.getStock2();
    System.out.println(stock2);
    
    stock3=list.getStock3();
    System.out.println(stock3);
    
    
    int count=0;
    Stock stock=new Stock();
 
    long TotalPriceOfShare;
    long TotalStock;
   
    while(count>=0)
    {
    System.out.println("1.STOCK1");
    System.out.println("2.STOCK2");
    System.out.println("3.STOCK3");
    System.out.println("4.EXIT");
    System.out.println();
    System.out.println("Enter Your Choice ");
    int choice=Utility.isInteger();
    switch(choice)
    {
    case 1:
    	System.out.println("--STOCK1 REPORT--");
    	for(int i=0;i<2;i++)
    	{
    	 System.out.println("Stock Name  :" +list.getStock1().get(i).getStockName());
    	 System.out.println("Number of Shares :"+list.getStock1().get(i).getNoOfShare());
    	 System.out.println("Price per Share :"+list.getStock1().get(i).getPricePerShare());
    	 TotalPriceOfShare=list.getStock1().get(i).getNoOfShare()*list.getStock1().get(i).getPricePerShare();
    	 System.out.println("Total Price of Share "+ TotalPriceOfShare);
    	 System.out.println();
    	
    	}
    	 TotalStock=list.getStock1().get(0).getNoOfShare()*list.getStock1().get(0).getPricePerShare()+list.getStock1().get(1).getNoOfShare()*list.getStock1().get(1).getPricePerShare();
    	 System.out.println("Total Price of Stock1 :"+TotalStock);
    	 System.out.println();
    	 
    	
    break;
    
    case 2:
    	System.out.println("--STOCK2 REPORT--");
    	for(int i=0;i<2;i++)
    	{
    	 System.out.println("Stock Name  :" +list.getStock2().get(i).getStockName());
    	 System.out.println("Number of Shares :"+list.getStock2().get(i).getNoOfShare());
    	 System.out.println("Price per Share :"+list.getStock2().get(i).getPricePerShare());
    	 TotalPriceOfShare=list.getStock2().get(i).getNoOfShare()*list.getStock2().get(i).getPricePerShare();
    	 System.out.println("Total Price of Share "+ TotalPriceOfShare);
    	 System.out.println();
    	}
    	 TotalStock=list.getStock2().get(0).getNoOfShare()*list.getStock2().get(0).getPricePerShare()+list.getStock2().get(1).getNoOfShare()*list.getStock2().get(1).getPricePerShare();
    	 System.out.println("Total Price of Stock2 :"+TotalStock);
    	 System.out.println();
    	 
    	
    	break;
    	
    case 3:
    	System.out.println("--STOCK3 REPORT--");
    	for(int i=0;i<2;i++)
    	{
    	 System.out.println("Stock Name  :" +list.getStock3().get(i).getStockName());
    	 System.out.println("Number of Shares :"+list.getStock3().get(i).getNoOfShare());
    	 System.out.println("Price per Share :"+list.getStock3().get(i).getPricePerShare());
    	 TotalPriceOfShare=list.getStock3().get(i).getNoOfShare()*list.getStock3().get(i).getPricePerShare();
    	 System.out.println("Total Price of Share "+ TotalPriceOfShare);
    	 System.out.println();
    	}
    	 TotalStock=list.getStock3().get(0).getNoOfShare()*list.getStock3().get(0).getPricePerShare()+list.getStock3().get(1).getNoOfShare()*list.getStock3().get(1).getPricePerShare();
    	 System.out.println("Total Price of Stock3 :"+TotalStock);
    	 System.out.println();
    	 break;
    	
    case 4:
    	System.out.println("EXIT");
    	count=1;
        return;
        
    default:
        System.out.println("You Entered Wrong choice");
        count=1;
        return;
          
	}
   }
}

}
