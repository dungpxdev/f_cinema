package com.fsoft.F_Cinema.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.F_Cinema.config.FileConfig;
import com.fsoft.F_Cinema.dto.MovieDTO;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.services.MovieService;
import com.fsoft.F_Cinema.utils.Converter;

@Controller
@RequestMapping("/admin/movie")
public class AdminMovieController {

	Logger logger = LoggerFactory.getLogger(AdminMovieController.class);

	@Autowired
	private MovieService movieService;

	@Autowired
	private FileConfig fileConfig;

	@Autowired
	private Converter converter;

	@GetMapping(path = { "", "/" })
	public String getMovie(Model model, Principal principal) {
		model.addAttribute("file", new MovieDTO());
		model.addAttribute("username", principal.getName());
		
		return "dashboard/admin/addMovie";
	}

	@PostMapping(path = { "", "/" })
	public String postMovie(MovieDTO movieDTO, Model model, Principal principal) {
		try {
			MultipartFile multipartFile = movieDTO.getPoster();
			String fileName = multipartFile.getOriginalFilename();
			File file = new File(fileConfig.getFolderUpload(), fileName);
			multipartFile.transferTo(file);
			movieDTO.setImage(file.getPath());
			
			MovieEntity movieEntity = converter.convertTo(movieDTO);
			movieEntity.setCreatedDate(new Date());
			movieEntity.setCreatedBy(principal.getName());
			
			movieService.save(movieEntity);
		} catch (IllegalStateException | IOException e) {
			logger.error(new StringBuilder("POST MOVIE ERROR: Cause ")
					.append(e.getMessage()).toString());
		}
		return "redirect:/admin";
	}

}
