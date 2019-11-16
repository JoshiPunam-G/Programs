
/**
 * Purpose : Implementation of Controller for Accessing Services implemented in service class .

 * Author  : Punam Joshi 
 * @version 1.0
 * @since   12-11-2019  
 */

package com.bridgelabz.fundoo.label.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.mail.Multipart;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.Utility.GlobalResource;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.service.LabelService;
import com.bridgelabz.fundoo.notes.service.NoteService;
import com.bridgelabz.fundoo.response.Response;
import com.google.common.net.MediaType;

@RequestMapping("/label")
@RestController
public class LabelController {
	
	private Logger logger=GlobalResource.getLogger(NoteService.class);
	
	@Autowired
	private LabelService labelservice;
	/**
	 * Purpose :create label
	 * @param labeldto
	 * @param Token
	 * @return
	 * @throws UserServiceException
	 */
	@PostMapping("/createlabel")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDTO labeldto,@RequestHeader String Token) throws UserServiceException
	{
		String methodName="createLabel()";
		logger.info(methodName + "createLabel API Called");		
		Response response=labelservice.createLabel(labeldto, Token);
	    return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	/**
	 * Purpose :update label
	 * @param labeldto
	 * @param token
	 * @param labelId
	 * @return
	 * @throws UserServiceException
	 */
	
	@PostMapping("/updatelabel")
	public ResponseEntity<Response> updateLabel(@RequestBody LabelDTO labeldto,@RequestHeader String token,@RequestParam String labelId) throws UserServiceException
	{
		String methodName="updateLabel()";
		logger.info(methodName + "updateLabel API Called");		
		Response response=labelservice.updateLabel(labeldto, token, labelId);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Purpose :delete Label
	 * @param token
	 * @param labelId
	 * @return
	 * @throws UserServiceException
	 */
	@DeleteMapping("/deletelabel")
	public ResponseEntity<Response> deleteLabel(@RequestHeader String token,@RequestParam String labelId) throws UserServiceException {
		String methodName="deleteLabel()";
		logger.info(methodName + "deleteLabel API Called");	
		Response response=labelservice.deleteLabel(token, labelId);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Purpose :add Label
	 * @param labelId
	 * @param token
	 * @param noteId
	 * @return
	 * @throws UserServiceException
	 */
	@PutMapping("/addlabel")
	public ResponseEntity<Response> addlabeltoNote(@RequestParam String labelId,@RequestHeader String token,@RequestParam String noteId) throws UserServiceException 
	{
		String methodName="addlabeltoNote()";
		logger.info(methodName + "addlabeltoNote API Called");	
		Response response=labelservice.addlabeltoNote(labelId, token, noteId);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	/**
	 * Purpose :Retrieve Label of Note
	 * @param token
	 * @param noteId
	 * @return
	 * @throws UserServiceException
	 */
	
	@GetMapping("/getLabelsOfNote")
	public ResponseEntity<Response> getLabelsOfNote(@RequestHeader String token, @RequestParam String noteId) throws UserServiceException 
	{
		String methodName="getLabelsOfNote()";
		logger.info(methodName + "getLabelsOfNote API Called");	
		Response response=labelservice.getLabelsOfNote(token, noteId);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);	
	}
	
	/**
	 * Purpose : Retrieve Note of Label
	 * @param token
	 * @param labelId
	 * @return
	 * @throws UserServiceException
	 */
	
	@GetMapping("/getNotesOfLabel")
	public ResponseEntity<Response> getNotesOfLabel(@RequestHeader String token, @RequestParam String labelId) throws UserServiceException 
	{
		String methodName="getNotesOfLabel()";
		logger.info(methodName + "getNotesOfLabel API Called");	
		Response response=labelservice.getNotesOfLabel(token, labelId);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}

	
	/**
	 * Purpose : Retrieve All Label
	 * @param token
	 * @return
	 * @throws UserServiceException
	 */
	
	@GetMapping("/getAllLabelFromUser")
	public ResponseEntity<Response> getAllLabelFromUser(@RequestHeader String token ,@RequestParam String email) throws UserServiceException 
	{
		String methodName="getAllLabelFromUser()";
		logger.info(methodName + "getAllLabelFromUser API Called");	
		Response response=labelservice.getAllLabelFromUser(token,email);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/addnotetoUser")
	public ResponseEntity<Response> addnotetoUser(@RequestHeader String token, @RequestParam String noteId ,@RequestParam String email) throws UserServiceException
	{
		String methodName="getAllLabelFromUser()";
		logger.info(methodName + "getAllLabelFromUser API Called");	
	    Response response=labelservice.addnotetoUser(token, noteId, email);
	    return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Purpose :Retrieve All Label
	 * @return
	 */
	@GetMapping("/getAllLabel")
	public List<Label> getAllLabel()
	{
		String methodName="getAllLabel()";
		logger.info(methodName + "getAllLabel API Called");
		return labelservice.getAllLabel();
	}
	
	/**
	 * Purpose :Delete All Label
	 * @return
	 */
	@DeleteMapping("/deleteAllLabel")
	public String deleteAllLabel()
	{
		String methodName="deleteAllLabel()";
		logger.info(methodName + "deleteAllLabel API Called");	
		labelservice.deleteAllLabel();
		return "All Label Deleted";
	}
	/**
	 * Purpose :Retrieve User Note And Label
	 * @param token
	 * @param noteId
	 * @param labelId
	 * @param email
	 * @return
	 * @throws UserServiceException
	 */
	
	@PutMapping("/getUserNoteAndLabel")
	public ResponseEntity<Response> getUserNoteAndLabel(@RequestHeader String token, @RequestParam String noteId, @RequestParam  String labelId ,  @RequestParam  String email) throws UserServiceException 
	{
		String methodName="getUserNoteAndLabel()";
		logger.info(methodName + "getUserNoteAndLabel API Called");	
		Response response=labelservice.getUserNoteAndLabel(token, noteId, labelId, email);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	
	

      @PutMapping("/upload")
	  public String uploadFile(@RequestParam("file")MultipartFile file )throws IOException 
	{
	  logger.info("Upload File");
	  File convertfile=new File("/home/admin106/DemoUpload/" +file.getOriginalFilename());
	  convertfile.createNewFile(); 
	 try
	 {
		 FileOutputStream fout=new FileOutputStream(convertfile); 
		 fout.write(file.getBytes());
		// fout.close();
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	  return "File Uploaded ";
	  
	  }
	
    
      @DeleteMapping("/delete")
    public String deleteFile(@RequestParam("file")MultipartFile file )throws IOException 
  	{
  	  logger.info("Upload File");
  	  File convertfile=new File("/home/admin106/DemoUpload/" +file.getOriginalFilename());
  	  convertfile.delete();
  	  return "file deleted";
  	}

}
