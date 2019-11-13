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

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.Utility.TokenUtil;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.notes.repository.NoteRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseStatus;

@Service
@PropertySource("user.properties")
public class NoteService implements NoteInterface {

	@Autowired
	private NoteRepository noterepository;
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private Environment environment;
	
	@Autowired
	private ModelMapper mapper;

	//private Response response;

	/**
	 * Purpose :API for Create Note
	 * 
	 * @throws UserServiceException
	 */

	@Override
	public Response createNote(NoteDTO notedto, String token) throws UserServiceException {
		String tokenNote = TokenUtil.decodetoken(token);
		if (notedto.getTitle().isEmpty() && notedto.getDescription().isEmpty()) {
			throw new UserServiceException(10, "Title and description are empty", tokenNote);
		} 
		 Note note=mapper.map(notedto, Note.class);
  		 Optional<User> user=repository.findById(tokenNote);
		 note.setUserId(tokenNote);
		 note.setCreateDate(LocalDateTime.now());
		 note.setModified(LocalDateTime.now());
		 noterepository.save(note);
		 
		 Response response = ResponseStatus.statusInformation(environment.getProperty("status.success.createnote"),100);
			return response;
	}

	/**
	 * Purpose :API For Update Note
	 */
	@Override
	public Response update(NoteDTO notedto, String noteId) throws UserServiceException {
		System.out.println("In Update Note");
		Note note = noterepository.findByNoteId(noteId);
		if(notedto.getTitle().isEmpty() && notedto.getDescription().isEmpty())
		{
			throw new UserServiceException("Title and description are empty" );
		}
		note.setTitle(notedto.getTitle());
		note.setDescription(notedto.getDescription());
		note.setModified(LocalDateTime.now());
		noterepository.save(note);
		Response response = ResponseStatus.statusInformation(environment.getProperty("status.notes.updatedSuccessfull"),200);
		return response;
	}

	
	/**
	 * Purpose :API for ArchieveAndUnArchieve note
	 */

	@Override
	public Response setArchieveUnarchieve(String noteId, String token) throws UserServiceException {
		System.out.println("Archieve Token");
		String tokenNote=TokenUtil.decodetoken(token);
		Note note=noterepository.findByNoteId(noteId,tokenNote);
		System.out.println(note);
		if(note==null)
		{
			throw new UserServiceException(environment.getProperty("status.note.notfound"));
		}
			if(note.isStatusArchieve()==false)
			{
				note.setStatusArchieve(true);
				noterepository.save(note);
			    Response response = ResponseStatus.statusInformation(environment.getProperty("status.note.archieve"),200);
				return response;
			}
			else
			{
				note.setStatusArchieve(false);
				noterepository.save(note);
				 Response response = ResponseStatus.statusInformation(environment.getProperty("status.note.unarchieve"),200);
					return response;
			}
		}
	
	
	/**
	 * Purpose :API For SetPinUnpin Note
	 * @throws UserServiceException 
	 */
	@Override
	public Response setPinUnpin(String noteId, String token) throws UserServiceException {
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
				Response response = ResponseStatus.statusInformation(environment.getProperty("status.note.pin"),200);
				return response;
			}
			else
			{
				note.setStatusArchieve(false);
				noterepository.save(note);
				Response response = ResponseStatus.statusInformation(environment.getProperty("status.note.unpin"),200);
				return response;
			}
	}
	
	/**
	 * Purpose :API For TrashUntrash
	 * @throws UserServiceException 
	 */

	@Override
	public Response setTrashUntrash(String noteId, String token) throws UserServiceException {
	   String tokenNote=TokenUtil.decodetoken(token);
	   Note note=noterepository.findByNoteId(noteId,tokenNote);
	   System.out.println(note);
	 if(note==null)
	 {
		 throw new UserServiceException(environment.getProperty("note.notpresent"));
	 }
	 if(note.isStatusTrashUntrash()==false)
		{
			note.setStatusTrashUntrash(true);
			noterepository.save(note);
			Response response = ResponseStatus.statusInformation(environment.getProperty("note.trash"),200);
			return response;
		}
		else
		{
			note.setStatusArchieve(false);
			noterepository.save(note);
			Response response = ResponseStatus.statusInformation(environment.getProperty("note.untrash"),200);
			return response;
		}
	}

/**
 * Purpose :API For DeleteTrash
 */
	
	@Override
	public Response deleteinTrash(String noteId, String token) {
		String tokenNote = TokenUtil.decodetoken(token);
		Optional<Note> note = noterepository.findById(noteId);
		System.out.println(note);
		if (tokenNote.equals(note.get().getNoteId())) {
			if (note.isPresent()) {
				if (note.get().isStatusTrashUntrash() == true) {
					noterepository.deleteByNoteId(noteId);
					Response response = ResponseStatus.statusInformation(environment.getProperty("note.delete"),200);
					return response;
				} else {
					noterepository.save(note.get());
					Response response = ResponseStatus.statusInformation(environment.getProperty("notNot.deleted"),200);
					return response;
				}
			} else {
				Response response = ResponseStatus.statusInformation(environment.getProperty("note.NotPresent"),200);
				return response;
			}

		} else {	
			Response response = ResponseStatus.statusInformation(environment.getProperty("note.invalid"),200);
			return response;
		}
	}
	 
/**
 * Purpose : Retrieve All Note
 */

@Override
public List<Note> getAllNote() {
		return noterepository.findAll();
    }


}
