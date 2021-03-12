package com.fsoft.F_Cinema.api.admin;

import java.security.Principal;
import java.util.ArrayList;
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
import com.fsoft.F_Cinema.dto.CinemaDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.services.CinemaService;

@RestController
@RequestMapping(path = { "/api/v1/admin/cinema" })
public class AdminCinemaApi {
	
	@Autowired
	private CinemaService cinemaService;

	@PostMapping(path = { "/status" }, produces = { MediaType.APPLICATION_JSON_VALUE },
									 consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deactiveCinema(@RequestBody CinemaDTO cinemaDTO, Principal principal) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		try {
			CinemaEntity cinemaEntity = cinemaService.changeStatus(cinemaDTO.getCode());
			apiResponse.apiResponseBuilder(null, HttpStatus.OK, cinemaEntity.getStatus(), cinemaEntity);
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			List<String> errors = new ArrayList<String>();
			errors.add(e.getMessage());
			apiResponse.apiResponseBuilder(errors, HttpStatus.BAD_REQUEST, "Something wrong !", null);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
