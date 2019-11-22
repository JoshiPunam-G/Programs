package com.bridgelabz.fundoo.notes.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ReminderDTO {
	private String date;
	private String reminder;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReminder() {
		return reminder;
	}
	public void setReminder(String reminder) {
		this.reminder = reminder;
	}
	@Override
	public String toString() {
		return "ReminderDTO [date=" + date + ", reminder=" + reminder + "]";
	}

}
