package com.fsoft.F_Cinema.repository;

import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.CinemaEntity;

@Repository
public interface CinemaRepositoryCustom {
	
	CinemaEntity update(CinemaEntity cinemaEntity);

}
