package com.bridgelabz.fundoo.label.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.service.LabelService;
import com.bridgelabz.fundoo.response.Response;

@RestController
public class LabelController {
	
	@Autowired
	private LabelService labelservice;
	
	@PostMapping("/createlabel")
	public Response createLabel(@RequestBody LabelDTO labeldto,@RequestHeader String Token) throws UserServiceException
	{
		return labelservice.createLabel(labeldto, Token);
	}
	
	@PostMapping("/updatelabel")
	public Response updateLabel(LabelDTO labeldto, String token, String labelId) throws UserServiceException
	{
		return labelservice.updateLabel(labeldto, token, labelId);
	}
	
	@DeleteMapping("/deletelabel")
	public Response deleteLabel(String token, String labelId) throws UserServiceException {
		return labelservice.deleteLabel(token, labelId);
	}
	
	@PutMapping("/addlabel")
	public Response addlabeltoNote(String labelId, String token, String noteId) throws UserServiceException 
	{
		return labelservice.addlabeltoNote(labelId, token, noteId);
	}
	
	
	@GetMapping("/getLabel")
	public List<Label> getAll()
	{
		return labelservice.getAll();
	}
}
