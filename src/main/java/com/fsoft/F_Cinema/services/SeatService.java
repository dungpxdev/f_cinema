package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.SeatEntity;

@Service
public interface SeatService {

	List<SeatEntity> findByCinemaCodeAndRoomCode(String cinemaCode, String roomCode);
	
	SeatEntity save(SeatEntity seatEntity);

	SeatEntity findByCodeAndRoomCodeAndCinemaId(String code, 
			String roomCode, String cinemaCode) throws Exception;

}
