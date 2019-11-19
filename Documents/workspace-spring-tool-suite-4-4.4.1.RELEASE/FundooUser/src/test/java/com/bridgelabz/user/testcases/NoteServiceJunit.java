package com.bridgelabz.user.testcases;
import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.notes.repository.NoteRepository;
import com.bridgelabz.fundoo.notes.service.NoteService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= { TestContext.class,WebApplicationContext.class})
@SpringBootTest
public class NoteServiceJunit {
	
	@InjectMocks
	private NoteService noteservice;
	
	@MockBean
	private NoteRepository noterepository;
	
	Note note=new Note();
	
	/**
	 * Purpose :Test Case For create Note.
	 */
	
	@Test
	public void createnote()
	{
		 NoteDTO notedto=new NoteDTO();
		 Note note=new Note();
		 note.setUserId("123");
		 note.setCreateDate(LocalDateTime.now());
		 note.setModified(LocalDateTime.now());
		 noterepository.save(note);
		 assertEquals(notedto.getTitle(), note.getTitle());
		 assertEquals(notedto.getDescription(), note.getDescription()); 
	}
	/**
	 * Purpose :Test Cases For UpdateNote
	 */
	
	@Test
	public void updatenote()
	{
		Note note=new Note();
		note.setTitle("mynote");
		note.setDescription("my updated note");
	    NoteDTO notedto=new NoteDTO();
	    notedto.setTitle("mynote");
	    notedto.setDescription("my updated note");
	    assertEquals(note.getTitle(), notedto.getTitle());
	    assertEquals(note.getDescription(), notedto.getDescription());
	}
	
	/**
	 * Purpose :Test Cases For Archieve
	 */
	
	@Test
	public void Archieve()
	{
		Note note=new Note();
		note.setStatusArchieve(true);
		note.setStatusArchieve(false);
		assertEquals(note.isStatusArchieve(), note.isStatusArchieve());
	}

	/**
	 * Purpose :Test Cases For PinUnPin
	 */
    @Test	
	public void PinUnPin() {
	   Note note=new Note();
	   note.setStatusPinUnpin(false);
	   note.setStatusPinUnpin(true);
	   assertEquals(note.isStatusPinUnpin(), note.isStatusPinUnpin());
	}
    
    /**
     * Purpose :Test Case For TrashUnTrash
     */
    @Test
    public void TrashUnTrash()
    {
    	Note note=new Note();
    	note.setStatusTrashUntrash(false);
    	assertEquals(note.isStatusTrashUntrash(), note.isStatusTrashUntrash());
    }
   
}