package com.fsoft.F_Cinema.services.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.constants.CinemaConstants;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.UserEntity;
import com.fsoft.F_Cinema.repository.CinemaRepository;
import com.fsoft.F_Cinema.repository.UserRepository;
import com.fsoft.F_Cinema.services.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService {

	@Autowired
	private CinemaRepository cinemaRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public CinemaEntity save(CinemaEntity cinemaEntity) {
		return cinemaRepository.save(cinemaEntity);
	}

	@Override
	public List<CinemaEntity> findAll() {
		return (List<CinemaEntity>) cinemaRepository.findAll();
	}

	@Override
	public CinemaEntity findByOwner(Principal principal) {
		UserEntity currentManager = userRepository.findByUsername(principal.getName());
		CinemaEntity cinemaEntity = cinemaRepository.findByUserId(currentManager.getId());
		return cinemaEntity;
	}

	@Override
	public CinemaEntity findByCode(String code) {
		return cinemaRepository.findByCode(code);
	}

	@Override
	public CinemaEntity findById(Long id) {
		return cinemaRepository.findById(id).get();
	}

	@Override
	public CinemaEntity changeStatus(String cinemaCode) throws Exception {
		CinemaEntity cinemaEntity = cinemaRepository.findByCode(cinemaCode);
		if (cinemaEntity == null)
			throw new Exception("Cinema Not Found");

		if (CinemaConstants.ACTIVE.getKey().equals(cinemaEntity.getStatus()))
			cinemaEntity.setStatus(CinemaConstants.DEACTIVE.getKey());
		else
			cinemaEntity.setStatus(CinemaConstants.ACTIVE.getKey());

		return cinemaRepository.save(cinemaEntity);
	}

}
