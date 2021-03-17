package com.fsoft.F_Cinema.services;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.MovieDTO;
import com.fsoft.F_Cinema.entities.MovieEntity;

@Service
public interface MovieService {
	MovieEntity save(MovieEntity movieEntity);
	
	List<MovieEntity> findAll();
	
	Optional<MovieEntity> findById(Long id);
	
	MovieEntity findOneAndUpdate(String id, MovieEntity movieEntity);
	
	MovieEntity findByCode(String code);
	
	MovieEntity movieBuild(MovieDTO movieDTO, Principal principal) throws IOException;
	
	MovieEntity updateTickets(Long number, Long movieId);
}
