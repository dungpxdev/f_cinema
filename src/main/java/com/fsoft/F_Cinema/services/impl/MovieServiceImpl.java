package com.fsoft.F_Cinema.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.repository.MovieRepository;
import com.fsoft.F_Cinema.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public MovieEntity save(MovieEntity movieEntity) {
		return movieRepository.save(movieEntity);
	}

	@Override
	public List<MovieEntity> findAll() {
		return (List<MovieEntity>) movieRepository.findAll();
	}

	@Override
	public Optional<MovieEntity> findById(String id) {
		try {
			Long movieId = Long.parseLong(id);
			return movieRepository.findById(movieId);
		} catch (Exception e) {
			logger.error(new StringBuilder("Parse movie error cause: ")
					.append(e.getMessage()).toString());
			return null;
		}

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

}
