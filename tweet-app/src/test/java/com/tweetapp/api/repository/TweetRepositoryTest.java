package com.tweetapp.api.repository;

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

import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.UserModel;
import com.tweetapp.api.repository.TweetRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class TweetRepositoryTest {
	
	@Autowired
	private TweetRepository tweetRepo;
	
	@Test
	void findByUserUsernameTest() {
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet tweet = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime),12L,new UserModel("test", "test", "test", "tset", "test", "test", "Test"), new ArrayList<Tweet>(),"test");
		tweetRepo.save(tweet);
		List<Tweet> actualResult = tweetRepo.findByUserUsername("test");
		assertNotNull(actualResult);
	}
}
