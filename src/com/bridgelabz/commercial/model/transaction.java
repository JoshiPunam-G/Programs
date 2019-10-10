package com.bridgelabz.commercial.model;

public class transaction {
	private int transaction_id;
	private String formatDateTime;
	
	
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getFormatDateTime() {
		return formatDateTime;
	}
	public void setFormatDateTime(String formatDateTime) {
		this.formatDateTime = formatDateTime;
	}
	@Override
	public String toString() {
		return "transaction [transaction_id=" + transaction_id + ", formatDateTime=" + formatDateTime + "]";
	}
	
	


}
