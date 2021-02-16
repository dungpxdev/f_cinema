package com.fsoft.F_Cinema.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.MovieEntity;

@Repository
public interface MovieRepository extends CrudRepository<MovieEntity, Long> {

}
