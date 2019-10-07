package com.bridgelabz.commercial.model;

import java.util.List;

public class StockModel {
	List<Customer> customer;
	List<Company> company;
	List<Transaction> transaction;
	
	@Override
	public String toString() {
		return "StockModel [customer=" + customer + ", company=" + company + ", transaction=" + transaction + "]";
	}
	public List<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	public List<Company> getCompany() {
		return company;
	}
	public void setCompany(List<Company> company) {
		this.company = company;
	}
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
	
	

}
