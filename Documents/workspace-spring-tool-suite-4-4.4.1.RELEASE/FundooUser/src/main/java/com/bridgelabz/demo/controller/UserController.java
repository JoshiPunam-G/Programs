/**
 * Purpose : Implementation of Controller for Accessing Services implemented in service class .
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019  
 */

package com.bridgelabz.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.demo.config.Response;
import com.bridgelabz.demo.model.EmailModel;
import com.bridgelabz.demo.model.PasswordDTO;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.model.UserDTO;
import com.bridgelabz.demo.service.EmailService;
import com.bridgelabz.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	public UserService userservice;
	
	@Autowired
	private BCryptPasswordEncoder bcrptpasswordencode;
	
	@Autowired 
	private EmailService mailservice;
	
	
	/**
	 * Purpose :Implementation for User Login
	 * 
	 * @throws Exceptions
	 */
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDTO userdto) throws Exception {
		if (userservice.login(userdto)) {
			return new ResponseEntity<String>("Login successfully..", HttpStatus.OK);
		}
		return new ResponseEntity<String>("User Not Found ,Register User ", HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * Purpose : Implementation for User Registration
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user)throws Exception {
		String encodedpassword=bcrptpasswordencode.encode(user.getPassword());
		User user1=new User(user.getId(),user.getUsername(),encodedpassword,user.getEmail());
		userservice.create(user1);
		return new ResponseEntity<>("Registration Successfully.!" ,HttpStatus.OK);
		//return user1.toString();
		
		
	}

	/**
	 * Purpose : Implementation for Retrieve All User
	 * 
	 * @param username
	 * @return
	 */

	@RequestMapping("/get")
	public User getUser(@RequestParam String username) {
		return userservice.getByUsername(username);
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
	public String update(@RequestParam String username, @RequestParam String password, @RequestParam String email)
			throws Exception {
		User user = userservice.update(username, password, email);
		return user.toString();
	}

	/**
	 * Purpose : Implementation for Retrieve All User data
	 * 
	 * @return
	 */
	@RequestMapping("/getAll")
	public List<User> getUser() {
		List<User> user = userservice.findAll();
		return user;
	}

	/**
	 *Purpose :Implementation for Delete user By username
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
	 * Purpose : Implementation for Delete All User
	 * 
	 * @return
	 */
	@RequestMapping("/deleteAll")
	public String deleteAll() {
		userservice.deleteAll();
		return "Record Deleted ";
	}
	
	
    
	/**
	 * Purpose :Implementation of Forgot password
	 * @param userdto
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/forgetPassword")
	public ResponseEntity<String> forgetPassword(@RequestBody UserDTO userdto) throws Exception {
		boolean response=userservice.forgetPassword(userdto);
		System.out.println(response);
		return new ResponseEntity<String>("Check mail for reset password." , HttpStatus.OK);
	}
	
	/**
	 * Purpose : Implementation of ResetPassword
	 * @param token
	 * @param passdto
	 * @return
	 */
	@PutMapping("/resetPassword/{token}")
	public  ResponseEntity<String> resetPassword(@PathVariable(name="token")String token ,@RequestBody PasswordDTO passdto)
	{
		boolean response=userservice.resetPassword(token, passdto);
		System.out.println(response);
		return new  ResponseEntity<String>("Password Has been Successfully Reset.!",HttpStatus.OK);
	}

	  /**
			 * Purpose :Implementation Of Java Mail sender
			 * @return
		 */
		    @RequestMapping("/simpleemail")
		    @ResponseBody
		  public  String sendEmail(EmailModel emailmodel) {
		     try {
		    	 mailservice.sendEmail(emailmodel);
		            return "Email Sent!";
		        }catch(Exception ex) {
		            return "Error in sending email: "+ex;
		        }
		    }


}