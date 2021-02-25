package com.fsoft.F_Cinema.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.repository.SeatRepository;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.services.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

	Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CinemaService cinemaService;

	@Override
	public List<SeatEntity> findByCinemaCodeAndRoomCode(String cinemaCode,
			String roomCode) {
		try {
			CinemaEntity cinema = cinemaService.findByCode(cinemaCode);
			RoomEntity room = roomService.findByCodeAndCinemaId(roomCode, cinema.getId());
			List<SeatEntity> seats = new ArrayList<SeatEntity>(room.getSeats().size());
			CollectionUtils.addAll(seats, room.getSeats().iterator());

			seats.sort(new Comparator<SeatEntity>() {
				@Override
				public int compare(SeatEntity seat1, SeatEntity seat2) {
					return seat1.getCode().compareTo(seat2.getCode());
				}
			});

			return seats;
		} catch (Exception e) {
			logger.error(new StringBuilder("FIND SEAT ERROR: Cause: ")
					.append(e.getMessage())
					.append(" With CinemaID ").append(cinemaCode)
					.append(" And RoomID ").append(roomCode)
					.toString());
			return null;
		}
	}

	@Override
	public SeatEntity save(SeatEntity seatEntity) {
		return seatRepository.save(seatEntity);
	}

	/**
	 * @throws InvalidCodeException
	 * 
	 */
	@Override
	public SeatEntity findByCodeAndRoomCodeAndCinemaId(String code, 
			String roomCode, 
			String cinemaCode)
			throws Exception {
		CinemaEntity cinemaEntity = cinemaService.findByCode(cinemaCode);
		RoomEntity roomEntity = roomService.findByCodeAndCinemaId(roomCode,
				cinemaEntity.getId());

		if (roomEntity == null || cinemaEntity == null)
			throw new Exception("The code given invalid");
		
		return seatRepository.findByCodeAndRoomIdAndCinemaId(
				code,
				roomEntity.getId(),
				cinemaEntity.getId());
	}

}
