package com.bridgelabz.fundoo.notes.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.notes.service.NoteService;
import com.bridgelabz.fundoo.response.Response;

@RequestMapping
@RestController
public class NoteController {
	@Autowired
	private NoteService noteservice;
	/**
	 * Purpose : API for CreateNote
	 * @param notedto
	 * @param token
	 * @return
	 * @throws UserServiceException
	 */
	@PostMapping("/createnote")
	public Response createNote(@RequestBody NoteDTO notedto, @RequestHeader String token) throws UserServiceException {

		return noteservice.createNote(notedto, token);
	}
	/**
	 * Purpose :API for Update Note
	 * @param notedto
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException
	 */
	@PostMapping("/updatenote")
	Response update(@RequestBody NoteDTO notedto,@RequestHeader  String noteId) throws UserServiceException
	{
		return noteservice.update(notedto, noteId);			
	}
	
	/**
	 * Purpose : API for Archieve
	 * @param notedto
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException
	 */
	@PutMapping("/archieve/{noteId}")
	public Response setArchieveUnarchieve(String noteId,  @RequestHeader  String token) throws UserServiceException
	{
		return noteservice.setArchieveUnarchieve(noteId, token);
	}
	
	/**
	 * Purpose :API for PinUnpin
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException 
	 */
	@PutMapping("/pin/{noteId}")
	public Response setPinUnpin(String noteId, @RequestHeader String token) throws UserServiceException
	{
		return noteservice.setPinUnpin(noteId, token);
	}
	
	
	/**
	 * Purpose :API For Trash 
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException 
	 */
	
	@PostMapping("/trash/{noteId}")
	public Response setTrashUntrash(String noteId,  @RequestHeader String token) throws UserServiceException {
		return noteservice.setTrashUntrash(noteId, token);
	}
	/**
	 * Purpose :API for DeleteTRash
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException 
	 */
	 @DeleteMapping("delete/{noteId}")
	 public Response deleteinTrash(String noteId,  @RequestHeader String token) throws UserServiceException
	 {
		 return noteservice.deleteinTrash(noteId, token);
	 }
	/**
	 * Purpose: API For Retrieve All Note
	 * @return
	 */
	
	@GetMapping("/getAllNote")
	public List<Note> getAllNote() {
		return noteservice.getAllNote();
	}

}
