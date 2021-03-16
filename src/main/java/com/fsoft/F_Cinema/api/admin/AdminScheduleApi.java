package com.fsoft.F_Cinema.api.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.F_Cinema.dto.ApiResponseDTO;
import com.fsoft.F_Cinema.dto.ScheduleDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.MovieService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.services.ScheduleService;

@RestController
@RequestMapping(path = { "/api/v1/admin/schedule" })
public class AdminScheduleApi {
	
	Logger logger = LoggerFactory.getLogger(AdminScheduleApi.class);
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private MovieService movieService;
	
	Map<String, Object> apiResponse;
	
	@PostMapping(path = { "/", "" },
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> postSchedule(@RequestBody ScheduleDTO scheduleDTO, Principal principal) {
		try {
			CinemaEntity cinemaEntity = cinemaService.findByCode(scheduleDTO.getCinema());
			RoomEntity roomEntity = roomService.findByCodeAndCinemaId(scheduleDTO.getRoom(), cinemaEntity.getId());
			MovieEntity movieEntity = movieService.findByCode(scheduleDTO.getMovie());

			scheduleService.save(scheduleDTO, movieEntity, cinemaEntity, roomEntity);

			apiResponse = new ApiResponseDTO()
					.apiResponseBuilder(
							null, HttpStatus.OK, "Successfuly", null);

			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			apiResponse = new ApiResponseDTO()
					.apiResponseBuilder(errors, HttpStatus.BAD_REQUEST, e.getMessage(), null);

			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(path = { "/fetch" },
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> fetchAllSchedule(
			@RequestBody ScheduleDTO scheduleDTO, 
			Principal principal) {
		try {
			CinemaEntity cinemaEntity = cinemaService.findByCode(scheduleDTO.getCinema());
			RoomEntity roomEntity = roomService.findByCodeAndCinemaId(
					scheduleDTO.getRoom(), 
					cinemaEntity.getId());
			MovieEntity movieEntity = movieService.findByCode(scheduleDTO.getMovie());
			
			List<ScheduleEntity> schedules = scheduleService.findByIds(
					movieEntity.getId(), 
					roomEntity.getId())
					.stream().filter(schedule -> 
					schedule.getStartTime().compareTo(new Date()) >= 0)
					.collect(Collectors.toList());

			// exclude fields in ScheduleEntity (Lazy not working)
			for (ScheduleEntity schedule : schedules) {
				schedule.setTickets(null);
				schedule.setRoom(null);
				schedule.setMovie(null);
			}

			apiResponse = new ApiResponseDTO()
					.apiResponseBuilder(
							null, HttpStatus.OK, "Successfuly", schedules);

			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			apiResponse = new ApiResponseDTO()
					.apiResponseBuilder(errors, HttpStatus.BAD_REQUEST, "Fetch schedule error!", null);

			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}

}
