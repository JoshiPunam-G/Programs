/**
 * Purpose : Service class for implementation of register , login , update , delete User data.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019   
 */
package com.bridgelabz.demo.service;
import java.util.List;

import java.util.Optional;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.bridgelabz.demo.Utility.TokenUtil;
import com.bridgelabz.demo.config.Response;
import com.bridgelabz.demo.model.EmailModel;
import com.bridgelabz.demo.model.PasswordDTO;
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
	
	 @Autowired 
	 private Response response;
	  
	 @Autowired 
	 private EmailService emailservice;
	 
	 @Autowired
	 TokenUtil tokenutil;
	 /**
		 *Purpose : API for Login Using email and password
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
			  user1.setUsername(user1.getUsername());
	    	   user1.setEmail(user1.getEmail());
		       user1.setPassword(encoder.encode(user1.getPassword()));
		       repository.save(user1); }
		       return response; 
		  
		  }
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
	 * Purpose :API for User Registration
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	
	public User create(@RequestBody User user) {
		return repository.save(user);
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
	   * Purpose : API Of Forgot Password 
	 * 
	   * @param emailmodel
	   * @param user
	   * @return
	 * @throws Exception 
	 * @throws MessagingException 
	   */
	 public boolean forgetPassword(UserDTO userdto) throws Exception 
	 {
		 EmailModel emailmodel=new EmailModel();
		 
		 User user=repository.findByEmail(userdto.getEmail());    //"chaudharipratibha26@gmail.com"
		 System.out.println("User :"+userdto.getEmail());
		 if(user==null)
		 {
			 System.out.println("User Not Found");
		 }
		 else
		 {
			 System.out.println("User Found");
			 emailmodel.setTo(userdto.getEmail());
			 emailmodel.setFrom("joshipunam207@gmail.com");
			 emailmodel.setSubject("Email for Reset password ");
			 try
			 {
			   // emailservice.sendEmail();
			   emailmodel.setBody(emailservice.getLink("http://localhost:8080/resetpassword/", user.getId()));
			   emailservice.sendEmail(emailmodel);
			 }
			 catch(SignatureVerificationException e )
			 {
				e.printStackTrace();
			 }
		 }
		 return true;
	 }
	 
	 /**
	  * Purpose :API for ResetPassword
	  * @param token
	  * @param passdto
	  * @return
	  */
	 public boolean resetPassword(String token ,PasswordDTO passdto)
	 {
		String userid=tokenutil.decodetoken(token);
		Optional<User> user=repository.findById(userid);
		if(passdto.getNewpassword().equals(passdto.getConfirmpassword()))
		{
			user.get().setPassword(encoder.encode(passdto.getNewpassword()));
			repository.save(user.get());
		    System.out.println("Reset Password");
		 return true;
		}
		System.out.println("reset password Failure");
		return false;
	 }	
}