package com.bridgelabz.fundoo.notes.service;
import java.time.LocalDateTime;
import java.util.List;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.dto.ReminderDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.response.Response;

public interface NoteInterface {
	 Response createNote(NoteDTO notedto, String token) throws UserServiceException;
	 public List<Note> getAllNote();
     Response  setArchieveUnarchieve(String noteId, String token)throws UserServiceException;
	 Response update(NoteDTO notedto,String token, String noteId) throws UserServiceException;
	 public Response setPinUnpin(String noteId, String token) throws UserServiceException;
	 public Response setTrashUntrash(String noteId, String token) throws UserServiceException;
	 public Response deleteinTrash(String noteId, String token) throws UserServiceException;
	 public Response retrieveNote(String token, String noteId);
	 public Response deleteNote(String token, String noteId);
	// public Response setReminder(String token,String noteId, LocalDateTime reminder) throws UserServiceException;
	 Response restoreTrashNotes(String token) throws UserServiceException;
	 Response getNoteByUserId(String token,String noteId);
	 Response setReminder(String token, String noteId, ReminderDTO reminder) throws UserServiceException;
	
}
