package com.fsoft.F_Cinema.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.repository.MovieRepositoryCustom;

@Repository
@Transactional
public class MovieRepositoryImpl implements MovieRepositoryCustom {

	Logger logger = LoggerFactory.getLogger(MovieRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MovieEntity findOneAndUpdate(Long id, MovieEntity newMovie) {
		MovieEntity oldMovie = entityManager.find(MovieEntity.class, id);
		newMovie.setId(oldMovie.getId());
		entityManager.merge(newMovie);
		return newMovie;
	}

	@Override
	public MovieEntity updateTickets(Long number, Long movieId) {
		MovieEntity oldMovie = entityManager.find(MovieEntity.class, movieId);
		oldMovie.setNumberOfTickets(number);
		entityManager.merge(oldMovie);
		return oldMovie;
	}

}
