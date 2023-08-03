package com.excellence.usersignupsignin.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
	private int userId;
	@Column(nullable = false, length = 10)
	private String userName;
	@Column(nullable = false, length = 8)
	private String userPassword;

}
