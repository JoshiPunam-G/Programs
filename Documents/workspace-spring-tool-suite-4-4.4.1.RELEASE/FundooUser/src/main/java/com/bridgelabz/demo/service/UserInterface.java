package com.bridgelabz.demo.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.demo.config.Response;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.model.UserDTO;

public interface UserInterface  {
	
	public User create(@RequestBody User user);
	public boolean login(UserDTO userdto) throws Exception ;
	public Response register(User user);
	public User update(String username, String password, String email) throws Exception;
	public User delete(String email) throws Exception;
	public List<User> findAll();
	public User getByUsername(String username);
	public void deleteAll();
}
