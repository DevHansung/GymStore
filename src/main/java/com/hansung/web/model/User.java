package com.hansung.web.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class User {
	
	private int userId;

	@NotEmpty(message = "The username must not be null")
	private String username;

	@NotEmpty(message = "The password must not be null")
	private String password;

	@NotEmpty(message = "The email must not be null")
	private String email;

	private boolean enabled = false; // 계정 활성화

	private String authority;
	
	private String postcode;
	
	private String address;
	
	private String detailAddress;

	private Cart cart;
}
