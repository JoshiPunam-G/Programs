package com.bridgelabz.commercialdataprocessing;

import Utility.Utility;

public class StockMain {
 
	
	public static void main(String[] args) throws Exception {
    

  
     StockMethod stockmethod=new StockMethod();
     stockmethod.read();
     System.out.println("--Buying Shares--");
     System.out.println("Enter symbol");
	 String symbol=Utility.isString();
	 System.out.println("Enter no of shares");
	 long user_share=Utility.isLong();
	// stockmethod.buy(user_share, symbol);
	 stockmethod.sell(user_share, symbol);

    	 }
     }	 
    


