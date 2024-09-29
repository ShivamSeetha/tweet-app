package com.tweetapp.api.service;

import java.util.List;

import com.tweetapp.api.exception.TweetDoesNotExistsException;
import com.tweetapp.api.model.Tweet;

public interface TweetService {

	Tweet postTweetByUsername(Tweet tweet, String username);

	Tweet updateTweet(Tweet tweet) throws TweetDoesNotExistsException;

	List<Tweet> getAllTweets() throws TweetDoesNotExistsException;

	List<Tweet> getAllTweetsByUsername(String username) throws TweetDoesNotExistsException;

	Tweet replyTweetById(Tweet replyTweet, String parentTweetId) throws TweetDoesNotExistsException;

	void deleteTweetById(String tweetId) throws TweetDoesNotExistsException;

	void likeTweetById(String tweetId) throws TweetDoesNotExistsException;

}
