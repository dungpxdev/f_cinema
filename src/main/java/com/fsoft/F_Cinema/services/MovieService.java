package com.fsoft.F_Cinema.services;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.MovieEntity;

@Service
public interface MovieService {
	MovieEntity save(MovieEntity movieEntity);
}
