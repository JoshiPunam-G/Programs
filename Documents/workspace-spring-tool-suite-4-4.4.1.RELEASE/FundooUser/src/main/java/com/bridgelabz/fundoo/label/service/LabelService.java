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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.Utility.TokenUtil;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.repository.LabelRepository;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.notes.repository.NoteRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseStatus;

@Service
@PropertySource("user.properties")
public class LabelService implements LabelInterface{

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
    */
	@Override
	public Response createLabel(LabelDTO labeldto, String Token) throws UserServiceException {
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
	//	User setuser=new User();
		Label label = mapper.map(labeldto,Label.class);
		label.setLabelName(labeldto.getLabelName());
	    label.setLabelCreateDate(LocalDateTime.now());
		label.setModified(LocalDateTime.now());
		labelrepository.save(label);
		Response response = ResponseStatus.statusInformation(environment.getProperty("status.success.createlabel"),100);
			return response;
	}
	
	/**
	 * Purpose :API for Update Label
	 */

	@Override
	public Response updateLabel(LabelDTO labeldto, String token, String labelId) throws UserServiceException {
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
		Response response = ResponseStatus.statusInformation(environment.getProperty("status.success.updatelabel"),100);
			return response;
	}
	
	/**
	 * Purpose :API For deleteLabel
	 */
	@Override
	public Response deleteLabel(String token, String labelId) throws UserServiceException {
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
		Response response=ResponseStatus.statusInformation(environment.getProperty("status.success.delete"), 200);
		return response;
	}

	
	/**
	 * Purpose :API for Add Label to Note
	 */
	@Override
	public Response addlabeltoNote(String labelId, String token, String noteId) throws UserServiceException {
		String userId = TokenUtil.decodetoken(token);
		Optional<User> user = userrepository.findById(userId);
		if(user== null) {
			throw new UserServiceException(-6,"Invalid Input");
		}
		Note note = noterepository.findByUserIdAndNoteId(userId, noteId);
		if(note == null) {
			throw new UserServiceException(101,"Invalid Note");
		}
		Label label = labelrepository.findByLabelIdAndUserId(labelId, userId);
		if(label == null) {
			throw new UserServiceException(-6,"Invalid label");
		}
		note.getListLabel().add(label);
		note.setModified(LocalDateTime.now());
		label.setModified(LocalDateTime.now());
		noterepository.save(note);
		labelrepository.save(label);
		Response response = ResponseStatus.statusInformation(environment.getProperty("status.label.addnote"),100);
		return response;
	}
	
	/**
	 * Purpose :API For Retrieve Label of Note
	 */
	
	@Override
	public List<LabelDTO> getLabelsOfNote(String token, String noteId) throws UserServiceException {
		String userId= TokenUtil.decodetoken(token);
		Optional<User> user = userrepository.findById(userId);
		if(user == null) {
			throw new UserServiceException(-6,"User dose not exist ");
		}
		Optional<Note> note = noterepository.findById(noteId);
		if(!note.isPresent()) {
			throw new UserServiceException(-6,"Note Dose Not exist ");
		}
		List<Label> label = note.get().getListLabel();
		List<LabelDTO> listlabel = new ArrayList<>();
		for(Label noteLabel: label) {
			LabelDTO labeldto= mapper.map(noteLabel,LabelDTO.class);
			listlabel.add(labeldto);
		
		}
		return listlabel;
	}

	/**
	 * Purpose : API for Note Of Label
	 */
	
	@Override
	public List<NoteDTO> getNotesOfLabel(String token, String labelId) throws UserServiceException {
	
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
		return noteList;
	}
	
	/**
	 * Purpose : Retrive All LAbel From User
	 */
	@Override
	public List<Label> getAllLabelFromUser(String token) throws UserServiceException {
		
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
			return listLabel;
	}
	
	/**
	 * Purpose :API For Retrieve All Label
	 * @return
	 */
	
	public List<Label> getAllLabel()
	{
		return labelrepository.findAll();
	}
}
