package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.dto.CinemaDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.utils.Converter;

@RequestMapping("/admin/cinema")
@Controller
public class AdminCinemaController {

	Logger logger = LoggerFactory.getLogger(AdminCinemaController.class);

	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private Converter converter;

	@PostMapping(path = { "/add" })
	public String postCinema(Principal principal, @Valid final CinemaDTO cinemaDTO) {
		try {
			CinemaEntity cinemaEntity = converter.convertTo(cinemaDTO);
			cinemaEntity.setCreatedBy(principal.getName());
			cinemaEntity.setCreatedDate(new Date());
			cinemaService.save(cinemaEntity);

			logger.info(new StringBuilder("INSERTED: ")
					.append(cinemaDTO.getCode())
					.append(" successful")
					.toString());
		} catch (Exception e) {
			logger.info(new StringBuilder("INSERTED: ")
					.append(cinemaDTO.getCode())
					.append(" failed cause: ")
					.append(e.getMessage()).toString());
		}
		return "redirect:/admin";
	}

	@GetMapping(path = { "", "/" })
	public String getCinemas(Model model) {
		List<CinemaEntity> cinemas = cinemaService.findAll();
		model.addAttribute("cinemas", cinemas);
		return "dashboard/admin/cinema";
	}
	
	@GetMapping(path = { "/edit/{cinemaCode}" })
	public String getEditCinema(Model model, @PathVariable String cinemaCode) {
		CinemaEntity cinemaEntity = cinemaService.findByCode(cinemaCode);
		model.addAttribute("cinema", cinemaEntity);
		return "dashboard/admin/editCinema";
	}
	
	@PostMapping(path = { "/edit/{cinemaCode}" })
	public String postEditCinema(Model model, @PathVariable String cinemaCode, CinemaDTO cinemaDTO) {
		cinemaService.update(cinemaDTO.getCode(), cinemaDTO);
		return "redirect:/admin/cinema";
	}
	
	@GetMapping(path = { "/add" })
	public String getAddCinemas(Model model) {
		return "dashboard/admin/addCinema";
	}

}
