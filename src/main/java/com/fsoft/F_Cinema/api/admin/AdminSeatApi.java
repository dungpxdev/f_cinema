package com.fsoft.F_Cinema.api.admin;

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

import com.fsoft.F_Cinema.dto.ApiResponseDTO;
import com.fsoft.F_Cinema.dto.SeatDTO;
import com.fsoft.F_Cinema.dto.SeatParamsDTO;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.services.SeatService;
import com.fsoft.F_Cinema.utils.Converter;

@RestController
@RequestMapping(path = { "/api/v1/admin/seat" })
public class AdminSeatApi {

	@Autowired
	private SeatService seatService;

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

			return new ResponseEntity<List<SeatDTO>>(seats, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			apiResponse.setCreatedDate(new Date());
			apiResponse.setMessage(e.getMessage());
			errors.add(e.getMessage());
			
			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}

}
