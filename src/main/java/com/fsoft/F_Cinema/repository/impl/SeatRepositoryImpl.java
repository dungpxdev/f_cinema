package com.fsoft.F_Cinema.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.repository.SeatRepositoryCustom;

@Repository
@Transactional
public class SeatRepositoryImpl implements SeatRepositoryCustom {
	
	Logger logger = LoggerFactory.getLogger(SeatRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public SeatEntity findByCode(String code, String roomCode, String cinemaCode) {
		
		return null;
	}

	@Override
	public SeatEntity update(SeatEntity seatEntity) {
		SeatEntity oldSeat = entityManager.find(SeatEntity.class, seatEntity.getId());
		oldSeat.setName(seatEntity.getName());
		oldSeat.setStatus(seatEntity.getStatus());
		entityManager.merge(oldSeat);
		
		return oldSeat;
	}
}
