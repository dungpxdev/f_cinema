package com.fsoft.F_Cinema.repository;

import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.MovieEntity;

@Repository
public interface MovieRepositoryCustom {

	MovieEntity findOneAndUpdate(Long id, MovieEntity movieEntity);

}
