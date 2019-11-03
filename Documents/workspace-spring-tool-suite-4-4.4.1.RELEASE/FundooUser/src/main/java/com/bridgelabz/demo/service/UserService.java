package com.bridgelabz.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.model.UserDTO;
import com.bridgelabz.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	

	/**
	 * Register User
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	public User create(@RequestBody User user) {
		return repository.save(user);
	}

	/**
	 * Login Using Username and password
	 * 
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

	}

	/**
	 * Update the User Info
	 * 
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
	 * Delete user by username
	 * 
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
	 * Retrieve All User
	 * 
	 * @return
	 */
	public List<User> findAll() {
		return repository.findAll();
	}

	/**
	 * Find User By Username
	 * 
	 * @param username
	 * @return
	 */

	public User getByUsername(String username) {
		return repository.findByUsername(username);
	}

	/**
	 * Delete All USer
	 */
	public void deleteAll() {
		repository.deleteAll();
	}

}
