package com.excellence.usersignupsignin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excellence.usersignupsignin.dto.User;
import com.excellence.usersignupsignin.response.ResponseStructure;
import com.excellence.usersignupsignin.service.UserServices;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserServices service;
	
	/*
	 * saveMethod for user
	 */
	@PostMapping("/userSave")
	public ResponseStructure<User> saveUser(@RequestBody User user) {
		
		return service.saveUser(user);
	}
	
	/*
	 * userLogin
	 */
	@GetMapping("/loginUser/{userName}/{userPassword}")
	public ResponseStructure<User> loginUser(@PathVariable String userName,@PathVariable String userPassword) {
	
		return service.loginUser(userName, userPassword);
	}
}

