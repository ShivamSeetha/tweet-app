package com.tweetapp.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDetails {

	private String shortMessage;
	private String longMessage;
	private String resourcePath;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss Z", timezone = "Asia/Kolkata")
	private Date timeStamp;

}
