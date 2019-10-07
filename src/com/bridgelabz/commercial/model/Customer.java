package com.bridgelabz.commercial.model;

public class Customer {
	private int cust_id;
	private String cust_name;
	private long shares;
	private long balance;
	
	
	
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public long getShares() {
		return shares;
	}
	public void setShares(long shares) {
		this.shares = shares;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", shares=" + shares + ", balance="
				+ balance + "]";
	}
	
	

}
