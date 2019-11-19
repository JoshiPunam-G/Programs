/**
 * Purpose : Implementation of service for Label.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   12-11-2019  
 */
package com.bridgelabz.fundoo.label.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.Utility.GlobalResource;
import com.bridgelabz.fundoo.Utility.TokenUtil;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.repository.LabelRepository;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.notes.repository.NoteRepository;
import com.bridgelabz.fundoo.notes.service.NoteService;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseStatus;

@Service
@PropertySource("user.properties")
public class LabelService implements LabelInterface{
	private Logger logger=GlobalResource.getLogger(NoteService.class);
	
	@Autowired
	private LabelRepository labelrepository;
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private NoteRepository noterepository;

	@Autowired
	private Environment environment;
	
	@Autowired
	private ModelMapper mapper;
		
   /**
    * Purpose:API for create label
    * @param labeldto
    * @param Token
    * @throws UserServiceException
    */
	@Override
	public Response createLabel(LabelDTO labeldto, String Token) throws UserServiceException {
		String methodName="createLabel()";
		logger.info(methodName + "createLabel API Called");		
		String userId=TokenUtil.decodetoken(Token);
		Optional<User> user=userrepository.findById(userId);
		System.out.println(user);
		if(!user.isPresent()) {
			throw new UserServiceException(100,"Invalid User");
		}
		Optional<Label> labelPresent = labelrepository.findByUserIdAndLabelName(userId, labeldto.getLabelName());
		if(labelPresent.isPresent()) {
			throw new UserServiceException(101,"User laready Exist");
		}
		Label label = mapper.map(labeldto,Label.class);
		label.setUserId(user.get().getId());
		label.setLabelName(labeldto.getLabelName());
	    label.setLabelCreateDate(LocalDateTime.now());
		label.setModified(LocalDateTime.now());
		labelrepository.save(label);
		return responseMessage(environment.getProperty("status.success.createlabel"),100);
	}
	
	/**
	 * Purpose :API for Update Label
	 * @param labeldto
	 * @param token
	 * @param labelId
	 * @throws UserServiceException
	 */

	@Override
	public Response updateLabel(LabelDTO labeldto, String token, String labelId) throws UserServiceException {
		String methodName="updateLabel()";
		logger.info(methodName + "updateLabel API Called");		
		String userId=TokenUtil.decodetoken(token);
		System.out.println(userId);
		Optional<User> user=userrepository.findById(userId);
		System.out.println(user);
		if(!user.isPresent()) {
			throw new UserServiceException(100,"Invalid User");
		}
		Optional<Label> labelPresent = labelrepository.findByUserIdAndLabelName(userId, labeldto.getLabelName());
		if(labelPresent.isPresent()) {
			throw new UserServiceException(101,"User laready Exist");
		}
		Label label = mapper.map(labeldto,Label.class);
		label.setLabelName(labeldto.getLabelName());
	    label.setLabelCreateDate(LocalDateTime.now());
		label.setModified(LocalDateTime.now());
		labelrepository.save(label);
		return responseMessage(environment.getProperty("status.success.updatelabel"),100);
	}
	
	/**
	 * Purpose :API For deleteLabel
	 * @param token
	 * @param labelId
	 * @throws UserServiceException
	 */
	@Override
	public Response deleteLabel(String token, String labelId) throws UserServiceException {
		String methodName="deleteLabel()";
		logger.info(methodName + "deleteLabel API Called");	
		String userId=TokenUtil.decodetoken(token);
		Optional<User> user=userrepository.findById(userId);
		if(!user.isPresent())
		{
			throw new UserServiceException(100,"User Not Found");
		}
		Label label=labelrepository.findByLabelIdAndUserId(labelId, userId);
		if(label==null)
		{
			throw new UserServiceException(100,"Label Does Not Exist");
		}
		return responseMessage(environment.getProperty("status.success.delete"), 200);
	}

	
	/**
	 * Purpose :API for Add Label to Note
	 * @param labelId
	 * @param token
	 * @param noteId
	 * @throws UserServiceException
	 */
	@Override
	public Response addlabeltoNote(String labelId, String token, String noteId) throws UserServiceException {
		String methodName="addlabeltoNote()";
		logger.info(methodName + "addlabeltoNote API Called");	
		
		String userId = TokenUtil.decodetoken(token);
		Note note = noterepository.findByUserIdAndNoteId(userId, noteId);
		System.out.println(note);
		
      	if(note == null) 
			throw new UserServiceException(101,"Invalid Note");
		Label label=labelrepository.findByLabelIdAndUserId(labelId, userId);
		
		if(label == null) {
			throw new UserServiceException(-6,"Invalid label");
		}
	    
		note.getLabellist().add(label);
	    note.setModified(LocalDateTime.now());
		noterepository.save(note);
		return responseMessage(environment.getProperty("status.label.addnote"),100);
	}
	
	

	
	/**
	 * Purpose :API For Retrieve Label of Note
	 * @param token
	 * @param noteId
	 * @throws UserServiceException
	 */
	
