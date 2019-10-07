package com.bridgelabz.commercialdataprocessing;

public class StockAccount {

	private String cust_name;
	private String address;
	private String company_name;
	private String date_of_birth;
	
	@Override
	public String toString() {
		return "StockAccount [cust_name=" + cust_name + ", address=" + address + ", company_name=" + company_name
				+ ", date_of_birth=" + date_of_birth + "]";
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	
	
}
