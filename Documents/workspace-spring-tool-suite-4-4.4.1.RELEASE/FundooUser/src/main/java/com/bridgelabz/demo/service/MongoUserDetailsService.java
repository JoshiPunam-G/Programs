package com.bridgelabz.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.repository.UserRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService{
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repository.findByEmail(email);

	    if(user == null) {
	      throw new UsernameNotFoundException("User not found");
	    }

	    List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

	    return (UserDetails) new User(user.getUsername(), user.getPassword(), email, authorities);
	
	}

}
