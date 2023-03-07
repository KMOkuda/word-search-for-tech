package com.game.domain.service;

import com.game.domain.model.Login;
import com.game.domain.model.SignupForm;

public interface UserService {
	public boolean registerUser(SignupForm signupForm);

	public boolean loginSucceed(Login login);
}
