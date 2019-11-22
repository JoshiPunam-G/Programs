
 
/**
 * Purpose : Implementation of Service for note.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   11-11-2019  
 */
package com.bridgelabz.fundoo.notes.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.Utility.GlobalResource;
import com.bridgelabz.fundoo.Utility.TokenUtil;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.dto.ReminderDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.notes.repository.NoteRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseStatus;
@Service
@PropertySource("user.properties")
public class NoteService implements NoteInterface {
	
	private Logger logger=GlobalResource.getLogger(NoteService.class);

	@Autowired
	private NoteRepository noterepository;
	
	@Autowired
    private UserRepository repository;

	@Autowired
	private Environment environment;
	
	@Autowired
	private ModelMapper mapper;

	/**
	 * Purpose :API for Create Note
	 * 
	 * @param NoteDTO 
	 * @param token
	 * @throws UserServiceException
	 */

	@Override
	public Response createNote(NoteDTO notedto, String token) throws UserServiceException {
		logger.info("createNote called");
		
		String tokenNote = TokenUtil.decodetoken(token);
		if (notedto.getTitle().isEmpty() && notedto.getDescription().isEmpty()) {
			throw new UserServiceException(10, "Title and description are empty", tokenNote);
		} 
		 Note note=mapper.map(notedto, Note.class);
		 note.setUserId(tokenNote);
		 note.setCreateDate(LocalDateTime.now());
		 note.setModified(LocalDateTime.now());
		 noterepository.save(note);
		 return statusInfo(environment.getProperty("status.success.createnote"), 100);
	}

	/**
	 * Purpose :API For Update Note
	 * @param  NoteDTO
	 * @param noteId
	 * @throws UserServiceException
	 */
	@Override
	public Response update(NoteDTO notedto,String token, String noteId) throws UserServiceException {
		logger.info("update Method Called");
		String tokenNote=TokenUtil.decodetoken(token);
		Note note = noterepository.findByNoteId(noteId);
		if(notedto.getTitle().isEmpty() && notedto.getDescription().isEmpty())
		{
			throw new UserServiceException("Title and description are empty",tokenNote );
		}
		note.setTitle(notedto.getTitle());
		note.setDescription(notedto.getDescription());
		note.setModified(LocalDateTime.now());
		noterepository.save(note);
		return statusInfo(environment.getProperty("status.notes.updatedSuccessfull"),200);	
	}	
	/**
	 * Purpose :API for ArchieveAndUnArchieve note
	 * @param noteId
	 * @param token
	 * @throws UserServiceException
	 */

	@Override
	public Response setArchieveUnarchieve(String noteId, String token) throws UserServiceException {
		logger.info("setArchieveUnarchieve Method Called");
		
		String tokenNote=TokenUtil.decodetoken(token);
		Note note=noterepository.findByNoteId(noteId,tokenNote);
		if(note==null)
		{
			throw new UserServiceException(environment.getProperty("status.note.notfound"));
		}
			if(note.isStatusArchieve()==false)
			{
				note.setStatusArchieve(true);
				noterepository.save(note);
				return statusInfo(environment.getProperty("status.note.archieve"),200);
			}
			else
			{
				note.setStatusArchieve(false);
				noterepository.save(note);
				return statusInfo(environment.getProperty("status.note.unarchieve"),200);	
			}
		}
	
	
	/**
	 * Purpose :API For SetPinUnpin Note
	 * @param noteID 
	 * @param token
	 * @throws UserServiceException 
	 */
	@Override
	public Response setPinUnpin(String noteId, String token) throws UserServiceException {
		logger.info("setPinUnpin Method Called");
		String tokenNote=TokenUtil.decodetoken(token);
		Note note=noterepository.findByNoteId(noteId,tokenNote);
		if(note==null)
		{
			throw new UserServiceException(environment.getProperty("status.note.notfound"));
		}
		
			if(note.isStatusPinUnpin()==false)
			{
				note.setStatusPinUnpin(true);
				noterepository.save(note);
				return statusInfo(environment.getProperty("status.note.pin"),200);
			}
			else
			{
				note.setStatusArchieve(false);
				noterepository.save(note);
				return statusInfo(environment.getProperty("status.note.unpin"),200);
			}
	}
	
