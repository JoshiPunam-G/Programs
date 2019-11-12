package com.bridgelabz.fundoo.service;

import java.util.List;



import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.fundoo.exception.NotFoundException;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.user.dto.PasswordDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;

public interface UserInterface  {
	
	public User create(@RequestBody User user);
	public Response login(UserDTO userdto) throws Exception ;
	public Response register(RegisterDTO userdto);
	public User delete(String email) throws Exception;
	public List<User> findAll();
	public User getByUsername(String username);
	public void deleteAll();
	Response forgetPassword(UserDTO userdto) throws Exception;
	Response resetPassword(String token, PasswordDTO passdto);
	Response update(RegisterDTO registerdto) throws Exception;
	
}
