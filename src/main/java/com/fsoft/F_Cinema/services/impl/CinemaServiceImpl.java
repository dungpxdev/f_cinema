package com.fsoft.F_Cinema.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.CinemaDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.repository.CinemaRepository;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.utils.Converter;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRepository cinemaRepository;

	@Override
	public CinemaEntity save(CinemaDTO cinemaDTO) {
		try {
			return cinemaRepository.save((CinemaEntity) new Converter().convertTo(cinemaDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CinemaEntity> findAll() {
		return (List<CinemaEntity>) cinemaRepository.findAll();
	}

}
