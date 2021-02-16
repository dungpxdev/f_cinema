package com.fsoft.F_Cinema.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.services.CinemaService;

@Controller
@RequestMapping(path = { "/admin/room" })
public class AdminRoomController {
	
	@Autowired
	private CinemaService cinemaService;

	@GetMapping(path = { "", "/" })
	public String getRoom(Model model) {
		List<CinemaEntity> rooms = cinemaService.findAll();
		model.addAttribute("rooms", rooms);
		return "dashboard/admin/room";
	}
	
}
