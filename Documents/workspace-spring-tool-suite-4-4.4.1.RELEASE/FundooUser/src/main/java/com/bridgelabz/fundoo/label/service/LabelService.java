package com.bridgelabz.fundoo.label.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.omg.CORBA.UserException;
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
	 * Purpose :API For CreateLabel
	 */
	
	@Override
	public Response createLabel(LabelDTO labeldto, String Token) throws UserServiceException {
		String userId=TokenUtil.decodetoken(Token);
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
		//label.setUserId(userId);
	    label.setLabelCreateDate(LocalDateTime.now());
		label.setModified(LocalDateTime.now());
		user.get().getLabel().add(label);
		labelrepository.save(label);
		userrepository.save(user.get());
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

	@Override
	public List<Label> showLabel(String token, String labelId) {
		
		return null;
	}

	public List<Label> getAll()
	{
		return labelrepository.findAll();
	}

}
