package com.bridgelabz.fundoo.notes.dto;

public class NoteDTO {
	
	private String title;
	 
	private String description;

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

	@Override
	public String toString() {
		return "NoteDTO [title=" + title + ", description=" + description + "]";
	}
}