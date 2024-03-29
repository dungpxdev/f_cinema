package com.fsoft.F_Cinema.api.admin;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.F_Cinema.dto.DisablePlanDTO;

@RestController
@RequestMapping(path = { "/api/v1/admin/disable" })
public class AdminDisablePlanApi {
	
	Logger logger = LoggerFactory.getLogger(AdminDisablePlanApi.class);

	@PostMapping(path = { "/", "" }, produces = { MediaType.APPLICATION_JSON_VALUE }, 
									 consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getMovie(@RequestBody DisablePlanDTO disablePlanDTO, Principal principal) {
		try {

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
