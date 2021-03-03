package com.fsoft.F_Cinema.api.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private MovieService movieService;
	
	@PostMapping(path = { "/", "" },
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> postSchedule(@RequestBody ScheduleDTO scheduleDTO, Principal principal) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		apiResponse.setCreatedBy(principal.getName());
		apiResponse.setCreatedDate(new Date());
		List<String> errors = new ArrayList<String>();
		try {
			CinemaEntity cinemaEntity = cinemaService.findByCode(scheduleDTO.getCinema());
			RoomEntity roomEntity = roomService.findByCodeAndCinemaId(scheduleDTO.getRoom(), 
					cinemaEntity.getId());
			MovieEntity movieEntity = movieService.findByCode(scheduleDTO.getMovie());

			ScheduleEntity scheduleEntity = new ScheduleEntity();
			scheduleEntity.setCode(cinemaEntity.getCode() + 
					movieEntity.getCode() + 
					roomEntity.getCode());
			scheduleEntity.setName(scheduleDTO.getName());
			scheduleEntity.setCreatedDate(new Date());
			scheduleEntity.setCreatedBy(principal.getName());
			scheduleEntity.setMovie(movieEntity);
			scheduleEntity.setStartTime(scheduleDTO.getStartTime());
			scheduleEntity.setEndTime(scheduleDTO.getEndTime());
			scheduleEntity.setRoom(roomEntity);

			scheduleService.save(scheduleEntity);
			apiResponse.setStatus(HttpStatus.OK);
			apiResponse.setMessage(new StringBuilder("Schedule for movie ")
					.append(movieEntity.getName())
					.append(" created").toString());
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			apiResponse.setCreatedDate(new Date());
			apiResponse.setMessage(e.getMessage());
			errors.add(e.getMessage());

			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}

}
