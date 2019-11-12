package com.bridgelabz.fundoo.label.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bridgelabz.fundoo.notes.model.Note;

public class Label {
	
	private String labelId;
	private String labelName;
	private String labelCreateDate;
	private String userEmail;
	private String userId;
	private List<Note> note = new ArrayList<Note>();
	private LocalDateTime localDateTime;
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getLabelCreateDate() {
		return labelCreateDate;
	}
	public void setLabelCreateDate(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public List<Note> getNote() {
		return note;
	}
	public void setNote(List<Note> note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "Label [labelId=" + labelId + ", labelName=" + labelName + ", labelCreateDate=" + labelCreateDate
				+ ", userEmail=" + userEmail + ", userId=" + userId + ", note=" + note + ", localDateTime="
				+ localDateTime + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public void setLabelCreateDate(String labelCreateDate) {
		this.labelCreateDate = labelCreateDate;
	}
	public void setModified(LocalDateTime now) {
		this.localDateTime=now;
		
	}
	public List<Label> getNotes() {
		
		return getNotes();
	}
	


}
