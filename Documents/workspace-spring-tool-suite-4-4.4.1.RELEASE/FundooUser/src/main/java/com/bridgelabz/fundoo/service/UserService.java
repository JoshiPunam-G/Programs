/**
 * Purpose : Service class for implementation of register , login , update , delete User data.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019   
 */
package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.bridgelabz.fundoo.Utility.GlobalResource;
import com.bridgelabz.fundoo.Utility.TokenUtil;
import com.bridgelabz.fundoo.exception.NotFoundException;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.model.EmailModel;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.notes.service.NoteService;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseStatus;
import com.bridgelabz.fundoo.user.dto.PasswordDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;

@Service
@PropertySource("user.properties")
public class UserService implements UserInterface {
	
	private Logger logger=GlobalResource.getLogger(NoteService.class);
	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private Environment environment; 

	@Autowired
	private Response response;

	@Autowired
	private EmailService emailservice;

	@Autowired
	TokenUtil tokenutil;
	 
	@Autowired
	private ModelMapper mapper;

	/**
	 * Purpose : API for Login Using email and password
	 * 
	 * @param password
	 * @param username
	 * @return
	 * @throws Exception
	 */
    @Override
	public Response login(@RequestBody UserDTO userdto)//throws NotFoundException 
    {
    	logger.info("login");
		User user = repository.findByEmail(userdto.getEmail());
		System.out.println(user);
		boolean flag=encoder.matches(userdto.getPassword(), user.getPassword());
		if (flag) {
			String tokengenerate=TokenUtil.createtoken(user.getId());
			System.out.println(tokengenerate);
			return responseToken(environment.getProperty("status.login.success"), 100 ,tokengenerate);
		}
		else
		{
			return responseMessage(environment.getProperty("status.login.incorrectpassword"), 200);
		}
	}

	/**
	 * Purpose :Password Encryption for registration
	 * 
	 * @param user
	 * @return
	 */
    @Override
    public Response register(@RequestBody RegisterDTO userdto) { //throws NotFoundException {
		logger.info("register");
    	Optional<User> user=repository.findById(userdto.getEmail());
		
		if(user.isPresent())
		{
			throw new NotFoundException(environment.getProperty("Exception.message.notfound"));
		}
		else
		{		
		    User user1=mapper.map(userdto, User.class);
		    user1.setPassword(encoder.encode(user1.getPassword()));
		    repository.save(user1);
			return responseMessage(environment.getProperty("status.success.register"), 300);
		}
	}

	/**
	 * Purpose :Implementation For Update User Info
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 * @throws Exception
	 */

    @Override
	public Response update(RegisterDTO registerdto) throws Exception {
		logger.info("update ");
    	User user=repository.findByEmail(registerdto.getEmail());
    	if(user==null)
    	{
    		throw new NotFoundException(environment.getProperty("Exception.message.notfound"));
    	}
    	else
    	{
    		user.setEmail(registerdto.getEmail());
    		System.out.println(registerdto.getEmail());
    		user.setPassword(encoder.encode(registerdto.getPassword()));
    		System.out.println(encoder.encode(registerdto.getPassword()));
    		user.setUsername(registerdto.getUsername());
    		repository.save(user);
    		System.out.println(registerdto);
 			return responseMessage(environment.getProperty("status.success.update"), 200);
    	}
	}


	/**
	 * Purpose : API Of Forgot Password
	 * 
	 * @param emailmodel
	 * @param user
	 * @return
	 * @throws Exception
	 * @throws MessagingException
	 */
    @Override
	public Response forgetPassword(UserDTO userdto) throws Exception {
    	logger.info("forgetPassword");
		EmailModel emailmodel = new EmailModel();

		User user = repository.findByEmail(userdto.getEmail()); 
		System.out.println("User :" + userdto.getEmail());
		
		if (user == null) {
			System.out.println("User Not Found");
		} else {
			System.out.println("User Found");
			emailmodel.setTo(userdto.getEmail());
			emailmodel.setFrom("joshipunam207@gmail.com");
			emailmodel.setSubject("Email for Reset password ");
			try {
				emailmodel.setBody(emailservice.getLink("http://localhost:8080/resetpassword/", user.getId()));
				emailservice.sendEmail(emailmodel);
		 		return responseMessage(environment.getProperty("status.success.sendmail"),200);
				
			} catch (UserServiceException e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	/**
	 * Purpose :API for ResetPassword
	 * 
	 * @param token
	 * @param passdto
	 * @return
	 */
    @Override
	public Response resetPassword(String token, PasswordDTO passdto) {
    	logger.info("resetPassword");
    	Response response=null;
    	
    	String userid = TokenUtil.decodetoken(token);
		Optional<User> user = repository.findById(userid);
		
		if (passdto.getNewpassword().equals(passdto.getConfirmpassword())) {
			user.get().setPassword(encoder.encode(passdto.getNewpassword()));
			repository.save(user.get());
			System.out.println("reset password");
 			return responseToken(environment.getProperty("status.resetsuccess.password.message"), 200, userid);
		}
		System.out.println("reset password Failure");
		
		return response;
	}

	/**
	 * Purpose :Implementation for Delete Particular User by Email
	 * 
	 * @param username
	 * @return
	 * @return
	 * @throws Exception
	 */

    @Override
	public Response delete(String email) throws Exception {
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new Exception("Email not found");
		} else {
			repository.delete(user);
		}
		return responseMessage(environment.getProperty("status.resetsuccess.password.message"), 100);
		
	}
    

   	/**
   	 * Purpose :API for User Registration
   	 * 
   	 * @param username
   	 * @param password
   	 * @param email
   	 * @return
   	 */

        @Override
   	public User create(@RequestBody User user) {
   		return repository.save(user);
   	}

   	/**
   	 * Purpose : Implementation For Retrieve All User
   	 * 
   	 * @return
   	 */
       @Override
   	public List<User> findAll() {
       logger.info("Retrieve All User");
   		return repository.findAll();
   	}

   	/**
   	 * Purpose : Implementation For Find User By Username
   	 * 
   	 * @param username
   	 * @return
   	 */
       @Override
   	public User getByUsername(String username) {
    	   logger.info("getByUsername");
   		return repository.findByUsername(username);
   	}

   	/**
   	 * Purpose :Implementation For Delete All USer
   	 */
       @Override
   	public void deleteAll() {
       	logger.info("deleteAll");
   		repository.deleteAll();
   	}

	/**
	 * Purpose : throw exception
	 * 
	 * @return
	 * @throws UserServiceException
	 */
	public User USerException() throws UserServiceException {

		throw new UserServiceException();
	}
	
	
	/**
	 * Purpose : Implementation for sending response message
	 * @param statusmessage
	 * @param statuscode
	 * @return
	 */
	public Response responseMessage(String statusmessage,int statuscode)
	{
		Response response=ResponseStatus.statusInformation(statusmessage, statuscode);
		response.getStatusMessage();
		return response;
	}
	
	public Response responseToken(String statusmessage,int statuscode, String token)
	{
		Response response=ResponseStatus.tokenstatusInformation(statusmessage, statuscode, token);
		response.getStatusMessage();
		return response;
	}
	
	

}