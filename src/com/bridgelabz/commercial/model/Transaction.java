package com.bridgelabz.commercial.model;

public class Transaction {
	private long buy;
	private long sell;
	private long amount;
	private String symbol;
	private String cust_name;
	private String date;
	
	public long getBuy() {
		return buy;
	}
	public void setBuy(long buy) {
		this.buy = buy;
	}
	public long getSell() {
		return sell;
	}
	public void setSell(long sell) {
		this.sell = sell;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Transaction [buy=" + buy + ", sell=" + sell + ", amount=" + amount + ", symbol=" + symbol
				+ ", cust_name=" + cust_name + ", date=" + date + "]";
	}
	
	

}
