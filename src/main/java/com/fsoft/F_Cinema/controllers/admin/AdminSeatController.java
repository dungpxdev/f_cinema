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

@Controller
@RequestMapping("/admin/seat")
public class AdminSeatController {

	Logger logger = LoggerFactory.getLogger(AdminSeatController.class);

	@Autowired
	private CinemaService cinemaService;

	@GetMapping(path = { "/", "" })
	public String getAddSeats(Model model) {
		try {
			List<CinemaEntity> cinemas = cinemaService.findAll();
			model.addAttribute("cinemas", cinemas);
		} catch (Exception e) {
			logger.error(new StringBuilder("GET SEAT: Error cause: ")
					.append(e.getMessage())
					.toString());
		}
		return "dashboard/admin/addSeat";
	}

}
