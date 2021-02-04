package com.fsoft.F_Cinema.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.CinemaEntity;

@Repository
public interface CinemaRepository extends CrudRepository<CinemaEntity, Long> {

}
