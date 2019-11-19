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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bridgelabz.fundoo.Utility.TokenUtil;
import com.bridgelabz.fundoo.exception.NotFoundException;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.model.EmailModel;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseStatus;
import com.bridgelabz.fundoo.user.dto.PasswordDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;

@Service
@PropertySource("user.properties")
public class UserService implements UserInterface {
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
    	Response response;
		User user = repository.findByEmail(userdto.getEmail());
		System.out.println(user);
		boolean flag=encoder.matches(userdto.getPassword(), user.getPassword());
		if (flag) {
			String tokengenerate=TokenUtil.createtoken(user.getId());
			System.out.println(tokengenerate);
			response=ResponseStatus.tokenstatusInformation(environment.getProperty("status.login.success"),
		    		Integer.parseInt(environment.getProperty("status.success.code")),tokengenerate);	
			response.setStatusMessage(environment.getProperty("status.login.success"));
		}
		else
		{
			response=ResponseStatus.statusInformation(environment.getProperty("status.login.incorrectpassword"),
		    		Integer.parseInt(environment.getProperty("status.invalid.details")));	
			response.setStatusMessage(environment.getProperty("status.login.incorrectpassword"));
		}
		return response;
	}

	/**
	 * Purpose :Password Encryption for registration
	 * 
	 * @param user
	 * @return
	 */
    @Override
    public Response register(@RequestBody RegisterDTO userdto) { //throws NotFoundException {
		Response response;
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
		    response=ResponseStatus.statusInformation(environment.getProperty("status.success.register"),
		    		Integer.parseInt(environment.getProperty("status.success.code")));	
			response.setStatusMessage(environment.getProperty("status.success.register"));
			return response;
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
		
    	User user=repository.findByEmail(registerdto.getEmail());
    	Response response;
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
    		
    		 response=ResponseStatus.statusInformation(environment.getProperty("status.success.update"),
 		    		Integer.parseInt(environment.getProperty("status.success.code")));	
 			response.setStatusMessage(environment.getProperty("status.success.updated"));
 			return response;
    		
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
    	
		EmailModel emailmodel = new EmailModel();

		User user = repository.findByEmail(userdto.getEmail()); // "chaudharipratibha26@gmail.com"
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
				 response=ResponseStatus.statusInformation(environment.getProperty("status.success.sendmail"),
		 		    		Integer.parseInt(environment.getProperty("status.success.code")));	
		 			response.setStatusMessage(environment.getProperty("status.success.sendmail"));
		 			return response;
				
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
    	Response response=null;
    	
		String userid = tokenutil.decodetoken(token);
		Optional<User> user = repository.findById(userid);
		System.out.println("in reset");
		if (passdto.getNewpassword().equals(passdto.getConfirmpassword())) {
			user.get().setPassword(encoder.encode(passdto.getNewpassword()));
			repository.save(user.get());
			System.out.println("reset password");
			response=ResponseStatus.statusInformation(environment.getProperty("status.resetsuccess.password.message"),
 		    		Integer.parseInt(environment.getProperty("status.success.code")));	
 			response.setStatusMessage(environment.getProperty("status.resetsuccess.password.message"));
 			return response;
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
	public User delete(String email) throws Exception {
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new Exception("Email not found");
		} else {
			repository.delete(user);
		}
		return user;
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
       	System.out.println("get All");
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
   		return repository.findByUsername(username);
   	}

   	/**
   	 * Purpose :Implementation For Delete All USer
   	 */
       @Override
   	public void deleteAll() {
       	
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
	
	

}