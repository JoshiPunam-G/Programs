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
	private Note note;
	
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
	
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return "Label [labelId=" + labelId + ", labelName=" + labelName + ", labelCreateDate=" + labelCreateDate
			 + ", userId=" + userId + ", note=" + note + ", localDateTime="
				+ labelCreateDate + "]";
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
	
	private List<Note> notelist = new ArrayList<Note>();
	private List<Label> labellist;
	public List<Label> getLabel() {
		
		return labellist;
	}
	public List<Note> getNotes() {
		return notelist;
	}
	


}
