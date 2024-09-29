package com.tweetapp.api.model;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.api.model.ExceptionResponse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

class ExceptionResponseTest {

	@Test
	public void testExceptionResponseTest() {

		ExceptionResponse exception = new ExceptionResponse();
		ExceptionResponse exceptionResponse = new ExceptionResponse("test", "test", new Date());

		exceptionResponse.getMessage();
		exceptionResponse.getDetails();
		exceptionResponse.getDateTime();

		exception.setMessage("test");
		exception.setDetails("test");
		exception.setDateTime(new Date());

		System.out.println(exception.toString());
	}
}