	/**
	 * Purpose :API For TrashUntrash
	 * @param noteID
	 * @param token
	 * @throws UserServiceException 
	 */
	@Override
	public Response setTrashUntrash(String noteId, String token) throws UserServiceException {
		logger.info( "setTrashUntrash Method Called");
		
	   String tokenNote=TokenUtil.decodetoken(token);
	   Note note=noterepository.findByNoteId(noteId,tokenNote);
	 
	 if(note==null)
	 {
		 throw new UserServiceException(environment.getProperty("note.notpresent"));
	 }
	 if(note.isStatusTrashUntrash()==false)
		{
			note.setStatusTrashUntrash(true);
			noterepository.save(note);
			return statusInfo(environment.getProperty("note.trash"),200);
		}
		else
		{
			note.setStatusArchieve(false);
			noterepository.save(note);
			return statusInfo(environment.getProperty("note.untrash"),200);
		}
	}

/**
 * Purpose :API For DeleteTrash
 * @param noteID
 * @param token
 */
	@Override
	public Response deleteinTrash(String noteId, String token) {
		logger.info("deleteinTrash Method Called");
		
		String tokenNote = TokenUtil.decodetoken(token);
		Optional<User> user=repository.findById(tokenNote);
		Note note=noterepository.findByUserIdAndNoteId(tokenNote, noteId);
		if(note.isTrash()==true)
		{
		   note.setStatusTrashUntrash(false);
		   repository.save(user.get());
		   noterepository.delete(note);
		   return statusInfo(environment.getProperty("status.success.deleteTrash"), 200);
		}
		else
		{
			return statusInfo(environment.getProperty("status.success.deleteTrash"), 200);
		}
	}
	
	
	/**
	 * Purpose :API For Restore Trash Note
	 * @param token
	 */

	@Override
	public Response restoreTrashNotes(String token) throws UserServiceException {
		logger.info("restore trash");
		String userId=TokenUtil.decodetoken(token);
		User user = repository.findById(userId)
				.orElseThrow(() -> new UserServiceException("Sorry ! Note are not available"));
		List<Note> userNote = user.getNotes().stream().filter(data -> (data.isTrash() == true))
				.collect(Collectors.toList());
		System.out.println(userNote);
		return statusInfo(environment.getProperty("status.success.restoreTrash"), 200);
	}	
/**
 * Purpose : Retrieve Note By NoteId
 * @param token
 * @param noteId
 */

@Override
public Response retrieveNote(String token, String noteId) {
	logger.info("retrieveNote Method Called");
	String tokenNote=TokenUtil.decodetoken(token);
	Note note=noterepository.findByUserIdAndNoteId(tokenNote, noteId);
	String title=note.getTitle();
	System.out.println(title);
	String description=note.getDescription();
	System.out.println(description);
	return statusInfo(environment.getProperty("status.findnotebyid.success"), 100);
}

/**
 * Purpose :Delete Note By NoteID
 * @param token
 * @param noteId
 */

@Override
public Response deleteNote(String token, String noteId) {
	logger.info("deleteNote Method Called");
	String tokenNote=TokenUtil.decodetoken(token);
	Note note=noterepository.findByUserIdAndNoteId(tokenNote, noteId);
	if(note.isTrash()==false)
	{
		note.setStatusTrashUntrash(true);
		note.setModified(LocalDateTime.now());
		noterepository.save(note);
		return responseMessage(environment.getProperty("status.deletenotebyid.success"), 100);	
	}
	return statusInfo(environment.getProperty("status.deletenote.fail"), 200);
}

/**
* Purpose : Retrieve All Note
*/
@Override
public List<Note> getAllNote() {
	logger.info( "getAllNote Method Called");
		return noterepository.findAll();
//		System.out.println(notes);
//		return notes;
   }

/**
 * Purpose :Retrieve Note By User Id.
 */

@Override
public Response getNoteByUserId(String token ,String noteId) {
	logger.info("getNoteByUserId");
	String tokenNote=TokenUtil.decodetoken(token);
    Note note=noterepository.findByNoteId(noteId, tokenNote);
    noterepository.save(note);
	return responseMessage(environment.getProperty("status.getNoteByUserId"), 200);
}


public void DeleteAllNote()
{
	String methodName="DeleteAllNote()";
	logger.info(methodName + "DeleteAllNote Method Called");
	 noterepository.deleteAll();
}


/**
 * Purpose :Reminder set To note
 */
@Override
public Response setReminder(String token, String noteId, ReminderDTO reminder) throws UserServiceException {
	logger.info("setReminder API Called");	 
	Note note1=noterepository.findByNoteId(noteId);
	System.out.println(note1);
	if(note1==null)
		 throw new UserServiceException(environment.getProperty("note.notpresent"));
	//note1.setReminderlist(reminderDTO);
   
   // reminderDTO.setReminder(reminderlist);
	noterepository.save(note1);
	return statusInfo(environment.getProperty("status.setReminder"), 200);
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

public Response responseToken(String statusmessage,int statuscode, String token)
{
	Response response=ResponseStatus.tokenstatusInformation(statusmessage, statuscode, token);
	response.getStatusMessage();
	return response;
}

public static Response statusInfo(String statusmessage,int statuscode)
{
	Response response=new Response();
	response.setStatusMessage(statusmessage);
	response.setStatuscode(statuscode);
	return response;
}



}