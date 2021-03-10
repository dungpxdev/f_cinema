package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.services.UserService;

/**
 * 
 * @author DungPX2
 * @Class AdminUserController
 * @since 2/2021
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserService userService;



	@GetMapping("/register")
	public String login(Model model) {

		return "index";
	}

	/**
	 * Create new member for cinema's staff
	 * new User is automatic generated username, password and email
	 * 	- 
	 * @param principal
	 * @param userDTO
	 * @return redirect/admin
	 */
	@PostMapping(path = { "/", "" })
	public String postStaff(Principal principal, @Valid UserDTO userDTO) {
		try {
			if (!(principal instanceof UsernamePasswordAuthenticationToken) ||
					!"admin".equals(principal.getName()))
				throw new Exception("You Don't have permission for this operation !");

			userService.createMember(userDTO);

		} catch (Exception e) {
			logger.info(new StringBuilder("INSERTED: New user ")
					.append(userDTO.getUsername())
					.append(" failed cause: ")
					.append(e.getMessage())
					.toString());
		}

		return "redirect:/admin";
	}
}
