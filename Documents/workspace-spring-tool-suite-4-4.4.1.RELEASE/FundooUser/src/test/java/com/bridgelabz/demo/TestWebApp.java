package com.bridgelabz.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class TestWebApp extends FundooApplicationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	
	
	@Test
	public void setUser() throws Exception {
		mockMvc.perform(get("/getUser")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.name").value("pratibha")).andExpect(jsonPath("$.id").value(10))
				.andExpect(jsonPath("$.email").value("pratibha@gmail.com"));
		
	}
//	
//	@Test
//	public void loginUser()throws Exception
//	{
//		mockMvc.perform(get("/login")).andExpect(status().isOk())
//		.andExpect(content().contentType("application/json;charset=UTF-8"))
//		.andExpect(jsonPath("$.email").value("punamsj20@gmail.com")).andExpect(jsonPath("$.password").value("punam@123"));
//	}
}
