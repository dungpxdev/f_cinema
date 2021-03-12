package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.dto.TicketDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.entities.TicketEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.MovieService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.services.ScheduleService;
import com.fsoft.F_Cinema.services.SeatService;
import com.fsoft.F_Cinema.services.TicketService;

@RequestMapping("/admin/ticket")
@Controller
public class AdminTicketController {

	Logger logger = LoggerFactory.getLogger(AdminTicketController.class);

	@Autowired
	private CinemaService cinemaService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private ScheduleService scheduleService;

	@Autowired
	private SeatService seatService;

	@Autowired
	private TicketService ticketService;

	@GetMapping(path = { "/", "" })
	public String getTickets(Model model, Principal principal) {
		List<CinemaEntity> cinemas = cinemaService.findAll();
		List<RoomEntity> rooms = roomService.findbyCinemaId(1L);// hard-code
		List<MovieEntity> movies = movieService.findAll();

		model.addAttribute("cinemas", cinemas);
		model.addAttribute("rooms", rooms);
		model.addAttribute("movies", movies);

		return "dashboard/admin/ticket";
	}

	@PostMapping(path = { "/generate" })
	public String generateTickets(final TicketDTO ticketDTO, final Principal principal, Model model) {
		try {
			ScheduleEntity scheduleEntity = scheduleService.findByCode(ticketDTO.getSchedule());
			List<SeatEntity> seats = seatService.findAllSeatNotOcupied(scheduleEntity, ticketDTO);
			ticketService.generate(seats, scheduleEntity, ticketDTO);
			
		} catch (Exception e) {
			logger.error(new StringBuilder("Ticket Generate Error Cause: ")
					.append(e.getMessage())
					.toString());
		}

		return "redirect:/admin";
	}

	@GetMapping(path = { "/views" })
	public String getTickets(Model model) {
		List<TicketEntity> tickets = ticketService.findAllByStatus("AVAILABLE");
		List<TicketDTO> ticketDTOs = ticketService.mapping(tickets);
		List<CinemaEntity> cinemas = cinemaService.findAll();
		List<MovieEntity> movies = movieService.findAll();
		
		model.addAttribute("cinemas", cinemas);
		model.addAttribute("movies", movies);
		model.addAttribute("tickets", ticketDTOs);
		return "dashboard/admin/ticketView";
	}
}
