/**
 * Purpose : Service class for implementation of register , login , update , delete User data.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019   
 */
package com.bridgelabz.demo.service;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.bridgelabz.demo.config.Response;
import com.bridgelabz.demo.model.EmailModel;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.model.UserDTO;
import com.bridgelabz.demo.repository.UserRepository;
@Service
public class UserService implements UserInterface
{
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	  @Autowired private JavaMailSender sender;
	 
	 @Autowired 
	 private Response response;
	  
	/*
	 * @Autowired private EmailService emailservice;
	 */

	/**
	 * Purpose :Implementation of User Registration
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	
	public User create(@RequestBody User user) {
		return repository.save(user);
	}

	/**
	 *Purpose : Implementation of Login Using email and password
	 * @param password 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	
	public boolean login(UserDTO userdto) throws Exception {
		User user = repository.findByEmail(userdto.getEmail());
		System.out.println("user: " + user);
		if (user == null) {
			return false;
		}
		else if (user.getEmail().contentEquals(userdto.getEmail()) && encoder.matches(userdto.getPassword(), user.getPassword()))
		{	
			user.setEmail(user.getEmail());
		    user.setPassword(encoder.encode(user.getPassword()));
		    repository.save(user);
			return true;
		}
		else
			return false;
	
	}
	/**
	 * Purpose :Password Encryption for registration
	 * @param user
	 * @return
	 */
	
	  public Response register(User user) {
	  User user1=repository.findByEmail(user.getEmail()); 
	  System.out.println(user);
	  if(user1.isPresent()) 
	  { 
		  System.out.println("Duplicate Entry");
		  return response;
       }
	  else {
		  user1.setUsername(user.getUsername());
    	   user1.setEmail(user.getEmail());
	       user1.setPassword(encoder.encode(user.getPassword()));
	       repository.save(user1); }
	       return response; 
	  
	  }
	 
	/*
	 * public User register(User user) { User
	 * userfound=repository.findByEmail(user.getEmail()); if(userfound.isPresent())
	 * { System.out.println("There is an account with that email address "); //
	 * return response; } else { User newuser=new User();
	 * newuser.setEmail(user.getEmail());
	 * newuser.setPassword(encoder.encode(user.getPassword())); return
	 * repository.save(newuser);} //return response; return userfound; }
	 */
	
	/**
	 * Purpose :Implementation For Update  User Info
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 * @throws Exception
	 */
	
	public User update(String username, String password, String email) throws Exception {
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new Exception("user not found");
		}
		user.setPassword(password);
		user.setUsername(username);
		return repository.save(user);
	}

	/**
	 * Purpose :Implementation for Delete Particular User by Email
	 * @param username
	 * @return
	 * @return
	 * @throws Exception
	 */
	
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
	 *Purpose : Implementation For Retrieve All User
	 * @return
	 */
	
	public List<User> findAll() {
		return repository.findAll();
	}

	/**
	 *Purpose : Implementation For Find User By Username
	 * @param username
	 * @return
	 */
	
	public User getByUsername(String username) {
		return repository.findByUsername(username);
	}
 
	/**
	 * Purpose :Implementation For Delete All USer
	 */
	
	public void deleteAll() {
		repository.deleteAll();
	}

	
	  
	  
	  /**
	   * Purpose : Implementation Of Forgot Password 
	 * 
	   * @param emailmodel
	   * @param user
	   * @return
	 * @throws MessagingException 
	   */
	
	 public boolean forgetPassword(UserDTO userdto) 
	 {
		 EmailModel emailmodel=new EmailModel();
		 System.out.println("User :"+userdto.getEmail());
		 User user1=repository.findByEmail(userdto.getEmail());
		// Link link = new Link(".");
		 if(!user1.isPresent())
		 {
			 System.out.println("User Not Found");
		 }
		 else
		 {
			 System.out.println("User Found");
			 emailmodel.setTo(userdto.getEmail());
			 emailmodel.setFrom("joshipunam207@gmail.com");
			 emailmodel.setSubject("Email for forgot password ");
			 try
			 {
			   //emailmodel.setBody(emailservice.getLink("http://localhost:8080/resetpassword/", user1.getId()));
			 }
			 catch(Exception e)
			 {
				System.out.println(e.getMessage());
			 }
			 
		 }
		return false;
		 
	 }
}