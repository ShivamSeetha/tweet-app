package com.tweetapp.api.model;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.UserModel;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class TweetTest {
	@Test
	public void testTweet() {

		Tweet tweet = new Tweet();
		LocalDate currentDate = LocalDate.now();
		LocalTime currentTime = LocalTime.now();
		Tweet tweet1 = new Tweet("test", "test", LocalDateTime.of(currentDate, currentTime), 12L, new UserModel(),
				new ArrayList<Tweet>(), "test");

		tweet1.getId();
		tweet1.getLikes();
		tweet1.getPostDate();
		tweet1.getReplies();
		tweet1.getTweet();
		tweet1.getTweetTag();
		tweet1.getUser();

		tweet.setId("test");
		tweet.setLikes(2l);
		tweet.setPostDate(LocalDateTime.of(currentDate, currentTime));
		tweet.setReplies(new ArrayList<Tweet>());
		tweet.setTweet("test");
		tweet.setTweetTag("test");
		tweet.setUser(new UserModel());

		System.out.println(tweet.toString());

	}
}
