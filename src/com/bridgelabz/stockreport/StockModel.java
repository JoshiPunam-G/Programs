package com.bridgelabz.stockreport;

import java.util.List;

public class StockModel {
   List<Stock1>  stock1;
  // List<Stock2> stock2;
  // List<Stock3> stock3;
public List<Stock1> getStock1() {
	return stock1;
}
public void setStock1(List<Stock1> stock1) {
	this.stock1 = stock1;
}

@Override
public String toString() {
	return "StockModel [stock1=" + stock1 + "]";
}
   

   
}
