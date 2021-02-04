package com.fsoft.F_Cinema.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fsoft.F_Cinema.entities.UserEntity;
import com.fsoft.F_Cinema.services.UserService;

@Component
public class UserInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;

	Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated() && !"anonymousUser".equals(auth.getName())) {
			logger.info("USER LOGGED: " + auth.getName());
			UserEntity userEntity = userService.findByUsername(auth.getName());
			modelAndView.addObject("username", userEntity.getUsername());
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
