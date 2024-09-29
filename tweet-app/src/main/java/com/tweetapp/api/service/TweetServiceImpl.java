package com.tweetapp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetapp.api.exception.TweetDoesNotExistsException;
import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.UserModel;
import com.tweetapp.api.repository.TweetRepository;
import com.tweetapp.api.repository.UserRepository;
import com.tweetapp.api.util.TweetConstants;

@Service
public class TweetServiceImpl implements TweetService {

	@Autowired
	TweetRepository tweetRepository;

	@Autowired
	UserRepository userRepository;

	// To post a new tweet
	@Override
	public Tweet postTweetByUsername(Tweet tweet, String username) {

		UserModel user = userRepository.findByUsername(username);
		tweet.setUser(user);
		return tweetRepository.save(tweet);

	}

	// To update an existing tweet
	@Override
	public Tweet updateTweet(Tweet tweet) throws TweetDoesNotExistsException {

		Optional<Tweet> optionalTweet = tweetRepository.findById(tweet.getId());
		
		if (optionalTweet.isPresent()) {
			return tweetRepository.save(tweet);
		} else {
			throw new TweetDoesNotExistsException(TweetConstants.TWEET_DOES_NOT_EXISTS);
		}

	}

	// To reply on a tweet
	@Override
	public Tweet replyTweetById(Tweet replyTweet, String parentTweetId) throws TweetDoesNotExistsException {

		Optional<Tweet> optionalTweet = tweetRepository.findById(parentTweetId);

		if (optionalTweet.isPresent()) {
			List<Tweet> replies = optionalTweet.get().getReplies();
			replies.add(replyTweet);
			tweetRepository.save(optionalTweet.get());
		} else {
			throw new TweetDoesNotExistsException(TweetConstants.TWEET_DOES_NOT_EXISTS);
		}

		return replyTweet;
	}

	// To return all the tweets
	@Override
	public List<Tweet> getAllTweets() throws TweetDoesNotExistsException {

		List<Tweet> tweets = tweetRepository.findAll();

		if (tweets.isEmpty()) {
			throw new TweetDoesNotExistsException(TweetConstants.TWEET_DOES_NOT_EXISTS);
		}
		return tweets;
	}

	// To return the tweets of a particular user
	@Override
	public List<Tweet> getAllTweetsByUsername(String username) throws TweetDoesNotExistsException {

		List<Tweet> tweets = tweetRepository.findByUserUsername(username);
		
		if (tweets.isEmpty()) {
			throw new TweetDoesNotExistsException(TweetConstants.TWEET_DOES_NOT_EXISTS);
		}
		return tweets;

	}

	// To delete an existing tweet
	@Override
	public void deleteTweetById(String tweetId) throws TweetDoesNotExistsException {

		Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

		if (optionalTweet.isPresent()) {
			tweetRepository.deleteById(tweetId);
		} else {
			throw new TweetDoesNotExistsException(TweetConstants.TWEET_DOES_NOT_EXISTS);
		}

	}

	// To like a tweet
	@Override
	public void likeTweetById(String tweetId) throws TweetDoesNotExistsException {

		Optional<Tweet> optionalTweet = tweetRepository.findById(tweetId);

		if (optionalTweet.isPresent()) {
			optionalTweet.get().setLikes(optionalTweet.get().getLikes() + 1);
			tweetRepository.save(optionalTweet.get());
		} else {
			throw new TweetDoesNotExistsException(TweetConstants.TWEET_DOES_NOT_EXISTS);
		}

	}
}
