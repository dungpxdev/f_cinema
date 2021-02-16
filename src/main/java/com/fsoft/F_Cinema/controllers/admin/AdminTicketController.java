package com.fsoft.F_Cinema.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/ticket")
@Controller
public class AdminTicketController {

	@GetMapping(path = {"/", ""})
	public String getTickets() {
		return "a";
	}
}
