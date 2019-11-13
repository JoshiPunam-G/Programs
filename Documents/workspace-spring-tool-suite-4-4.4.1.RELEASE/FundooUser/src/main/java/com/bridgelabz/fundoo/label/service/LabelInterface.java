package com.bridgelabz.fundoo.label.service;

import java.util.List;

import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.response.Response;

public interface LabelInterface {
	Response createLabel(LabelDTO labeldto, String Token) throws UserServiceException;

	Response deleteLabel(String token, String labelId) throws UserServiceException;

	Response addlabeltoNote(String labelId, String token, String noteId) throws UserServiceException;

	Response updateLabel(LabelDTO labeldto, String token,String labelId) throws UserServiceException;
	
	public List<LabelDTO> getLabelsOfNote(String token, String noteId) throws UserServiceException ;
	
	public List<NoteDTO> getNotesOfLabel(String token, String labelId) throws UserServiceException;
	
	public List<Label> getAllLabelFromUser(String token) throws UserServiceException;
}
