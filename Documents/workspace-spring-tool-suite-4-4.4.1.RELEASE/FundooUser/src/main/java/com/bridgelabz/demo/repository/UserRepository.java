package com.bridgelabz.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);

	public User findByEmail(String email);

}
