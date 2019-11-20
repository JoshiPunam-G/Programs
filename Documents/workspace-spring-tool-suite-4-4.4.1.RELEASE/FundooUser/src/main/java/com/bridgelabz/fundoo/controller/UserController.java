/**
 * Purpose : Implementation of Controller for Accessing Services implemented in service class .
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019  
 */
package com.bridgelabz.fundoo.controller;
import java.util.List;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.Utility.GlobalResource;
import com.bridgelabz.fundoo.exception.RecordNotFoundException;
import com.bridgelabz.fundoo.exception.UserServiceException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.notes.service.NoteService;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.UserService;
import com.bridgelabz.fundoo.user.dto.PasswordDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;

@RequestMapping("/user")
@RestController
public class UserController {
	
	private Logger logger=GlobalResource.getLogger(NoteService.class);

	@Autowired
	public UserService userservice;
	
	public UserRepository repository;
	
	/**
	 * Purpose :Implementation for User Login
	 * @throws Exception 
	 * 
	 * @throws Exceptions
	 */
	
	@GetMapping("/login")
	public ResponseEntity<Response> login(@RequestBody UserDTO userdto) throws UserServiceException  {
		logger.info("login");
		Response response=userservice.login(userdto);
		if(response!=null)
		{
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * Purpose : Implementation for User Registration
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/register")	
	public ResponseEntity<Response> register(@RequestBody RegisterDTO userdto){ //throws UserServiceException  {
	    logger.info("register");
		Response response=userservice.register(userdto);
		if(response!=null)
		{
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
		}
	}
	

	/**
	 * Purpose :Implementation for Update All User
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 * @throws Exception
	 */
	
	  @PutMapping("/update") 
	  public ResponseEntity<Response> update(@RequestBody RegisterDTO registerdto)throws Exception {
		 logger.info("update");
		  Response response=userservice.update(registerdto);
	   	 return new ResponseEntity<Response>(response,HttpStatus.OK);  
	  
	  }
	 

	/**
	 * Purpose :Implementation of Forgot password
	 * 
	 * @param userdto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/forgetPassword")
	public ResponseEntity<Response> forgetPassword(@RequestBody UserDTO userdto) throws Exception {
		logger.info("forgetPassword");;
		Response response=userservice.forgetPassword(userdto);
		if(response!=null) {
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else
		{
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Purpose : Implementation of ResetPassword
	 * 
	 * @param token
	 * @param passdto
	 * @return
	 */
	
	@PutMapping("/resetPassword/{token}")
	public ResponseEntity<Response> resetPassword(@PathVariable(name = "token") String token, @RequestBody PasswordDTO passdto) {
		logger.info("resetPassword");
		Response response=userservice.resetPassword(token, passdto);
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	/**
	 * Purpose : Implementation for Retrieve All User
	 * 
	 * @param username
	 * @return
	 */

	//@RequestMapping(value="/get" ,method = RequestMethod.GET)
	@GetMapping("/get")
	public User get(@RequestParam String username) throws RecordNotFoundException, UserServiceException 
	{
		logger.info("retrieve user");
			User user=userservice.getByUsername(username);
			if(user==null)
			{
				throw new RecordNotFoundException("User Not Found");
			}
			else
			{
				throw new UserServiceException("Internal Server Exception ");
			}
	}

	/**
	 * Purpose : Implementation for Retrieve All User data
	 * 
	 * @return
	 */
	@GetMapping("/getAll")
	public List<User> getAll() {
		System.out.println("get All");
		return userservice.findAll();
	}

	/**
	 * Purpose :Implementation for Delete user By username
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/delete")
	public ResponseEntity<Response> delete(@RequestParam String email) throws Exception {
		logger.info("delete user");
		Response response=userservice.delete(email);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 * Purpose : Implementation for Delete All User
	 * 
	 * @return
	 */
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		logger.info("Delete All User");
		userservice.deleteAll();
		return "Record Deleted ";
	}

//	
//	@Cacheable(value = "user", key = "#email")
//	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
//	public User getUser(@PathVariable String email) {
//		System.out.println("Retrive User");
//		return repository.findByEmail(email);
//	}

	@CacheEvict(value = "user", allEntries = true)
	@DeleteMapping("/{email}")
	public void deleteUserByID(@PathVariable String email) {
		repository.deleteById(email);
	}

	@CachePut(value = "user", key = "#user.username")
	@PutMapping("/updateUser")
	public User updatePersonByUsername(@RequestBody User user) {
		repository.save(user);
		return user;
	}

}