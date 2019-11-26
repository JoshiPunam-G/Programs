package com.bridgelabz.fundoo.notes.model;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.notes.dto.ReminderDTO;
@Document
public class Note {
	@Id
	private String noteId;
	private String title;
	private String description;
	private  LocalDateTime createDate;
	private boolean statusArchieve;
	private  boolean statusPinUnpin;
	private boolean statusTrashUntrash;
	private String userId;
	private LocalDateTime now;
	//private String reminder;
    private List<ReminderDTO> reminderlist;
	private List<Label> labellist ;
//	public LocalDateTime getReminder() {
//		return reminder;
//	}
//	public void setReminder(LocalDateTime reminder) {
//		this.reminder = reminder;
//	}
	

	//	public String getReminder() {
//		return reminder;
//	}
//	public void setReminder(String reminder) {
//		this.reminder = reminder;
//	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Note(String noteId, String title, String description, LocalDateTime createDate, boolean statusArchieve,
			boolean statusPinUnpin, boolean statusTrashUntrash, String userId) {
		super();
		this.noteId = noteId;
		this.title = title;
		this.description = description;
		this.createDate = createDate;
		this.statusArchieve = statusArchieve;
		this.statusPinUnpin = statusPinUnpin;
		this.statusTrashUntrash = statusTrashUntrash;
		this.userId = userId;
	}

	public Note() {
		// TODO Auto-generated constructor stub
	}

	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime localDateTime) {
		this.createDate = localDateTime;
	}
	public boolean isStatusArchieve() {
		return statusArchieve;
	}
	public void setStatusArchieve(boolean statusArchieve) {
		this.statusArchieve = statusArchieve;
	}
	public boolean isStatusPinUnpin() {
		return statusPinUnpin;
	}
	public void setStatusPinUnpin(boolean statusPinUnpin) {
		this.statusPinUnpin = statusPinUnpin;
	}
	public boolean isStatusTrashUntrash() {
		return statusTrashUntrash;
	}
	public void setStatusTrashUntrash(boolean statusTrashUntrash) {
		this.statusTrashUntrash = statusTrashUntrash;
	}
	
	
	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", description=" + description + ", createDate="
				+ createDate + ", statusArchieve=" + statusArchieve + ", statusPinUnpin=" + statusPinUnpin
				+ ", statusTrashUntrash=" + statusTrashUntrash + ", userId=" + userId + ", now=" + now + ", labellist="
				+ labellist + "]";
	}
	public void setModified(LocalDateTime now) {
		this.now=now;
		
	}
	
	public boolean isTrash() {
	
		return false;
	}
	//@DBRef(lazy=true)
	
	public List<Label> getLabellist() {
		return labellist;
	}
	public void setLabellist(List<Label> labellist) {
		this.labellist = labellist;
	}
	public boolean isPresent() {
		return false;
	}

	public List<ReminderDTO> getReminderlist() {
		return reminderlist;
	}

	public void setReminderlist(List<ReminderDTO> reminderlist) {
		this.reminderlist = reminderlist;
	}
	
}
