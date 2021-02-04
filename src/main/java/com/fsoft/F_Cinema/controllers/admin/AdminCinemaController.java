package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.dto.CinemaDTO;
import com.fsoft.F_Cinema.services.CinemaService;

@RequestMapping("/admin/cinema")
@Controller
public class AdminCinemaController {

	Logger logger = LoggerFactory.getLogger(AdminCinemaController.class);

	@Autowired
	private CinemaService cinemaService;

	@PostMapping(path = { "/", "" })
	public String postMovie(Principal principal ,@Valid CinemaDTO cinemaDTO) {
		try {
			cinemaDTO.setCreatedBy(principal.getName());
			cinemaDTO.setCreatedDate(new Date());
			cinemaService.save(cinemaDTO);

			logger.info(new StringBuilder("INSERTED: ")
					.append(cinemaDTO.getCode())
					.append(" successful").toString());
		} catch (Exception e) {
			logger.info(new StringBuilder("INSERTED: ")
					.append(cinemaDTO.getCode())
					.append(" failed cause: ")
					.append(e.getMessage()).toString());
		}
		return "redirect:/admin";
	}

}
