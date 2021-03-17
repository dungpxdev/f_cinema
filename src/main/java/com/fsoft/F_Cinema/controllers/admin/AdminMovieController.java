package com.fsoft.F_Cinema.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(path = { "/add" })
	public String getMovie(Model model, Principal principal) {
		model.addAttribute("file", new MovieDTO());
		model.addAttribute("username", principal.getName());
		
		return "dashboard/admin/addMovie";
	}

	@PostMapping(path = { "/add" }, consumes = "multipart/form-data;charset=UTF-8")
	public String postMovie(MovieDTO movieDTO, Model model, Principal principal) {
		try {
			MovieEntity movieEntity = movieService.movieBuild(movieDTO, principal);
			movieService.save(movieEntity);
		} catch (IllegalStateException | IOException e) {
			logger.error(new StringBuilder("POST MOVIE ERROR: Cause ")
					.append(e.getMessage())
					.toString());
		} catch (Exception e) {
			logger.error(new StringBuilder("POST MOVIE ERROR: Cause ")
					.append(e.getMessage())
					.toString());
		}

		return "redirect:/admin/movie";
	}
	
	@GetMapping(path = { "/", "" })
	public String getAllMovies(Model model) {
		List<MovieEntity> movies = movieService.findAll();
		model.addAttribute("movies", movies);
		
		return "dashboard/admin/allMovie";
	}
	
	@GetMapping(path = { "/modify" })
	public String getModify(Model model) {
		List<MovieEntity> movies = movieService.findAll();
		model.addAttribute("movies", movies);
		
		return "dashboard/admin/modifyMovie";
	}
	
	@GetMapping(path = { "/edit" })
	public String fetchMovie(Model model, @RequestParam String movieId) {
		try {
			Long movieID = Long.parseLong(movieId);
			Optional<MovieEntity> movieEntity = movieService.findById(movieID);
			model.addAttribute("movie", movieEntity.get());
			return "dashboard/admin/editMovie";
		} catch (Exception e) {
			return "redirect:/admin/movie?error=true";
		}
	}
	
	@PostMapping(path = { "/edit" }, consumes = "multipart/form-data;charset=UTF-8")
	public String editMovie(MovieDTO movie, 
							@RequestParam String movieId, 
							Principal principal) {
		try {
			MultipartFile multipartFile = movie.getPoster();
			if (!multipartFile.isEmpty()) {
				String fileName = multipartFile.getOriginalFilename();
				File file = new File(fileConfig.getFolderUpload(), fileName);
				multipartFile.transferTo(file);
				movie.setImage(file.getPath().split("static")[1]);
			}

			MovieEntity movieEntity = converter.convertTo(movie);
			movieEntity.setModifiedDate(new Date());
			movieEntity.setModifiedBy(principal.getName());

			movieService.findOneAndUpdate(movieId, movieEntity);
		} catch (IllegalStateException | IOException e) {
			logger.error(new StringBuilder("POST MOVIE ERROR: Cause ")
					.append(e.getMessage())
					.toString());
		}

		return "redirect:/admin/movie";
	}

}
