/**
 * Purpose : Implementation of service for Label.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   12-11-2019  
 */
package com.bridgelabz.fundoo.label.service;

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
		Optional<User> user = userrepository.findById(userId);
		
		if(!user.isPresent())
			throw new UserServiceException("user not found");
		List<Label> label=labelrepository.findByLabelId(labelId);
		
	
		if(label == null) {
			throw new UserServiceException(-6,"Invalid label");
		}
		note.setLabellist(label);
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
		Response response = null;
		String methodName="getLabelsOfNote()";
		logger.info(methodName + "getLabelsOfNote API Called");	
		String userId= TokenUtil.decodetoken(token);
		Optional<User> user = userrepository.findById(userId);
		if(user == null) {
			throw new UserServiceException(-6,"User dose not exist ");
		}
		Optional<Note> note = noterepository.findById(noteId);
		if(!note.isPresent()) {
			throw new UserServiceException(-6,"Note Dose Not exist ");
		}
		List<Label> label = note.get().getLabellist();
		List<LabelDTO> listlabel = new ArrayList<>();
		for(Label noteLabel: label) {
			LabelDTO labeldto= mapper.map(noteLabel,LabelDTO.class);
			listlabel.add(labeldto);
		}
		return  response;
	}

	/**
	 * Purpose : API for Retrieve Note Of Label
	 * @param token
	 * @param labelId
	 * @throws UserServiceException
	 */
	
	@Override
	public Response getNotesOfLabel(String token, String labelId) throws UserServiceException {
		Response response = null;
		String methodName="getNotesOfLabel()";
		logger.info(methodName + "getNotesOfLabel API Called");	
		String userId =TokenUtil.decodetoken(token);
		Optional<User> user = userrepository.findById(userId);
		if(!user.isPresent()) {
			throw new UserServiceException(-6,"Invalid User");
		}
		Label label = labelrepository.findByLabelIdAndUserId(labelId, userId);
		if(label == null) {
			throw new UserServiceException(-6,"Invalid Label");
		}
		List<Note> notes = label.getNotes();
		List<NoteDTO> noteList = new ArrayList<>();
		for(Note note : notes) {
			NoteDTO noteDto= mapper.map(note, NoteDTO.class);
			noteList.add(noteDto);
		}
		return response;
	}
	
	/**
	 * Purpose : Retreive All LAbel From User
	 * @param token
	 * @throws UserServiceException
	 */
	@Override
	public Response getAllLabelFromUser(String token) throws UserServiceException {
		Response response=null;
		String methodName="getAllLabelFromUser()";
		logger.info(methodName + "getAllLabelFromUser API Called");	
		String userId = TokenUtil.decodetoken(token);
		Optional<User> user = userrepository.findById(userId);
		if(!user.isPresent()) {
			throw new UserServiceException("Invalis input ");
		}
		List<Label> labels = labelrepository.findByUserId(userId);
		List<Label> listLabel =  new ArrayList<>();
		for(Label noteLabel:labels) {
			Label labelDto = mapper.map(noteLabel, Label.class);
			listLabel.add(labelDto);
		}
			return response;
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
}
