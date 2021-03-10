package com.fsoft.F_Cinema.controllers.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.services.CinemaService;

@RequestMapping("/admin/disable")
@Controller
public class AdminDisablePlanController {

	@Autowired
	private CinemaService cinemaService;

	Logger logger = LoggerFactory.getLogger(AdminDisablePlanController.class);

	@GetMapping(path = { "", "/" })
	public String getDisablePlan(Model model) {
		List<CinemaEntity> cinemas = cinemaService.findAll();
		model.addAttribute("cinemas", cinemas);
		return "dashboard/admin/disablePlan";
	}

}
