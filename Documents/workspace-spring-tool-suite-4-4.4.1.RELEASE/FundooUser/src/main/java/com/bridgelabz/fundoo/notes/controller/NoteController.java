/**
 * Purpose : Implementation of Controller for Accessing Services implemented in service class .
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   11-11-2019  
 */
package com.bridgelabz.fundoo.notes.controller;
import java.util.List;
import org.slf4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.Utility.GlobalResource;
import com.bridgelabz.fundoo.Utility.TokenUtil;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.notes.service.NoteService;
import com.bridgelabz.fundoo.response.Response;
@RequestMapping("/note")
@RestController
public class NoteController {
	
	private Logger logger=GlobalResource.getLogger(NoteService.class);
	@Autowired
	private NoteService noteservice;
	/**
	 * Purpose : API for CreateNote
	 * @param notedto
	 * @param token
	 * @return
	 * @throws UserServiceException
	 */
//	@PostMapping("/createnote")
//	public  ResponseEntity<Response> createNote(@RequestBody NoteDTO notedto, @RequestHeader String token) throws UserServiceException {
//		String methodName="createNote()";
//		logger.info(methodName + "createNote API Called");		
//		String id =TokenUtil.decodetoken(token);
//		Response response=noteservice.createNote(notedto, id);
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//	}
	@PostMapping("/createnote")
	public ResponseEntity<Response> createNote(@RequestBody NoteDTO notedto,@RequestHeader String token) throws UserServiceException{
		logger.info("cretaeNote");
		//String id =TokenUtil.decodetoken(token);
		Response response=noteservice.createNote(notedto, token);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
		
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
	public ResponseEntity<Response> update(@RequestBody NoteDTO notedto,@RequestHeader String token ,@RequestParam String noteId) throws UserServiceException
	{
		String methodName="update()";
		logger.info(methodName + "update API Called");
		Response response=noteservice.update(notedto,token, noteId);
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
	public ResponseEntity<Response> setArchieveUnarchieve(@RequestParam String noteId,@RequestHeader String token) throws UserServiceException
	{
		String methodName="setArchieveUnarchieve()";
		logger.info(methodName + "setArchieveUnarchieve API Called");
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
	public ResponseEntity<Response> setPinUnpin( @RequestParam String noteId, @RequestHeader String token) throws UserServiceException
	{
		String methodName="setPinUnpin()";
		logger.info(methodName + "setPinUnpin API Called");
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
	public ResponseEntity<Response> setTrashUntrash(@RequestParam String noteId,  @RequestHeader String token) throws UserServiceException {
		String methodName="setTrashUntrash()";
		logger.info(methodName + "setTrashUntrash API Called");
		Response response=noteservice.setTrashUntrash(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/restoreTrashNotes")
	public ResponseEntity<Response> restoreTrashNotes(@RequestHeader String token) throws UserServiceException
	{
		logger.info("restoreTrashNotes");
		Response response=noteservice.restoreTrashNotes(token);
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
	 public ResponseEntity<Response> deleteinTrash( @RequestParam String noteId,  @RequestHeader String token) throws UserServiceException
	 {
		 String methodName="deleteinTrash()";
			logger.info(methodName + "deleteinTrash API Called");
		 Response response=noteservice.deleteinTrash(noteId, token);
		 return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	 }
	 
	 /**
	  * Purpose : API for Retrieve Note
	  * @param token
	  * @param noteId
	  * @return
	  */
	 @GetMapping("/retrieveNote")
	 public ResponseEntity<Response> retrieveNote(@RequestHeader String token, @RequestParam String noteId)
	 {
		 String methodName="retrieveNote()";
			logger.info(methodName + "retrieveNote API Called");
		 Response response=noteservice.retrieveNote(token, noteId);
		 return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	 }
	 /**
	  * Purpose : API for delete note by noteid.
	  * @param token
	  * @param noteId
	  * @return
	  */
	 @DeleteMapping("/deleteNote")
	 public ResponseEntity<Response> deleteNote(String token, String noteId) {
		 String methodName="deleteNote()";
		logger.info(methodName + "deleteNote API Called");	 
		 Response response=noteservice.deleteNote(token, noteId);
		 return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	 }
//	 
//	 @PutMapping("/setReminder")
//	 public ResponseEntity<Response> setReminder(@RequestHeader String token, @RequestParam String noteId,@RequestParam String reminder) throws UserServiceException
//	 {
//		 String methodName="setReminder()";
//		logger.info(methodName + "setReminder API Called");	 
//		 Response response=noteservice.setReminder(token, noteId, reminder);
//		 return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
//	 }
	 
	/**
	 * Purpose: API For Retrieve All Note
	 * @return
	 */
	
	@GetMapping("/getAllNote")
	public List<Note> getAllNote() {
		String methodName="getAllNote()";
		logger.info(methodName + "getAllNote Method Called");
		return noteservice.getAllNote();
	}
	
	/**
	 * Purpose : API For Retrieve Note by Id
	 * @param token
	 * @return
	 */
	
	@GetMapping("/getNoteByUserId")
	public ResponseEntity<Response> getNoteByUserId(@RequestHeader String token,@RequestParam String noteId) {
		logger.info("getNoteByUserId");
		Response response=noteservice.getNoteByUserId(token,noteId);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Purpose : API For Delete All Note
	 * @return
	 */

	@DeleteMapping("/deleteAllNote")
	public String deleteNote()
	{
		noteservice.DeleteAllNote();
		return "All Note Deleted";
	}
	

}