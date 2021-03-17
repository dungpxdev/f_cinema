package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.constants.ScheduleStatusConstant;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.MovieService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.services.ScheduleService;

@Controller
@RequestMapping("/admin/schedule")
public class AdminScheduleController {

	Logger logger = LoggerFactory.getLogger(AdminScheduleController.class);

	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@GetMapping(path = { "/", "" })
	public String getSchedule() {

		return "redirect:/admin/schedule/table";
	}

	@GetMapping(path = { "/add" })
	public String addSchedule(Model model, Principal principal) {
		List<CinemaEntity> cinemas = cinemaService.findAll();
		List<RoomEntity> rooms = roomService.findbyCinemaId(1L);//hard-code
		List<MovieEntity> movies = movieService.findAll();
		model.addAttribute("cinemas", cinemas);
		model.addAttribute("rooms", rooms);
		model.addAttribute("movies", movies);
		return "dashboard/admin/addSchedule";
	}

	@GetMapping(path = { "/table" })
	public String getScheduleTable(Model model) {
		List<ScheduleEntity> scheduleEntities = scheduleService.findNexts();
		List<ScheduleStatusConstant> status = Arrays.asList(ScheduleStatusConstant.values());
		List<MovieEntity> movies = movieService.findAll();
		List<ScheduleEntity> schedules = scheduleService.findAllNextSchedules();
		model.addAttribute("dates", schedules);
		model.addAttribute("schedules", scheduleEntities);
		model.addAttribute("status", status);
		model.addAttribute("movies", movies);
		return "dashboard/admin/scheduleTable";
	}

}

