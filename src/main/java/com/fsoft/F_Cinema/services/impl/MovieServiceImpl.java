package com.fsoft.F_Cinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.repository.MovieRepository;
import com.fsoft.F_Cinema.services.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public MovieEntity save(MovieEntity movieEntity) {
		return movieRepository.save(movieEntity);
	}

}
