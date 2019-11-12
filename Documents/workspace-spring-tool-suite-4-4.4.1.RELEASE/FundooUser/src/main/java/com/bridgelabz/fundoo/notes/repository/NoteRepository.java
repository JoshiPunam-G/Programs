package com.bridgelabz.fundoo.notes.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.notes.model.Note;


@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
	
	public Note findByNoteId(String noteId) ;
	
	public Note findByUserIdAndNoteId(String noteId, String noteId2);
	
	public Optional<Note> findById(String noteID);
	
	public Note findByNoteId(String noteId,String token);
	
	public Optional<Note> deleteByNoteId(String noteId );
}
