package com.game.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.game.domain.model.SignupForm;
import com.game.repository.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("UserDaoImpl")
	UserDao dao;

	@Override
	public boolean registerUser(SignupForm signupForm) {
		if (dao.insertUser(signupForm) == 1) {
			System.out.println("successful user insert.");
			return true;
		} else {
			return false;
		}
	};
}
