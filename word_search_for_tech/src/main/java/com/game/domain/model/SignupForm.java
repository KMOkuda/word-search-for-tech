package com.game.domain.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignupForm {
	@NotBlank
	@Length(min = 5, max = 15)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String userName;

	@NotBlank
	@Length(min = 8, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String password;

	@Email
	@Length(max = 254)
	private String email;
}
