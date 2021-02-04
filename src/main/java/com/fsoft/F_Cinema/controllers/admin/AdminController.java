package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.services.UserService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserService userService;

	@GetMapping(path = { "/", "" })
	public String index(Model model, Principal principal) {
		List<UserDTO> users = userService.findNewsUsers();
		model.addAttribute("newusers", users);
		model.addAttribute("username", principal.getName());
		return "dashboard/admin/home";
	}

}
