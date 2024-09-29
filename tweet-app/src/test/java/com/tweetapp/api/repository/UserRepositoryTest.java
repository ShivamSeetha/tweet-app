package com.tweetapp.api.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tweetapp.api.model.UserModel;
import com.tweetapp.api.repository.UserRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;
	
	@Test
	void findByUsernameContainingTest() {
		UserModel user = new UserModel("test", "test", "test", "test", "test", "test", "Test");
		
		userRepo.save(user);
		List<UserModel> actualResult = userRepo.findByUsernameContaining("test");
		assertNotNull(actualResult);
	}
	
	@Test
	void findByUsernameTest() {
		UserModel user = new UserModel("test", "test", "test", "test", "test", "test", "Test");
		
		userRepo.save(user);
		UserModel actualResult = userRepo.findByUsername("test");
		assertNotNull(actualResult);
	}
}
