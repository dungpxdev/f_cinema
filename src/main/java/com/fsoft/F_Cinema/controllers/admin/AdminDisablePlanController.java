package com.fsoft.F_Cinema.controllers.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.entities.DisablePlanEntity;
import com.fsoft.F_Cinema.services.DisablePlansService;

@RequestMapping("/admin/disable")
@Controller
public class AdminDisablePlanController {

	@Autowired
	private DisablePlansService disablePlanService;

	Logger logger = LoggerFactory.getLogger(AdminDisablePlanController.class);

	@GetMapping(path = { "", "/" })
	public String getDisablePlan(Model model) {
		List<DisablePlanEntity> disables = disablePlanService.findNextDisable();
		model.addAttribute("disables", disables);
		return "dashboard/admin/disablePlan";
	}

}
