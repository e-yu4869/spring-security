package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.service.userDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class webSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private userDetailServiceImpl userDetailService;

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService).passwordEncoder(passEncoder());
	}
	
	@Bean
	public BCryptPasswordEncoder passEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers(
				"/images/**",
				"/css/**",
				"/javascript/**"
				);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.authorizeRequests()
		  .antMatchers("/login").permitAll()
		  .anyRequest().authenticated()
		  .and()
		.formLogin()
		  .loginPage("/login")
			.loginProcessingUrl("/login") // ログインフォームのアクションに指定したURL[action="@{/login}"]を設定
			.usernameParameter("userName") // ログインフォームのユーザー欄のname属性を設定
			.passwordParameter("password") // ログインフォームのパスワード欄のname属性を設定
			.successForwardUrl("/login/top") // ログイン成功時に遷移するURL
			.failureUrl("/login/error") // ログイン失敗時に遷移するURL
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login/logout")
			.permitAll()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

}
