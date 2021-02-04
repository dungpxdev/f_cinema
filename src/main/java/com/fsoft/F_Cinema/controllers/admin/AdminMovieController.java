package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/movie")
public class AdminMovieController {

	@GetMapping(path = { "", "/" })
	public String getMovie(Model model, Principal principal) {
		model.addAttribute("username", principal.getName());
		return "dashboard/admin/ticket";
	}

}
