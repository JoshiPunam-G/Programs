/**
 * Purpose : Define interface for User and extending MonoRepository 
 * Author  : Punam Joshi 
 * @version 1.0
 * @since   2-11-2019   
 */
package com.bridgelabz.fundoo.repository;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);

	public User findByEmail(String email);
	
	public Optional<User> findById(String email);

	public void save(Optional<User> user);
//
//	public User findOne(Long valueOf);
//
//	public void delete(String email);

}
