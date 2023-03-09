package com.game;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Autowired
	private DataSource dataSource;

	private static final String USER_SQL = "SELECT" + " user_name," + " password,"
			+ " true" + " FROM" + " t_user" + " WHERE" + " user_name = ?";

	private static final String ROLE_SQL = "SELECT" + " user_name," + " role" + " FROM"
			+ " t_user" + " WHERE" + " user_name = ?";

	/**
	 * @param http
	 * @return
	 * @throws Exception
	 *
	 * アクセス権限について規定
	 */

/**
	@Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("hoge").password("HOGE").roles("USER");
    }**/

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


		http.authorizeHttpRequests(authz -> authz
				.mvcMatchers("/webjars/**", "/css/**").permitAll()
				.antMatchers("/**").permitAll()
				);

		http.formLogin()
				.loginProcessingUrl("/login")
				.loginPage("/login")
				.failureUrl("/login")
				.usernameParameter("userName")
				.passwordParameter("password")
				.defaultSuccessUrl("/", true)
				.permitAll();
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

	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);

		users.setUsersByUsernameQuery(USER_SQL);
		users.setAuthoritiesByUsernameQuery(ROLE_SQL);

		System.out.println(users.getUsersByUsernameQuery());

		return users;
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
