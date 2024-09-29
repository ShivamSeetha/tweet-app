package com.tweetapp.api.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.tweetapp.api.util.TweetConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public final class ProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 
	 * @param kafkaTemplate
	 */
	public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	/**
	 * 
	 * @param message
	 */
	public void sendMessage(String message) {
		log.info("Producing the message:=> {}", message);

		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TweetConstants.TOPIC_NAME,
				message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message = [ {} ] due to : {}", message, ex.getMessage());
			}

			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Message sent = [ {} ] with offset = [ {} ]", message, result.getRecordMetadata().offset());
			}

		});
	}
}
