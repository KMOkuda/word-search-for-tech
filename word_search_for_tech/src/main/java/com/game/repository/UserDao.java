package com.game.repository;

import com.game.domain.model.SignupForm;

public interface UserDao {
	public int insertUser(SignupForm signupForm);
}
