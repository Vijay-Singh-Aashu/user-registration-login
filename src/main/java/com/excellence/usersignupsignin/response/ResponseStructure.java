package com.excellence.usersignupsignin.response;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseStructure<T> {

	private int statusCode;
	private String message;
	private String description;
	private T Data;
	
}
