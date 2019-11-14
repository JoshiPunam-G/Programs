
/**
 * Purpose : Implementation of Controller for Accessing Services implemented in service class .

 * Author  : Punam Joshi 
 * @version 1.0
 * @since   12-11-2019  
 */

package com.bridgelabz.fundoo.label.controller;
import java.util.List;
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
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.service.LabelService;
import com.bridgelabz.fundoo.response.Response;

@RequestMapping("/label")
@RestController
public class LabelController {
	
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
		System.out.println("--");
		Response response=labelservice.addlabeltoNote(labelId, token, noteId);
		return new ResponseEntity<Response>(response,HttpStatus.ACCEPTED);
	}
	/**
	 * Purpose : Retrieve All Label
	 * @param token
	 * @return
	 * @throws UserServiceException
	 */
	
	@GetMapping("/getLabel")
	public List<Label> getAllLabelFromUser(@RequestHeader String token) throws UserServiceException 
	{
		return labelservice.getAllLabelFromUser(token);
	}
	
	/**
	 * Purpose :Retrieve All Label
	 * @return
	 */
	@GetMapping("/getAllLabel")
	public List<Label> getAllLabel()
	{
		return labelservice.getAllLabel();
	}
	
	@DeleteMapping("/deleteAllLabel")
	public String deleteAllLabel()
	{
		labelservice.deleteAllLabel();
		return "All Label Deleted";
	}

}
