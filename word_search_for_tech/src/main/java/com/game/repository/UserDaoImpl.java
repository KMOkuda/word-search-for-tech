package com.game.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.game.domain.model.SignupForm;

@Repository("UserDaoImpl")
public class UserDaoImpl implements UserDao{
	@Autowired
	private JdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public int insertUser(SignupForm signupForm) {
		String sql = "INSERT INTO t_user (user_name, password, email) " + "VALUES("
				+ "?, ?, ?" + ")";

		int count = jdbc.update(sql, signupForm.getUserName(), passwordEncoder.encode(signupForm.getPassword()), signupForm.getEmail());
		return count;
	};
}
