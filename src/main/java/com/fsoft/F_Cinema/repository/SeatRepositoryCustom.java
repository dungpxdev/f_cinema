package com.fsoft.F_Cinema.repository;

import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.SeatEntity;

@Repository
public interface SeatRepositoryCustom {
	
	SeatEntity findByCode(String code, String roomCode, String cinemaCode);

	SeatEntity update(SeatEntity seatEntity);
}
