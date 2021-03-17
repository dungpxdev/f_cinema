package com.fsoft.F_Cinema.services.impl;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.F_Cinema.config.FileConfig;
import com.fsoft.F_Cinema.constants.MovieStatusConstant;
import com.fsoft.F_Cinema.dto.MovieDTO;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.repository.MovieRepository;
import com.fsoft.F_Cinema.services.MovieService;
import com.fsoft.F_Cinema.utils.Converter;
import com.fsoft.F_Cinema.utils.FileUtils;
import com.fsoft.F_Cinema.validation.DateValidation;

@Service
public class MovieServiceImpl implements MovieService {
	
	Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	@Autowired
	private FileConfig fileConfig;

	@Autowired
	private Converter converter;
	
	@Autowired
	private FileUtils fileUtils;

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private DateValidation dateValidation;

	@Override
	public MovieEntity save(MovieEntity movieEntity) {
		return movieRepository.save(movieEntity);
	}

	@Override
	public List<MovieEntity> findAll() {
		return (List<MovieEntity>) movieRepository.findAll();
	}

	@Override
	public Optional<MovieEntity> findById(Long id) {
		return movieRepository.findById(id);
	}

	@Override
	public MovieEntity findOneAndUpdate(String id, MovieEntity newMovie) {
		try {
			Long movieId = Long.parseLong(id);
			Optional<MovieEntity> oldMovie = movieRepository.findById(movieId);
			if (oldMovie.get() == null)
				throw new Exception(new StringBuilder("Movie does not exist with ID: ")
						.append(movieId).toString());
			if (newMovie.getImage() == null)
				newMovie.setImage(oldMovie.get().getImage());

			newMovie.setId(movieId);
			newMovie.setCreatedBy(oldMovie.get().getCreatedBy());
			newMovie.setCreatedDate(oldMovie.get().getCreatedDate());

			return movieRepository.findOneAndUpdate(movieId, newMovie);
		} catch (Exception e) {
			logger.error(new StringBuilder("Update Movie Error Cause: ")
					.append(e.getMessage()).toString());

			return null;
		}

	}

	@Override
	public MovieEntity findByCode(String code) {
		return movieRepository.findByCode(code);
	}

	@Override
	public MovieEntity movieBuild(MovieDTO movieDTO, Principal principal) throws IOException {
		try {
			if (dateValidation.isDatePassAway(new SimpleDateFormat("yyyy-MM-dd").parse(movieDTO.getStartTime()))) {
				throw new Exception("Date is pass away !");
			}

			MultipartFile multipartFile = movieDTO.getPoster();
			String fileName = multipartFile.getOriginalFilename();
			File file = new File(fileConfig.getFolderUpload(), fileName);
			File file2 = new File(fileConfig.getFolderUpload(), fileUtils.renameFile(fileName));
			file.renameTo(file2);
			multipartFile.transferTo(file);
			movieDTO.setImage(file.getPath().split("static")[1]);

			MovieEntity movieEntity = converter.convertTo(movieDTO);
			movieEntity.setCreatedDate(new Date());
			movieEntity.setCreatedBy(principal.getName());
			movieEntity.setPrice(movieDTO.getPrice());
			movieEntity.setStartTime(new SimpleDateFormat("yyyy-MM-dd").parse(movieDTO.getStartTime()));
			movieEntity.setEndTime(new SimpleDateFormat("yyyy-MM-dd").parse(movieDTO.getEndTime()));
			
			if (!dateValidation.isDatePassAway(new SimpleDateFormat("yyyy-MM-dd").parse(movieDTO.getStartTime())))
				movieEntity.setStatus(MovieStatusConstant.WAITING.getKey());
			else
				movieEntity.setStatus(MovieStatusConstant.ACTIVE.getKey());

			return movieEntity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public MovieEntity updateTickets(Long number, Long movieId) {
		return movieRepository.updateTickets(number, movieId);
	}

}
