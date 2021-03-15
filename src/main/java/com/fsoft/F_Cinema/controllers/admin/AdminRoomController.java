package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;
import java.util.ArrayList;
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

import com.fsoft.F_Cinema.constants.SeatConstant;
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
	public String postRoom(final RoomDTO roomDTO, Model model, Principal principal) {
		try {

			RoomEntity roomEntity = converter.convertTo(roomDTO);
			roomEntity.setCreatedBy(principal.getName());
			roomEntity.setCreatedDate(new Date());
			roomEntity.setName(roomDTO.getName().toUpperCase());
			roomEntity.setCode(roomDTO.getCode().toUpperCase());
			
			CinemaEntity cinemaEntity = cinemaService.findByCode(roomDTO.getCinema());
			
			if (roomEntity.getNumberOfSeat() % SeatConstant.NUMBERSEATOFROW.getValue() != 0) {
				throw new Exception("Number of seats provided invalid");
			}
			
			
			
			
			RoomEntity isExistRoom = roomService.findByCodeAndCinemaId(roomEntity.getCode(), cinemaEntity.getId());
			List<String> errors = new ArrayList<String>();
			if (isExistRoom != null) {
				errors.add(
						new StringBuilder("The Room with code ")
						.append(roomEntity.getCode())
						.append(" in cinema ID ")
						.append(cinemaEntity.getCode())
						.append(" already exist. Please check!")
						.toString());
			}
			
			if (!errors.isEmpty()) {
				model.addAttribute("errors", errors);
				model.addAttribute("room", roomDTO);
				return "redirect:/admin/room";
			}
			
			roomEntity.setCinema(cinemaEntity);
			roomService.save(roomEntity);
		} catch (Exception e) {
			logger.error(new StringBuilder("POST ROOM: Fail cause: ")
					.append(e.getMessage())
					.toString());
		}
		
		return "redirect:/admin/seat";
	}

}
