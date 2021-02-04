package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.CinemaDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;

@Service
public interface CinemaService {
	CinemaEntity save(CinemaDTO cinemaDTO);
	List<CinemaEntity> findAll();
}
