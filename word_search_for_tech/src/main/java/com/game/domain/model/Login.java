package com.game.domain.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class Login implements Serializable{

	@NotBlank
	@Length(min = 5, max = 15)
	private String userName;

	@NotBlank
	@Length(min = 8, max = 20)
	private String password;

	public Login(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public Login() {

	}
}
