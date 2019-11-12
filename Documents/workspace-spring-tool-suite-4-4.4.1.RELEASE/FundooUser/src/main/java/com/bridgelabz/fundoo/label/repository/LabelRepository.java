package com.bridgelabz.fundoo.label.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.label.model.Label;

@Repository
public interface LabelRepository extends MongoRepository<Label, String>{
	
	public Label findByLabelIdAndUserId(String LabelId,String userId);
	
	public Optional<Label> findByUserIdAndLabelName(String userId,String labelName);
	
	public List<Label> findByUserId(String UserId);

}
