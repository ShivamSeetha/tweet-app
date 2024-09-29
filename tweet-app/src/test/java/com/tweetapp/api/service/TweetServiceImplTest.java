package com.tweetapp.api.service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.api.exception.TweetDoesNotExistsException;
import com.tweetapp.api.exception.UserDoesNotExistsException;
import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.UserModel;
import com.tweetapp.api.repository.TweetRepository;
import com.tweetapp.api.service.TweetServiceImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class TweetServiceImplTest {
	
	@Autowired
	private TweetRepository tweetRepository;
	
	@Autowired
	private TweetServiceImpl tweetService;

	@Test
    public void getAllTweetsTest(){
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet tweet = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime),12L,new UserModel("test", "test", "test", "test", "test", "test", "Test"), new ArrayList<Tweet>(),"test");
		tweetRepository.save(tweet);
		List<Tweet> tweetList = tweetService.getAllTweets();
        assertNotNull(tweetList);
    }
	
	@Test
	public void updateTweetTest() throws UserDoesNotExistsException {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet tweet = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime),12L,new UserModel("test", "test", "test", "test", "test", "test", "Test"), new ArrayList<Tweet>(),"test");
		tweetRepository.save(tweet);
		Tweet tweet1 = tweetService.updateTweet(tweet);
        assertNotNull(tweet1);
	}
	
	@Test
	public void getAllTweetsByUsernameTest() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet tweet = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime),12L,new UserModel("test", "test", "test", "test", "test", "test", "Test"), new ArrayList<Tweet>(),"test");
		tweetRepository.save(tweet);
		assertNotNull(tweetService.getAllTweetsByUsername("test"));
	}
	
	@Test
	public void postTweetByUsername() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet tweet = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime),12L,new UserModel("test", "test", "test", "test", "test", "test", "Test"), new ArrayList<Tweet>(),"test");
		tweetService.postTweetByUsername(tweet, "test");
		 assertNotNull(tweet);
	}
	
	@Test
	public void deleteTweetByIdTest() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet tweet = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime),12L,new UserModel("test", "test", "test", "test", "test", "test", "Test"), new ArrayList<Tweet>(),"test");
		tweetRepository.save(tweet);
		tweetService.deleteTweetById("test");
	}
	
	@Test
	public void deleteTweetByIdTestException() throws TweetDoesNotExistsException {
		
		Tweet tweet = new Tweet();
		tweetRepository.save(tweet);
		assertThrows(TweetDoesNotExistsException.class, () -> tweetService.deleteTweetById(""));
	}

	
	@Test
	public void replyTweetByIdTest() throws Exception {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet replyTweet = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime),12L,new UserModel("test", "test", "test", "test", "test", "test", "Test"), new ArrayList<Tweet>(),"test");
		assertNotNull(tweetService.replyTweetById(replyTweet, "test"));
	}
	
	@Test
	public void likeTweetByIdTest() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet tweet = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime),12,new UserModel("test", "test", "test", "test", "test", "test", "Test"), new ArrayList<Tweet>(),"test");
		tweetRepository.save(tweet);
		tweetService.likeTweetById("test");
	}
}