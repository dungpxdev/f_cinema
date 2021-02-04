package com.fsoft.F_Cinema.controllers.admin;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.constants.RoleConstant;
import com.fsoft.F_Cinema.dto.RoleDTO;
import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.services.UserService;
import com.fsoft.F_Cinema.utils.Converter;

@RequestMapping("/admin/auth")
@Controller
public class AdminAuthController {

	Logger logger = LoggerFactory.getLogger(AdminAuthController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Converter converter;

	@GetMapping(path = { "/login" })
	public String login(Model model) {
		Authentication auth = SecurityContextHolder.getContext()
									.getAuthentication();
		if (auth instanceof UsernamePasswordAuthenticationToken)
			return "redirect:/admin";

		model.addAttribute("title", "F_Movie Admin Dashboard! | Login and registration");
		return "auth/login";
	}

	@PostMapping(path = "/register")
	public String register(@Valid UserDTO userDTO) {
		try {
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			userDTO.setCreatedDate(new Date());
			RoleDTO roleDTO = new RoleDTO();
			Set<String> roles = new HashSet<String>();
			roleDTO.setCode(RoleConstant.SCHEDULE_MANAGER.getValue());
			roleDTO.setName(RoleConstant.SCHEDULE_MANAGER.getKey());
			userDTO.setRoles(roles);
			userService.save(converter.convertTo(userDTO));
			StringBuilder message = new StringBuilder("Save new user: ");
			message.append(userDTO.getEmail());
			logger.info(message.toString());
			return "redirect:/admin";
		} catch (Exception e) {
			logger.info(e.getMessage());
			return "redirect:/auth/login";
		}
	}

}
