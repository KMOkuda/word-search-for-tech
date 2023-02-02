package com.game.domain.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{
	private String username;
	private String email;
	private String password;
	private String role;

}
