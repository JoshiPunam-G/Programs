package com.bridgelabz.stockreport;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Stock implements Serializable {

	private String stockName;
	private int noOfShare;
	private long pricePerShare;
	
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public int getNoOfShare() {
		return noOfShare;
	}
	public void setNoOfShare(int noOfShare) {
		this.noOfShare = noOfShare;
	}
	public long getPricePerShare() {
		return pricePerShare;
	}
	public void setPricePerShare(long pricePerShare) {
		this.pricePerShare = pricePerShare;
	}
	@Override
	public String toString() {
		return "Stock [stockName=" + stockName + ", noOfShare=" + noOfShare + ", pricePerShare=" + pricePerShare + "]";
	}
	
	
	
}
