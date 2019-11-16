package com.bridgelabz.fundoo.label.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.fundoo.notes.model.Note;

@Document
public class Label {
	
	@Id
	private String labelId;
	private String labelName;
	private  LocalDateTime labelCreateDate;
	private String userId;
	
	private LocalDateTime now;
	@DBRef(lazy=true)
	private List<Note> notelist;
	
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
	public LocalDateTime getLabelCreateDate() {
		return labelCreateDate;
	}
	public void setLabelCreateDate(LocalDateTime localdate) {
		this.labelCreateDate = localdate;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Label [labelId=" + labelId + ", labelName=" + labelName + ", labelCreateDate=" + labelCreateDate
				+ ", userId=" + userId + ", now=" + now + ", notelist=" + notelist + "]";
	}
	public LocalDateTime getNow() {
		return now;
	}
	public void setNow(LocalDateTime now) {
		this.now = now;
	}
	public List<Note> getNotelist() {
		return notelist;
	}
	public void setNotelist(List<Note> notelist) {
		this.notelist = notelist;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public LocalDateTime getLocalDateTime() {
		return labelCreateDate;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.labelCreateDate = localDateTime;
	}
	public void setModified(LocalDateTime now) {
		this.labelCreateDate=now;
		
	}
	
	public List<Note> getNotes() {
		return null;
	}
}
