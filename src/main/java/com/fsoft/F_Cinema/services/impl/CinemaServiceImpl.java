package com.fsoft.F_Cinema.services.impl;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.constants.CinemaConstants;
import com.fsoft.F_Cinema.dto.CinemaDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.UserEntity;
import com.fsoft.F_Cinema.repository.CinemaRepository;
import com.fsoft.F_Cinema.repository.UserRepository;
import com.fsoft.F_Cinema.services.CinemaService;

@Service
public class CinemaServiceImpl implements CinemaService {
	
	Logger logger = LoggerFactory.getLogger(CinemaServiceImpl.class);

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

	@Override
	public CinemaEntity update(String cinemaCode, CinemaDTO cinemaDTO) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CinemaEntity cinemaEntity = cinemaRepository.findByCode(cinemaCode);
		cinemaEntity.setNumberOfRoom(cinemaDTO.getNumberOfRoom());
		cinemaEntity.setName(cinemaEntity.getName());
		cinemaEntity.setCode(cinemaEntity.getCode());
		cinemaEntity.setAddress(cinemaEntity.getAddress());
		cinemaEntity.setNumberOfRoom(cinemaEntity.getNumberOfRoom());
		cinemaEntity.setModifiedBy(authentication.getName());
		cinemaEntity.setModifiedDate(new Date());
		return cinemaRepository.update(cinemaEntity);
	}

}
