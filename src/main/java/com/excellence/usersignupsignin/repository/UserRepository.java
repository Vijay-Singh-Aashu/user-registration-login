package com.excellence.usersignupsignin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.excellence.usersignupsignin.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {

		public User getByUserName(String userName);
	}


