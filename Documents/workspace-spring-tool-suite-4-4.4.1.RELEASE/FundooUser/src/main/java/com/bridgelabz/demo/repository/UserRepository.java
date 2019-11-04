/**
 * Purpose : Define interface for User and extending MonoRepository 
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019   
 */


package com.bridgelabz.demo.repository;




import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.model.UserDTO;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);

	public User findByEmail(String email);
	
	public void save(UserDTO userdto);
}
