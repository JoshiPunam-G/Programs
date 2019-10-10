package com.bridgelabz.commercialdataprocessing;

import Utility.Utility;

public class StockMain {
 
	
	public static void main(String[] args) throws Exception {
  
     StockMethod stockmethod=new StockMethod();
	 stockmethod.read();
	 int counter=0;
	 
	 System.out.println("Enter Customer id");
     int cust_id=Utility.isInteger();
     Utility.isString();
     System.out.println("Enter symbol");
	 String symbol=Utility.isString();
	 System.out.println("Enter no of shares");
	 long user_share=Utility.isLong();
	
	
	 while(counter>=0)
	 { 
		 System.out.println();
		 System.out.println("1.BUY ");
		 System.out.println("2. Sell ");
		 System.out.println("3. SAVE");
		 System.out.println("4. PRINT REPORT");
		 System.out.println("5. EXIT ");
		 System.out.println("Enter Your Choice");
		 int choice=Utility.isInteger();
		
		 switch(choice)
		 {
		 case 1:
			 stockmethod.buy(user_share, symbol,cust_id);
			 break;
		
		 case 2:
			 stockmethod.sell(user_share, symbol,cust_id);
             break;
             
		 case 3:
			 stockmethod.save();
			 break;
			 
		 case 4:
			 stockmethod.printReport();
			 break;
		 
		 case 5:
			 counter=1;
			 System.out.println("EXIT");
			 return;
			 
		 default:
			 counter=1;
			 System.out.println("You Entered Wrong Choice ");
			 return;
		 }
	 }
	 
     }
   }	 
    


