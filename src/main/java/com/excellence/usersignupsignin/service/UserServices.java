package com.excellence.usersignupsignin.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.excellence.usersignupsignin.dao.UserDao;
import com.excellence.usersignupsignin.dto.User;
import com.excellence.usersignupsignin.response.ResponseStructure;

import ch.qos.logback.core.boolex.Matcher;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServices {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ResponseStructure<User> responseStructure;
	
	@Autowired
	private HttpSession httpSession;
	/*
	 * saveMethod for user
	 */
	public ResponseStructure<User> saveUser(User user) {
		
		if((user.getUserName().length()<=10)&&(user.getUserPassword().length()<=8)) {
			
			String password = user.getUserPassword();
			
			java.util.regex.Matcher alphabets = Pattern.compile("[a-zA-z]").matcher(password);
			java.util.regex.Matcher special = Pattern.compile("[!@#$%&*]").matcher(password);
			java.util.regex.Matcher digit = Pattern.compile("[0-9]").matcher(password);
			
			if((alphabets.find())&&(special.find())&&(digit.find())) {
				User user1=userDao.saveUser(user);
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setMessage("User----Registered----Successfully...");
				responseStructure.setDescription("user has combination of specialcharacter, digits....and alphabets ...");
				responseStructure.setData(user1);
				return responseStructure;
			}else {
				
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setMessage("User******Not*****Registered...");
				responseStructure.setDescription("user should has combination of special character, digits....and alphabets ...");
				responseStructure.setData(user);
				return responseStructure;
			}
			
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMessage("User----Not----Registered...");
			responseStructure.setDescription("user should has username with less than equals to 10 character and "
					+ "password should be less than equals to 8 characters with combination of specialcharacter, digits....and alphabets  ");
			responseStructure.setData(user);
			return responseStructure;
		}
		
		
	}
	
	/*
	 * userLogin
	 */
	public ResponseStructure<User> loginUser(String userName,String userPassword) {
	
		User user = userDao.loginUser(userName);
		
		
		if(user!=null) {
			
			if((user.getUserName().equals(userName))&&(user.getUserPassword().equals(userPassword))) {
				
				
				httpSession.setAttribute("userpassword", user.getUserPassword());
				responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
				responseStructure.setMessage("User----LoggedIn----Successfully...");
				responseStructure.setData(user);
				return responseStructure;
			}else {
				responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
				responseStructure.setMessage("User----Failed----to---loggedin...");
				responseStructure.setDescription("Hey please check your username and password...... ...");
				responseStructure.setData(user);
				return responseStructure;
			}
		}else {
			responseStructure.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMessage("User----Failed----to---loggedin...");
			responseStructure.setDescription(".......provided username is not registered.....yet....");
			responseStructure.setData(user);
			return responseStructure;
		}
		
	}

}
