package com.bridgelabz.commercial.model;

public class Company {
	private String comp_symbol;
	private long available_shares;
	private long price_per_shares;
	
	
	public String getComp_symbol() {
		return comp_symbol;
	}
	public void setComp_symbol(String comp_symbol) {
		this.comp_symbol = comp_symbol;
	}
	public long getAvailable_shares() {
		return available_shares;
	}
	public void setAvailable_shares(long available_shares) {
		this.available_shares = available_shares;
	}
	public long getPrice_per_shares() {
		return price_per_shares;
	}
	public void setPrice_per_shares(long price_per_shares) {
		this.price_per_shares = price_per_shares;
	}
	@Override
	public String toString() {
		return "Company [comp_symbol=" + comp_symbol + ", available_shares=" + available_shares + ", price_per_shares="
				+ price_per_shares + "]";
	}
	

}
