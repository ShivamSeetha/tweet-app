package com.tweetapp.api.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tweetapp.api.exception.UserAlreadyExistsException;
import com.tweetapp.api.exception.UserDoesNotExistsException;
import com.tweetapp.api.model.UserModel;
import com.tweetapp.api.model.UserResponse;

public interface UserService {

	UserModel createUser(UserModel user) throws UserAlreadyExistsException;

	UserModel updateUser(UserModel user) throws UserDoesNotExistsException;

	List<UserModel> getAllUsers() throws UserDoesNotExistsException;

	List<UserModel> getUserByUsername(String username) throws UserDoesNotExistsException;

	Optional<UserModel> getUserById(String id);

	Map<String, String> resetPassword(String username, String password);

	UserResponse loginUser(String username, String password) throws Exception;

}
