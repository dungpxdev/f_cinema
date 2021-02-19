package com.fsoft.F_Cinema.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.MovieEntity;

@Service
public interface MovieService {
	MovieEntity save(MovieEntity movieEntity);
	
	List<MovieEntity> findAll();
	
	Optional<MovieEntity> findById(String id);
	
	MovieEntity findOneAndUpdate(String id, MovieEntity movieEntity);
}
