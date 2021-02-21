package com.fsoft.F_Cinema.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.repository.SeatRepository;
import com.fsoft.F_Cinema.services.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

	@Autowired
	private SeatRepository seatRepository;

	@Override
	public List<SeatEntity> findByCinemaCodeAndRoomCode(String cinemaCode, String roomCode) {
		try {
			return seatRepository.findByCinemaCodeAndRoomCode(cinemaCode, roomCode);
		} catch (Exception e) {
			logger.error(new StringBuilder("FIND SEAT ERROR: Cause: ")
					.append(e.getMessage())
					.append(" With CinemaID ").append(cinemaCode)
					.append(" And RoomID ").append(roomCode)
					.toString());
			return null;
		}
	}

}
