package com.bridgelabz.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.model.UserDTO;
import com.bridgelabz.demo.service.UserService;

@RestController
public class UserController {
	@Autowired
	public UserService userservice;

	/**
	 * create user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/register")
	public String create(@RequestBody User user) {
		userservice.create(user);
		return user.toString();
	}

	/**
	 * Retrieve All User
	 * 
	 * @param username
	 * @return
	 */

	@RequestMapping("/get")
	public User getUser(@RequestParam String username) {
		return userservice.getByUsername(username);
	}

	/**
	 * login
	 * 
	 * @throws Myexceptions
	 */
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDTO userdto) throws Exception {
		if (userservice.login(userdto)) {
			return new ResponseEntity<String>("Login successfully..", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Register...", HttpStatus.BAD_REQUEST);

	}

	/**
	 * Update All User
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/update")
	public String update(@RequestParam String username, @RequestParam String password, @RequestParam String email)
			throws Exception {
		User user = userservice.update(username, password, email);
		return user.toString();
	}

	/**
	 * Retrieve All User data
	 * 
	 * @return
	 */
	@RequestMapping("/getAll")
	public List<User> getUser() {
		List<User> user = userservice.findAll();
		return user;
	}

	/**
	 * Delete user By username
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam String email) throws Exception {
		User user = userservice.delete(email);
		return new ResponseEntity<String>("deleted successfully.." + user, HttpStatus.OK);
	}

	/**
	 * Delete All User
	 * 
	 * @return
	 */
	@RequestMapping("/deleteAll")
	public String deleteAll() {
		userservice.deleteAll();
		return "Record Deleted ";
	}

}
