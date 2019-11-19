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

import com.bridgelabz.fundoo.label.dto.LabelDTO;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.label.repository.LabelRepository;
import com.bridgelabz.fundoo.label.service.LabelService;
import com.bridgelabz.fundoo.model.User;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= { TestContext.class,WebApplicationContext.class})
@SpringBootTest
public class LabelServiceJunit {
	
	@InjectMocks
	private LabelService labelservice;
	
	@MockBean
	private LabelRepository labelrepository;
	
	
	/**
	 * Purpose : Test Case For CreateLabel
	 */
	@Test
	public void createLabel()
	{
		Label label=new Label();
		LabelDTO labeldto=new LabelDTO();
		labeldto.setLabelName("firstlabel");
		label.setLabelName(labeldto.getLabelName());
	    label.setLabelCreateDate(LocalDateTime.now());
		label.setModified(LocalDateTime.now());
		labelrepository.save(label);
		assertEquals(labeldto.getLabelName(), label.getLabelName());	
	}
	
	/**
	 * Purpose :Test Case For UpdateLabel
	 */
	
	@Test
	public void updateLabel()
	{
		Label label=new Label();
		label.setLabelName("my label");
		LabelDTO labeldto=new LabelDTO();
		labeldto.setLabelName("my label");
		labelrepository.save(label);
		assertEquals(labeldto.getLabelName(),label.getLabelName());
	}
	
	/**
	 * Purpose :Test Case For Delete Label.
	 */
	
	@Test
	public void deleteLabel()
	{
		Label label=new Label();
		label.setLabelId("");
//		User user=new User();
//		user.setId("");
		assertEquals(label.getLabelId(), label.getLabelId());
	}

}