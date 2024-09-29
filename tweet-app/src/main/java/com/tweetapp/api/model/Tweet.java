package com.tweetapp.api.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tweets")
public class Tweet {

	@Id
	private String id;
	private String tweet;
	@CreatedDate
	private LocalDateTime postDate;
	private long likes;
	private UserModel user;
	private List<Tweet> replies;
	private String tweetTag;

}
