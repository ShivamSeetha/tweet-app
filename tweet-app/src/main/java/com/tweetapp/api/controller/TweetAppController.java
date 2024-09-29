package com.tweetapp.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.api.messaging.ProducerService;
import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.UserModel;
import com.tweetapp.api.model.UserResponse;
import com.tweetapp.api.service.TweetService;
import com.tweetapp.api.service.UserService;
import com.tweetapp.api.util.TweetConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping(value = TweetConstants.URL)
public class TweetAppController {

	@Autowired
	UserService userService;

	@Autowired
	TweetService tweetService;

	@Autowired
	ProducerService producerService;


	/**
	 * To login a registered user
	 * 
	 * @param user
	 * @return logged in user
	 * @throws Exception
	 */
	@ResponseBody
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserModel user) throws Exception {

		producerService.sendMessage("Login request received for user");
		log.info("Requesting tokenManager to generate JWT Token by userDetails object for username: {}",
				user.getUsername());

		log.info("JWT Token generated successfully for the user: {}", user.getUsername());

		return new ResponseEntity<>(
				userService.loginUser(user.getUsername(), user.getPassword()),
				HttpStatus.OK);

	}

	/**
	 * To register a user
	 * 
	 * @param user
	 * @return registered user
	 */
	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody UserModel user) {

		producerService.sendMessage("Registration request received for user: " + user.getUsername());
		log.info("Successfully registered the user with userId: {}", user.getId());
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}

	/**
	 * To reset the password of a registered user
	 * 
	 * @param user
	 * @return new password
	 */
	@ResponseBody
	@PostMapping("/reset")
	public Map<String, String> resetUserPassword(@RequestBody UserModel user) {

		producerService.sendMessage("Reset password request received for user: " + user.getUsername());
		log.info("Password reset successfull for the userId: {}", user.getId());
		return new HashMap<>(userService.resetPassword(user.getUsername(), user.getPassword()));

	}

	/**
	 * To retrieve all the tweets
	 * 
	 * @return all the tweets
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Tweet>> getAllTweets() {

		log.info("Retrieving all the tweets.");
		producerService.sendMessage("Received request to send all the tweets");
		return new ResponseEntity<>(tweetService.getAllTweets(), HttpStatus.OK);

	}

	/**
	 * To retrieve all the users
	 * 
	 * @return all the users
	 */
	@GetMapping("/users/all")
	public ResponseEntity<List<UserModel>> getAllUsers() {

		log.info("Retrieving all the users.");
		producerService.sendMessage("Received request to send all the users.");
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

	}

	/**
	 * To search a user by the username
	 * 
	 * @param username
	 * @return details of a particular user
	 */
	@GetMapping("/user/search/{username}")
	public ResponseEntity<List<UserModel>> searchUser(@PathVariable("username") String username) {

		log.info("Request to fetch the user profile for user: {}", username);
		producerService.sendMessage("Received request to search user with username: " + username);
		return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);

	}

	/**
	 * To get the tweets of a user who has already posted the tweets
	 * 
	 * @param username
	 * @return tweets of a particular user
	 */
	@GetMapping("/{username}")
	public ResponseEntity<List<Tweet>> getAllTweetsByUser(@PathVariable("username") String username) {

		log.info("Retrieving all the tweets posted by the user");
		producerService.sendMessage("Received request to get all tweets for the user: " + username);
		return new ResponseEntity<>(tweetService.getAllTweetsByUsername(username), HttpStatus.OK);

	}

	/**
	 * To post new tweets
	 * 
	 * @param username
	 * @param tweet
	 * @return new tweet
	 */
	@PostMapping("/{username}/add")
	public ResponseEntity<Tweet> postTweetByUser(@PathVariable("username") String username, @RequestBody Tweet tweet) {

		log.info("Creating new tweet");
		producerService.sendMessage("Received request to add tweet of user with username: " + username);

		try {
			return new ResponseEntity<>(tweetService.postTweetByUsername(tweet, username), HttpStatus.CREATED);

		} catch (Exception e) {
			log.error("Error occured in adding new tweet for user: {}", username);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * To update the tweets
	 * 
	 * @param username
	 * @param tweetId
	 * @param tweet
	 * @return updated tweet
	 */
	@PutMapping("/{username}/update/{id}")
	public ResponseEntity<Tweet> updateTweetByUser(@PathVariable("username") String username,
			@PathVariable("id") String tweetId, @RequestBody Tweet tweet) {

		log.info("Updating the tweet");
		producerService.sendMessage(
				"Received request to update tweet with tweet id" + tweetId + "of user with username: " + username);
		return new ResponseEntity<>(tweetService.updateTweet(tweet), HttpStatus.OK);

	}

	/**
	 * To delete the tweets
	 * 
	 * @param username
	 * @param tweetId
	 * @return deleted tweet
	 */
	@DeleteMapping("/{username}/delete/{id}")
	public ResponseEntity<HttpStatus> deleteTweetByUser(@PathVariable("username") String username,
			@PathVariable("id") String tweetId) {

		log.info("Deleting the tweet");
		producerService.sendMessage("Request to delete tweet with tweetId: " + tweetId + " by the user: " + username);
		tweetService.deleteTweetById(tweetId);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	/**
	 * To like the tweets
	 * 
	 * @param username
	 * @param tweetId
	 * @return like of tweet
	 */
	@PutMapping("/{username}/like/{id}")
	public ResponseEntity<HttpStatus> likeTweetByUser(@PathVariable("username") String username,
			@PathVariable("id") String tweetId) {

		log.info("Request to like the tweet by the user: {} on tweet with TweetId: {} ", username, tweetId);
		producerService
				.sendMessage("Request to perform like by the user: " + username + " on tweet with Tweet id " + tweetId);

		tweetService.likeTweetById(tweetId);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	/**
	 * To post reply on the tweet
	 * 
	 * @param username
	 * @param tweetId
	 * @param replyTweet
	 * @return reply of tweet
	 */
	@PostMapping("/{username}/reply/{id}")
	public ResponseEntity<Tweet> replyTweetByUser(@PathVariable("username") String username,
			@PathVariable("id") String tweetId, @RequestBody Tweet replyTweet) {

		log.info("Adding reply by the user: {} on the tweet with tweetId: {}", username, tweetId);
		producerService.sendMessage("Request to add reply by the user");
		return new ResponseEntity<>(tweetService.replyTweetById(replyTweet, tweetId), HttpStatus.OK);

	}

	/**
	 * To check the status of microservice
	 * 
	 * @return status of app
	 */
	@GetMapping("/health-check")
	public String healthCheck() {
		return TweetConstants.STATUS;
	}
}
