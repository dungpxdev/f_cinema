package com.fsoft.F_Cinema.api.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.F_Cinema.constants.SeatStatusConstant;
import com.fsoft.F_Cinema.dto.ApiResponseDTO;
import com.fsoft.F_Cinema.dto.SeatDTO;
import com.fsoft.F_Cinema.dto.SeatParamsDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.services.SeatService;
import com.fsoft.F_Cinema.utils.Converter;

@RestController
@RequestMapping(path = { "/api/v1/admin/seat" })
public class AdminSeatApi {

	@Autowired
	private SeatService seatService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CinemaService cinemaService;

	@Autowired
	private Converter converter;

	@PostMapping(path = { "/", "" }, 
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> fetchSeats(@RequestBody SeatParamsDTO params) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		List<String> errors = new ArrayList<String>();
		try {
			List<SeatEntity> seatEnties = seatService.findByCinemaCodeAndRoomCode(
					params.getCinemaCode(), 
					params.getRoomCode());
			List<SeatDTO> seats = seatEnties.stream()
					.map(seat -> converter.convertTo(seat))
					.collect(Collectors.toList());

			return new ResponseEntity<List<SeatDTO>>(seats, HttpStatus.OK);
		} catch (Exception e) {
			apiResponse.setCreatedDate(new Date());
			apiResponse.setMessage(e.getMessage());
			errors.add(e.getMessage());
			
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}
	
	/**
	 * Save seat into database
	 * 
	 * @param <SeatParamsDTO> params
	 * @param <Principal> principal
	 */
	@PostMapping(path = { "/add" },
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addSeat(@RequestBody SeatParamsDTO params, Principal principal) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		apiResponse.setCreatedDate(new Date());
		apiResponse.setCreatedBy(principal.getName());
		List<String> errors = new ArrayList<String>();
		try {
			//checking if seat already exist
			SeatEntity isExist = seatService.findByCodeAndRoomCodeAndCinemaId(
					params.getCode(),
					params.getRoomCode(), 
					params.getCinemaCode());
			
			if (isExist != null) {
				apiResponse.setMessage("Seat already exist");
				return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
			}
			
			CinemaEntity cinemaEntity = cinemaService.findByCode(params.getCinemaCode());
			RoomEntity roomEntity = roomService.findByCodeAndCinemaId(params.getRoomCode(),
					cinemaEntity.getId());
			//create seat entity and set some params needed
			SeatEntity seatEntity = converter.convertTo(params);
			seatEntity.setCreatedDate(new Date());
			seatEntity.setCreatedBy(principal.getName());
			seatEntity.setRoom(roomEntity);
			seatEntity.setStatus(SeatStatusConstant.AVAILABLE.getValue());
			
			seatService.save(seatEntity);
			
			apiResponse.setMessage("Seat created successful");
			apiResponse.setStatus(HttpStatus.OK);
			
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			apiResponse.setMessage("Adding seat failed cause some errors");
			apiResponse.setStatus(HttpStatus.BAD_REQUEST);
			apiResponse.setErrors(errors);
			errors.add(e.getMessage());

			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}

}
