package com.excellence.usersignupsignin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excellence.usersignupsignin.dto.User;
import com.excellence.usersignupsignin.repository.UserRepository;

@Repository
public class UserDao {
	
	@Autowired
	private UserRepository repository;
	
	/*
	 * saveMethod for user
	 */
	public User saveUser(User user) {
		
		return repository.save(user);
	}
	
	/*
	 * userLogin
	 */
	public User loginUser(String userName) {
	
		return repository.getByUserName(userName);
	}
	
	

}
