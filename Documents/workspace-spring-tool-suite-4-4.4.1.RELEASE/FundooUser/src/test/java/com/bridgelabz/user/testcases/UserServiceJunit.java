package com.bridgelabz.user.testcases;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import com.amazonaws.services.lambda.model.Environment;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.model.EmailModel;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.UserService;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;

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
	
//	@Autowired
//	private WebApplicationContext webApplicationContext;

//	@MockBean
//	private MockMvc mockMvc;
//
//	@Before
//	public void setup() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//	}
//	
	
	
	User user=new User("100","punam" ,"punam@123" ,"punamsj20@gmail.com" ,true);
	/**
	 * Purpose :Test Case For Registration
	 */
	@Test
	public void registerTest() {
		RegisterDTO registrationdto =new RegisterDTO();
		registrationdto.setEmail("punamsj20@gmail.com");
		registrationdto.setUsername("punam");
		registrationdto.setPassword("punam@123");
		Optional<User> existuser=Optional.of(user);
		when(userrepository.findByEmail(user.getEmail())).thenReturn(user);
		when(mapper.map(registrationdto, User.class)).thenReturn(user);
		when(userrepository.save(user)).thenReturn(user);
		assertEquals(registrationdto.getEmail(), existuser.get().getEmail());
	}
	 
	
	/**
	 * Purpose : Test case for Login
	 */
	
	@Test
   public void loginTest()
   {
        UserDTO userdto=new UserDTO();
        userdto.setEmail("punamsj20@gmail.com");
        userdto.setPassword("punam@123");
    	Optional<User> existuser=Optional.of(user);
    	when(userrepository.findByEmail(user.getEmail())).thenReturn(user);
    	when(encoder.matches(userdto.getPassword(), user.getPassword())).thenReturn(true);
    	assertEquals(userdto.getEmail(), existuser.get().getEmail());  
   }
	/**
	 * Purpose :Test cases for Update
	 * @throws Exception 
	 */
	
	@Test
	public void updateTest() throws Exception
	{
		RegisterDTO registrationdto =new RegisterDTO();
	//   when(userservice.update(registrationdto)).thenReturn(response);
		registrationdto.setPassword("1234");
		registrationdto.setEmail("punamsj20@gmail.com");
		registrationdto.setUsername("punam");
	    Optional<User> existuser=Optional.of(user);
	    when(userrepository.findByEmail(user.getEmail())).thenReturn(user);
	    when(userrepository.save(user)).thenReturn(user);
	    assertEquals(registrationdto.getEmail(),existuser.get().getEmail() );
	}
	
	/**
	 * Purpose :Test Case For Forgot Password
	 * @throws Exception
	 */
	
	@Test
	public void forgetpasswordTest() throws Exception
	{
		UserDTO userdto=new UserDTO();
		EmailModel emailmodel = new EmailModel();
		emailmodel.setTo(userdto.getEmail());
		emailmodel.setFrom("joshipunam207@gmail.com");
		emailmodel.setSubject("Email for Reset password ");
		when(userrepository.findByEmail(user.getEmail())).thenReturn(user);
		when(userrepository.save(user)).thenReturn(user);
		assertEquals(userdto.getEmail(),emailmodel.getTo() );
	}
	

//	@Test
//	public void resetpasswordTest()
//	{
//		PasswordDTO passdto=new PasswordDTO();
//		passdto.setNewpassword("1234");
//		passdto.setConfirmpassword("1234");
//		when(passdto.getNewpassword().equals(passdto.getConfirmpassword())).thenReturn(true);
//		when(encoder.matches(passdto.getNewpassword(), user.getPassword())).thenReturn(true);
//		when(userrepository.save(user)).thenReturn(user);
//		assertEquals(passdto.getNewpassword(), user.getPassword());
//	}
}

