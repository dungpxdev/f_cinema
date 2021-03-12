package com.fsoft.F_Cinema.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.repository.CinemaRepositoryCustom;

@Repository
@Transactional
public class CinemaRepositoryImpl implements CinemaRepositoryCustom {

	Logger logger = LoggerFactory.getLogger(SeatRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CinemaEntity update(CinemaEntity cinemaEntity) {
		CinemaEntity oldCinema = entityManager.find(CinemaEntity.class, cinemaEntity.getId());
		entityManager.merge(oldCinema);

		return oldCinema;
	}

}
