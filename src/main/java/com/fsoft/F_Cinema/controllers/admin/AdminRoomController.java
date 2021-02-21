package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.dto.RoomDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.utils.Converter;

@Controller
@RequestMapping(path = { "/admin/room" })
public class AdminRoomController {
	
	Logger logger = LoggerFactory.getLogger(AdminRoomController.class);
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private Converter converter;

	@GetMapping(path = { "", "/" })
	public String getRoom(Model model) {
		List<CinemaEntity> cinemas = cinemaService.findAll();
		model.addAttribute("cinemas", cinemas);
		return "dashboard/admin/room";
	}
	
	@PostMapping(path = { "/", "" })
	public String postRoom(RoomDTO roomDTO, Principal principal) {
		try {
			roomDTO.setCreatedBy(principal.getName());
			roomDTO.setCreatedDate(new Date());
			roomDTO.setName(roomDTO.getName().toUpperCase());
			roomDTO.setCode(roomDTO.getCode().toUpperCase());
			RoomEntity roomEntity = converter.convertTo(roomDTO);
			CinemaEntity cinemaEntity = cinemaService.findByCode(roomDTO.getCinema());
			roomEntity.setCinema(cinemaEntity);
			roomService.save(roomEntity);
		} catch (Exception e) {
			logger.error(new StringBuilder("POST ROOM: Fail cause: ")
					.append(e.getMessage())
					.toString());
		}
		return "redirect:/seat";
	}

}
