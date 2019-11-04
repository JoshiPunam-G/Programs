/**
 * Purpose : Service class for implementation of register , login , update , delete User data.
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019   
 */


package com.bridgelabz.demo.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.demo.config.Response;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.model.UserDTO;
import com.bridgelabz.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private Response response;
	
	

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
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean login(UserDTO userdto) throws Exception {
		User user = repository.findByEmail(userdto.getEmail());
		System.out.println("user: " + user);
		if (user == null) {
			return false;
		} else if (user.getEmail().contentEquals(userdto.getEmail()))
			return true;
		else
			return false;
		
		/*
		 * List<SimpleGrantedAuthority> authorities = Arrays.asList(new
		 * SimpleGrantedAuthority("user"));
		 * 
		 * return new User(user.getUsername(), user.getPassword(), authorities);
		 */

	}
	public Response register(UserDTO userdto)
	{
		User user=repository.findByEmail(userdto.getEmail());
		System.out.println(user);
		if(user.isPresent())
		{
			System.out.println("Duplicate Entry");
			return response;
		}
		else
		{
			userdto.setEmail(userdto.getEmail());
			userdto.setPassword(encoder.encode(userdto.getEmail()));
			repository.save(userdto);
		}
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
	
	

}
