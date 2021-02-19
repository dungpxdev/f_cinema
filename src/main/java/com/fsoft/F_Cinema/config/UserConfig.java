package com.fsoft.F_Cinema.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fsoft.F_Cinema.interceptor.UserInterceptor;

@Configuration
public class UserConfig implements WebMvcConfigurer {

	@Autowired
	private UserInterceptor userInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor);
	}

}
