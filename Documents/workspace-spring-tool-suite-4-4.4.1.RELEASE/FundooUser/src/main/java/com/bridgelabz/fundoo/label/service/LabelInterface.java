package com.bridgelabz.fundoo.label.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.response.Response;

public interface LabelInterface {
	Response createLabel(LabelDTO labeldto, String Token) throws UserServiceException;

	Response deleteLabel(String token, String labelId) throws UserServiceException;

	Response addlabeltoNote(String labelId, String token, String noteId) throws UserServiceException;

	Response updateLabel(LabelDTO labeldto, String token,String labelId) throws UserServiceException;
	
	public Response getLabelsOfNote(String token, String noteId) throws UserServiceException ;
	
	public Response getNotesOfLabel(String token, String labelId) throws UserServiceException;
	
	public Response getAllLabelFromUser(String token ,String email) throws UserServiceException;
	
	public Response getUserNoteAndLabel(String token , String noteId ,String labelId,String email) throws UserServiceException;
	
	public Response addnotetoUser(String token ,String noteId,String email)throws UserServiceException;
	
	public Response uploadFile(@RequestParam("file")MultipartFile file )throws IOException ;
	
	public Response  deleteFile(@RequestParam("file")MultipartFile file )throws IOException;
}
