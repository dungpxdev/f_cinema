package com.fsoft.F_Cinema.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.services.IUserService;

@RequestMapping(path = "/auth")
@Controller
public class AuthController {

	Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private IUserService userService;

	@GetMapping(path = { "/login" })
	public String login(Model model) {
		model.addAttribute("title", "F_Movie Admin Dashboard! | Login and registration");
		return "auth/login";
	}

	@PostMapping(path = "/register")
	public String register(@Valid UserDTO user) {
		try {
			userService.save(user);
			StringBuilder message = new StringBuilder("Save new user: ");
			message.append(user.getEmail());
			logger.info(message.toString());
			return "redirect:/index";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/auth/login";
		}
	}
}
