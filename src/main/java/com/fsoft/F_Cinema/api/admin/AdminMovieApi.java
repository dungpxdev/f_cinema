package com.fsoft.F_Cinema.api.admin;

import java.security.Principal;
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
import com.fsoft.F_Cinema.dto.MovieDTO;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.services.MovieService;

@RestController
@RequestMapping(path = { "/api/v1/admin/movie" })
public class AdminMovieApi {
	
	Logger logger = LoggerFactory.getLogger(AdminMovieApi.class);
	
	@Autowired
	private MovieService movieService;

	@PostMapping(path = { "/", "" }, 
			produces = {MediaType.APPLICATION_JSON_VALUE },
			consumes = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getMovie(@RequestBody MovieDTO movieDTO, Principal principal) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		apiResponse.setCreatedBy(principal.getName());
		apiResponse.setCreatedDate(new Date());
		List<String> errors = new ArrayList<String>();
		try {
			MovieEntity movieEntity = movieService.findByCode(movieDTO.getCode());
			if (movieEntity == null) {
				apiResponse.setMessage("Invalid movie for code" + movieDTO.getCode());
				apiResponse.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
			}
			
			movieEntity.setSchedules(null);
			movieEntity.setDisablePlans(null);
			movieEntity.setMovieCat(null);
			
			return new ResponseEntity<>(movieEntity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			apiResponse.setMessage(e.getMessage());
			errors.add(e.getMessage());

			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}

}
