package com.fsoft.F_Cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fsoft.F_Cinema.constants.RoleConstant;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceCustom userDetailsService;

	/**
	 * Authentication
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}

	/**
	 * Authorization
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/admin/auth/login").permitAll()
			.antMatchers("/admin/auth/register").permitAll()
			
			.antMatchers("/admin/movie/**")
				.hasAnyAuthority(RoleConstant.MOVIE_MANAGER.getKey(), 
								 RoleConstant.ADMIN.getKey())
				
			.antMatchers("/admin/schedule/**")
				.hasAnyAuthority(RoleConstant.SCHEDULE_MANAGER.getKey(), 
								 RoleConstant.ADMIN.getKey())
				
			.antMatchers("/admin/room/**")
				.hasAnyAuthority(RoleConstant.ROOM_MANAGER.getKey(), 
								 RoleConstant.ADMIN.getKey())
				
			.antMatchers("/admin/ticket/**")
				.hasAnyAuthority(RoleConstant.TICKET_MANAGER.getKey(), 
								 RoleConstant.ADMIN.getKey())
				
			.antMatchers("/api/v1/admin/ticket/**")
				.hasAnyAuthority(RoleConstant.TICKET_MANAGER.getKey(), 
								 RoleConstant.ADMIN.getKey())
				
			.antMatchers("/api/v1/admin/movie/**")
				.hasAnyAuthority(RoleConstant.MOVIE_MANAGER.getKey(), 
								 RoleConstant.ADMIN.getKey())
				
			.antMatchers("/api/v1/admin/schedule/**")
				.hasAnyAuthority(RoleConstant.SCHEDULE_MANAGER.getKey(), 
								 RoleConstant.ADMIN.getKey())
				
			.antMatchers("/api/v1/admin/room/**")
				.hasAnyAuthority(RoleConstant.ROOM_MANAGER.getKey(), 
								 RoleConstant.ADMIN.getKey())
				
			.antMatchers("/admin/**").hasAuthority(RoleConstant.ADMIN.getKey())
			.antMatchers("/api/v1/admin/**").hasAuthority(RoleConstant.ADMIN.getKey())
			
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/admin/auth/login")
				.usernameParameter("username")
				.passwordParameter("password").permitAll()
			.and()
			.logout().logoutUrl("/auth/logout").permitAll()
			.and()
			.httpBasic();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	 
	/**
	 * Disable request to web resources
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers(
			"/build/**",
			"/icons/**",
			"/images/**",
			"/vendors/**"
		);
	}

}
