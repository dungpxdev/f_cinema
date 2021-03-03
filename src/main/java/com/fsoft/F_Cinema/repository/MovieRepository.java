package com.fsoft.F_Cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, MovieRepositoryCustom {
	
	MovieEntity findByCode(String code);
}
