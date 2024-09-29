package com.tweetapp.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tweetapp.api.exception.UserAlreadyExistsException;
import com.tweetapp.api.exception.UserDoesNotExistsException;
import com.tweetapp.api.model.UserModel;
import com.tweetapp.api.model.UserResponse;
import com.tweetapp.api.repository.UserRepository;
import com.tweetapp.api.util.TweetConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	// To create the user
	@Override
	public UserModel createUser(UserModel user) throws UserAlreadyExistsException {

		Optional<UserModel> userOptional = userRepository.findById(user.getId());

		if (userOptional.isEmpty()) {
			return userRepository.save(user);
		} else {
			throw new UserAlreadyExistsException(TweetConstants.USER_ALREADY_EXISTS);
		}

	}

	// To update the user
	@Override
	public UserModel updateUser(UserModel user) throws UserDoesNotExistsException {

		Optional<UserModel> userOptional = userRepository.findById(user.getId());

		if (userOptional.isPresent()) {
			return userRepository.save(user);
		} else {
			throw new UserDoesNotExistsException(TweetConstants.USER_DOES_NOT_EXISTS);
		}

	}

	// To fetch all the users
	@Override
	public List<UserModel> getAllUsers() throws UserDoesNotExistsException {
		
		List<UserModel> users = userRepository.findAll();
		
		if (users.isEmpty()) {
			throw new UserDoesNotExistsException(TweetConstants.USER_DOES_NOT_EXISTS);
		}
		return users;
	}

	// To fetch the details of user by username
	@Override
	public List<UserModel> getUserByUsername(String username) throws UserDoesNotExistsException {
		
		List<UserModel> users = userRepository.findByUsernameContaining(username);
		
		if (users.isEmpty()) {
			throw new UserDoesNotExistsException(TweetConstants.USER_DOES_NOT_EXISTS);
		}
		return users;
	}

	// To fetch the details of user by id
	@Override
	public Optional<UserModel> getUserById(String id) {
		return userRepository.findById(id);
	}

	// To reset the password of user
	@Override
	public Map<String, String> resetPassword(String username, String password) throws UserDoesNotExistsException {

		Map<String, String> newPassword = new HashMap<>();
		UserModel user = userRepository.findByUsername(username);
		
		if (ObjectUtils.isEmpty(user)) {
			throw new UserDoesNotExistsException(TweetConstants.USER_DOES_NOT_EXISTS);
		}
		
		user.setPassword(password);
		userRepository.save(user);

		newPassword.put(TweetConstants.NEW_PASSWORD, user.getPassword());
		newPassword.put(TweetConstants.RESET_STATUS, TweetConstants.SUCCESS);

		return newPassword;
	}

	// To login a user
	@Override
	public UserResponse loginUser(String username, String password) throws Exception {

		UserResponse response = new UserResponse();
		UserModel user = userRepository.findByUsername(username);

		if (user.getPassword().equals(password)) {
			response.setUser(user);
			response.setLoginStatus(TweetConstants.SUCCESS);
		} else {
			response.setLoginStatus(TweetConstants.FAILED);
		}

		return response;

	}

}
