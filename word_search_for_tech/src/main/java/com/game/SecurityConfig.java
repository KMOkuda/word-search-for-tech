package com.game;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {
	@Autowired
	private DataSource dataSource;

	private static final String USER_SQL = "SELECT" + " user_id," + " password,"
			+ " true" + " FROM" + " m_user" + " WHERE" + " user_id = ?";

	private static final String ROLE_SQL = "SELECT" + " user_id," + " role" + " FROM"
			+ " m_user" + " WHERE" + " user_id = ?";

	/**
	 * @param http
	 * @return
	 * @throws Exception
	 *
	 * アクセス権限について規定
	 */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(authz -> authz
				.mvcMatchers("/webjars/**", "/css/**").permitAll()
				.anyRequest().authenticated());

		http.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login")
				.defaultSuccessUrl("/", true);
/**
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login");
**/
		return http.build();
	}

	//参考URL：https://qiita.com/okaponta_/items/de1e640037b89b3ad6ca
	//ログイン処理時のユーザー情報をDBから取得する
	/**
	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

		users.setUsersByUsernameQuery(USER_SQL);
		users.setAuthoritiesByUsernameQuery(ROLE_SQL);

		return users;
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

/**
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user").password("{noop}password").roles("USER");
	}**/
}
