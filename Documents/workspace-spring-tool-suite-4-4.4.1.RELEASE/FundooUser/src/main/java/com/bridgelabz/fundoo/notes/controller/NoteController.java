/**
 * Purpose : Implementation of Controller for Accessing Services implemented in service class .
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   11-11-2019  
 */
package com.bridgelabz.fundoo.notes.controller;
import java.util.List;

import javax.validation.constraints.PastOrPresent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.exception.UserServiceException;
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
	public  ResponseEntity<Response> createNote(@RequestBody NoteDTO notedto, @RequestHeader String token) throws UserServiceException {
         
		Response response=noteservice.createNote(notedto, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	/**
	 * Purpose :API for Update Note
	 * @param notedto
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException
	 */
	@PutMapping("/updatenote")
	public ResponseEntity<Response> update(@RequestBody NoteDTO notedto,@RequestHeader String noteId) throws UserServiceException
	{
		Response response=noteservice.update(notedto, noteId);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Purpose : API for Archieve
	 * @param notedto
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException
	 */
	@PutMapping("/archieve")
	public ResponseEntity<Response> setArchieveUnarchieve(@RequestHeader String noteId,  @RequestHeader  String token) throws UserServiceException
	{
		Response response=noteservice.setArchieveUnarchieve(noteId, token);
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * Purpose :API for PinUnpin
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException 
	 */
	@PutMapping("/pin")
	public ResponseEntity<Response> setPinUnpin( @RequestHeader String noteId, @RequestHeader String token) throws UserServiceException
	{
		Response response=noteservice.setPinUnpin(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	
	/**
	 * Purpose :API For Trash 
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException 
	 */
	
	@PostMapping("/trash")
	public ResponseEntity<Response> setTrashUntrash(@RequestHeader String noteId,  @RequestHeader String token) throws UserServiceException {
		Response response=noteservice.setTrashUntrash(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	/**
	 * Purpose :API for DeleteTRash
	 * @param noteId
	 * @param token
	 * @return
	 * @throws UserServiceException 
	 */
	 @DeleteMapping("/deleteTrash")
	 public ResponseEntity<Response> deleteinTrash( @RequestHeader String noteId,  @RequestHeader String token) throws UserServiceException
	 {
		 Response response=noteservice.deleteinTrash(noteId, token);
		 return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
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
