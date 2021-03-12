package com.fsoft.F_Cinema.api.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.fsoft.F_Cinema.dto.RoomDTO;
import com.fsoft.F_Cinema.dto.SeatParamsDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.utils.Converter;

@RestController
@RequestMapping(path = { "/api/v1/admin/room" })
public class AdminRoomApi {
	
	Logger logger = LoggerFactory.getLogger(AdminRoomApi.class);

	@Autowired
	private RoomService roomService;

	@Autowired
	private CinemaService cinemaService;

	@Autowired
	private Converter converter;

	@PostMapping(path = { "/", "" }, produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> fetchRooms(@RequestBody SeatParamsDTO params) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		List<String> errors = new ArrayList<String>();
		try {
			CinemaEntity cinemaEntity = cinemaService.findByCode(params.getCinemaCode());
			if (cinemaEntity == null) {
				apiResponse.setMessage("Invalid Cinema Code");
				apiResponse.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
			}
			
			List<RoomEntity> roomEnties = roomService.findbyCinemaId(cinemaEntity.getId());
			List<RoomDTO> rooms = new ArrayList<RoomDTO>();
			roomEnties.forEach(room -> {
				RoomDTO roomDTO = converter.convertTo(room);
				roomDTO.setSchedules(null);
				roomDTO.setSeats(null);
				rooms.add(roomDTO);
			});

			return new ResponseEntity<List<RoomDTO>>(rooms, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			apiResponse.setCreatedDate(new Date());
			apiResponse.setMessage(e.getMessage());
			errors.add(e.getMessage());

			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}

}
