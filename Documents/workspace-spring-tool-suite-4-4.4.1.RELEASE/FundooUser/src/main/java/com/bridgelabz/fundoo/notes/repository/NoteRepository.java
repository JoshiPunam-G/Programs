package com.bridgelabz.fundoo.notes.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.notes.model.Note;


@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
	
	public Note findByNoteId(String noteId) ;
	
	public List<Note> findByUserId(String userId);

	public Note findByUserIdAndNoteId(String token, String noteId);
	
	public Optional<Note> findById(String noteID);
	
	public Note findByNoteId(String noteId,String token);
	
	public Optional<Note> deleteByNoteId(String noteId );
	
	//public List<Note> findByNoteId(String noteId);
}
