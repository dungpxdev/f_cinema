package com.fsoft.F_Cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceCustom userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/auth/login").permitAll()
			.antMatchers("/auth/register").permitAll()
			//.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/auth/login").permitAll()
			.and()
			.logout().permitAll()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	 }

}
