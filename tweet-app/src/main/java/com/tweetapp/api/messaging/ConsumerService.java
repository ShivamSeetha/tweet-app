package com.tweetapp.api.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tweetapp.api.util.TweetConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public final class ConsumerService {

	/**
	 * 
	 * @param message
	 */
	@KafkaListener(topics = TweetConstants.TOPIC_NAME, groupId = TweetConstants.GROUP_ID)
	public void consume(String message) {
		log.info("Consumed the message:=> {}", message);
	}

}