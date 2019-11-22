package com.bridgelabz.user.testcases;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.notes.dto.NoteDTO;
import com.bridgelabz.fundoo.notes.model.Note;
import com.bridgelabz.fundoo.notes.repository.NoteRepository;
import com.bridgelabz.fundoo.notes.service.NoteService;
import com.bridgelabz.fundoo.response.Response;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= { TestContext.class,WebApplicationContext.class})
@SpringBootTest
public class NoteServiceJunit {
	
	@InjectMocks
	private NoteService noteservice;
	
	@MockBean
	private NoteRepository noterepository;
	
	@MockBean
	private ModelMapper mapper;
	
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//
//	@MockBean
//	private MockMvc mockMvc;
//
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
	Note note=new Note("123", "first note", "This is My First Note", null, false, false, false, "1234");
	String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiNWRjZjc2YjI0MmQ4ZTExOGFiZDU0OTgwIn0.fVtN3r56pYZIKvS19suINk_R9gWiGobP4N9DRNLxuio";
	/**
	 * Purpose :Test Case For create Note.
	 * @throws UserServiceException 
	 */
	@Test
	public void createnote() throws UserServiceException
	{
		 NoteDTO notedto=new NoteDTO();
		 notedto.setTitle("first note");
		 notedto.setDescription("This is My First Note");
		 System.out.println("In Create Note :"  + notedto.getTitle() + "," + notedto.getDescription());
		 String tokenNote="5dd7d7fc42d8e17cd636e2d1";
	   	 assertEquals(true, tokenNote.contentEquals(token));
		 when(mapper.map(notedto, Note.class)).thenReturn(note);
		 when(noterepository.save(note)).thenReturn(note);
		 Response response=noteservice.createNote(notedto, tokenNote);
		 System.out.println("---In The Note---"+response);
		 assertEquals(HttpStatus.ACCEPTED, noteservice.createNote(notedto,token).getStatuscode()); 
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
	
//	/**
//	 * Purpose :Test Cases For Archieve
//	 */
//	
//	@Test
//	public void Archieve()
//	{
//		Note note=new Note();
//		note.setStatusArchieve(true);
//		note.setStatusArchieve(false);
//		assertEquals(note.isStatusArchieve(), note.isStatusArchieve());
//	}
//
//	/**
//	 * Purpose :Test Cases For PinUnPin
//	 */
//    @Test	
//	public void PinUnPin() {
//	   Note note=new Note();
//	   note.setStatusPinUnpin(false);
//	   note.setStatusPinUnpin(true);
//	   assertEquals(note.isStatusPinUnpin(), note.isStatusPinUnpin());
//	}
//    
//    /**
//     * Purpose :Test Case For TrashUnTrash
//     */
//    @Test
//    public void TrashUnTrash()
//    {
//    	Note note=new Note();
//    	note.setStatusTrashUntrash(false);
//    	assertEquals(note.isStatusTrashUntrash(), note.isStatusTrashUntrash());
//    }
   
}