	@Override
	public Response getLabelsOfNote(String token, String noteId) throws UserServiceException {
		String methodName="getLabelsOfNote()";
		logger.info(methodName + "getLabelsOfNote API Called");
		
		String userId= TokenUtil.decodetoken(token);
		System.out.println(userId);
		Note note=noterepository.findByNoteId(noteId);
		System.out.println(note);
		List<Label> labellist=labelrepository.findByUserId(userId);
		System.out.println(labellist);
		note.setLabellist(labellist);
		return responseMessage(environment.getProperty("status.getLabelsOfNote"),100);
	}

	/**
	 * Purpose : API for Retrieve Note Of Label
	 * @param token
	 * @param labelId
	 * @throws UserServiceException
	 */
	
	@Override
	public Response getNotesOfLabel(String token, String labelId) throws UserServiceException {
		String methodName="getNotesOfLabel()";
		logger.info(methodName + "getNotesOfLabel API Called");	
		String userId=TokenUtil.decodetoken(token);
		System.out.println(userId);
		Label label=labelrepository.findByLabelIdAndUserId(labelId, userId);
		System.out.println(label);
		List<Note> notelist=noterepository.findByUserId(userId);
		System.out.println(notelist);
		label.setNotelist(notelist);
		return responseMessage(environment.getProperty("status.getNotesOfLabel"),100);
	}
	
	/**
	 * Purpose : Retreive All LAbel From User
	 * @param token
	 * @throws UserServiceException
	 */
	@Override
	public Response getAllLabelFromUser(String token,String email) throws UserServiceException {
		String methodName="getAllLabelFromUser()";
		logger.info(methodName + "getAllLabelFromUser API Called");	
		
		String userId = TokenUtil.decodetoken(token);
		Optional<User> user = userrepository.findById(userId);
		User newuser=userrepository.findByEmail(email);
		if(!newuser.isPresent()) {
			throw new UserServiceException("Invalis input ");
		}
		List<Label> labellist = labelrepository.findByUserId(userId);
	    newuser.setLabellist(labellist);
	    userrepository.save(newuser);
		return responseMessage(environment.getProperty("status.getAllLabelFromUser"),100);
	}
	
	/**
	 * Purpose :API For Retrieve All Label
	 * @return
	 */
	
	public List<Label> getAllLabel()
	{
		String methodName="getAllLabel()";
		logger.info(methodName + "getAllLabel API Called");	
		return labelrepository.findAll();
	}
	
	/**
	 * Purpose :API for Delete All Label
	 */
	public void deleteAllLabel()
	{
		String methodName="deleteAllLabel()";
		logger.info(methodName + "deleteAllLabel API Called");	
	   labelrepository.deleteAll();
	}
	
	
	/**
	 * Purpose : Implementation for sending response message
	 * @param statusmessage
	 * @param statuscode
	 * @return
	 */
	public Response responseMessage(String statusmessage,int statuscode)
	{
		Response response=ResponseStatus.statusInformation(statusmessage, statuscode);
		response.getStatusMessage();
		return response;
	}

	
	/**
	 * Purpose : Implementation of Retrieve User Note and Label.
	 */
	@Override
	public Response getUserNoteAndLabel(String token, String noteId, String labelId ,String email) throws UserServiceException {
		String userId = TokenUtil.decodetoken(token);
		User user1=userrepository.findByEmail(email);
		List<Note> notelist =noterepository.findByUserId(userId);
         if(notelist == null) 
			throw new UserServiceException(101,"Invalid Note");      
        List<Label> label=labelrepository.findByLabelId(labelId);
		if(label == null) {
			throw new UserServiceException(-6,"Invalid label");
		}
		Note note=noterepository.findByNoteId(noteId);
		List<Label> labellist=labelrepository.findByLabelId(labelId);
		user1.setNotelist(notelist);
		note.setLabellist(labellist);
		userrepository.save(user1);
		noterepository.save(note);
		return responseMessage(environment.getProperty("status.Note.addnote.addlabel"),100);
	}

	/**
	 * Purpose :Add Note To User
	 */
	@Override
	public Response addnotetoUser(String token, String noteId ,String email) throws UserServiceException {
		String userId = TokenUtil.decodetoken(token);
		Note note = noterepository.findByUserIdAndNoteId(userId, noteId);
		System.out.println(note);
		if(note == null) 
			throw new UserServiceException(101,"Invalid Note");
		
        Optional<User> user = userrepository.findById(userId);
		if(!user.isPresent())
			throw new UserServiceException("user not found");
		List<Note> notelist=noterepository.findByUserId(userId);
		User newuser=userrepository.findByEmail(email);
		newuser.setNotelist(notelist);
		userrepository.save(newuser);
		return responseMessage(environment.getProperty("status.Note.addnotetoUser"),100);
	}
	
	/**
	 * Purpose :Upload File
	 */

	@Override
	public Response uploadFile(MultipartFile file) throws IOException {
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
		 return responseMessage(environment.getProperty("status.uploadfile.success"), 200);
	}

	/**
	 * Purpose : Delete File
	 */
	@Override
	public Response deleteFile(MultipartFile file) throws IOException {
		logger.info("Upload File");
	  	File convertfile=new File("/home/admin106/DemoUpload/" +file.getOriginalFilename());
	  	convertfile.delete();
	  	return responseMessage(environment.getProperty("status.deletefile.success"), 100);
	}
}
