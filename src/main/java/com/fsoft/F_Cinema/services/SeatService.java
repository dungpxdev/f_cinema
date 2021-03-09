package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.SeatDTO;
import com.fsoft.F_Cinema.dto.TicketDTO;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;

@Service
public interface SeatService {

	List<SeatEntity> findByCinemaCodeAndRoomCode(String cinemaCode, String roomCode);
	
	SeatEntity save(SeatEntity seatEntity);

	SeatEntity findByCodeAndRoomCodeAndCinemaCode(String code, 
			String roomCode, String cinemaCode) throws Exception;
	
	String getNextSeatRow(String cinemaCode, String roomCode) throws Exception;

	List<SeatEntity> saveMany(List<SeatEntity> seats);
	
	List<List<SeatDTO>> convertSeatToRender(List<SeatDTO> seatDTOs);

	SeatEntity update(SeatEntity seatEntity);
	
	List<SeatEntity> findAllSeatNotOcupied(ScheduleEntity scheduleEntity, TicketDTO ticketDTO);
}
