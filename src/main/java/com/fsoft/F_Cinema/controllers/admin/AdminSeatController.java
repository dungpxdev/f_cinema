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
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.RoomService;

@Controller
@RequestMapping("/admin/seat")
public class AdminSeatController {

	Logger logger = LoggerFactory.getLogger(AdminSeatController.class);

	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private RoomService roomService;

	@GetMapping(path = { "/", "" })
	public String getAddSeats(Model model) {
		try {
			List<CinemaEntity> cinemas = cinemaService.findAll();
			model.addAttribute("cinemas", cinemas);

			List<RoomEntity> roomsOfFirstCinemaOfList = roomService.findbyCinemaId(cinemas.get(0).getId());
			model.addAttribute("rooms", roomsOfFirstCinemaOfList);
		} catch (Exception e) {
			logger.error(new StringBuilder("GET SEAT: Error cause: ")
					.append(e.getMessage())
					.toString());
		}
		return "dashboard/admin/addSeat";
	}

}
