package com.tweetapp.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweetapp.api.model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {

	List<UserModel> findByUsernameContaining(String username);

	UserModel findByUsername(String username);

}
