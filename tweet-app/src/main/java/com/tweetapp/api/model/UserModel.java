package com.tweetapp.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserModel {

	@Id
	private String id;
	@Indexed
	private String username;
	private String password;
	@Indexed
	private String email;
	private String firstName;
	private String lastName;
	private String contactNumber;

}
