package com.bridgelabz.user.testcases;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import com.amazonaws.services.lambda.model.Environment;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.UserService;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= { TestContext.class,WebApplicationContext.class})
@SpringBootTest
public class UserServiceJunit {
	
	@InjectMocks
    private UserService userservice;
	
	@MockBean
	private UserRepository userrepository;
	
	@MockBean
	private ModelMapper mapper;
	
	@MockBean
	private PasswordEncoder encoder;
	
	@MockBean
	private Response response;
	
	@MockBean
	private Environment environment;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
//	
//	User user=new User("100","punam" ,"punam@123" ,"punamsj20@gmail.com" ,true);
//	/**
//	 * Purpose :Test Case For Registration
//	 */
//	@Test
//	public void registerTest() {
//        
//		
//		RegisterDTO registrationdto =new RegisterDTO();
//      
//		registrationdto.setEmail("punamsj20@gmail.com");
//		registrationdto.setUsername("punam");
//		registrationdto.setPassword("punam@123");
//    	user.setId("100");
//
//		System.out.println(user);
//		System.out.println(registrationdto.getEmail());
//		System.out.println(user.getEmail());
//		assertEquals(registrationdto.getEmail(), user.getEmail());
//	}
//	 
//	/**
//	 * Purpose : Test case for Login
//	 */
//	
//	@Test
//   public void loginTest()
//   {
//        UserDTO userdto=new UserDTO();
//        userdto.setEmail("punamsj20@gmail.com");
//        userdto.setPassword("punam@123");
//        System.out.println(userdto.getEmail());
//        System.out.println(user.getEmail());
//        assertEquals(userdto.getEmail(),user.getEmail());
//        System.out.println(userdto.getPassword());
//        System.out.println(user.getPassword());
//        assertEquals(userdto.getPassword(),user.getPassword());
//   }
//	/**
//	 * Purpose :Test cases for Update
//	 */
//	
//	@Test
//	public void update()
//	{
//	   User user=new User();
//	   user.setPassword("123");
//	   UserDTO userdto=new UserDTO();
//	   userdto.setPassword("123");
//	   assertEquals(userdto.getPassword(),user.getPassword());
//	}
//	
//	
	
	@Test
	public void testEmployee() throws Exception {
		mockMvc.perform(post("/register")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.email").value("punamsj20@gmail.com")).andExpect(jsonPath("$.username").value("punam"))
				.andExpect(jsonPath("$.password").value("1234"));

	}

	}

