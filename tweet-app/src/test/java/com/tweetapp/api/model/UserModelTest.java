package com.tweetapp.api.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.api.model.UserModel;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)

public class UserModelTest {
	@Test
	public void testUserTest() {
		UserModel user = new UserModel();
		UserModel user1 = new UserModel("test", "test", "test", "tset", "test", "test", "Test");

		user1.getContactNumber();
		user1.getId();
		user1.getFirstName();
		user1.getLastName();
		user1.getUsername();
		user1.getPassword();
		user1.getEmail();

		user.setContactNumber("test");
		user.setId("test");
		user.setFirstName("test");
		user.setLastName("test");
		user.setUsername("test");
		user.setPassword("test");
		user.setEmail("test");

		System.out.println(user.toString());

	}
}
