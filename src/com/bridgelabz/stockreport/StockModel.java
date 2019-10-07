package com.bridgelabz.stockreport;

import java.util.List;

public class StockModel {
   List<Stock1>  stock1;
   List<Stock2> stock2;
   List<Stock3> stock3;
public List<Stock1> getStock1() {
	return stock1;
}
public void setStock1(List<Stock1> stock1) {
	this.stock1 = stock1;
}
public List<Stock2> getStock2() {
	return stock2;
}
public void setStock2(List<Stock2> stock2) {
	this.stock2 = stock2;
}
public List<Stock3> getStock3() {
	return stock3;
}
public void setStock3(List<Stock3> stock3) {
	this.stock3 = stock3;
}
@Override
public String toString() {
	return "StockModel [stock1=" + stock1 + ", stock2=" + stock2 + ", stock3=" + stock3 + "]";
}
   

   
}
