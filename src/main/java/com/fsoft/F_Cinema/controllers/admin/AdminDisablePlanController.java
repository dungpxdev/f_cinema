package com.fsoft.F_Cinema.controllers.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/disable")
@Controller
public class AdminDisablePlanController {

	Logger logger = LoggerFactory.getLogger(AdminDisablePlanController.class);

	@GetMapping(path = { "", "/" })
	public String getCinemas(Model model) {

		return "dashboard/admin/cinema";
	}

}
