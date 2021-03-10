package com.fsoft.F_Cinema.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.SeatDTO;
import com.fsoft.F_Cinema.dto.TicketDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.repository.SeatRepository;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.DisablePlansService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.services.SeatService;
import com.fsoft.F_Cinema.utils.StringUltis;

@Service
public class SeatServiceImpl implements SeatService {

	Logger logger = LoggerFactory.getLogger(SeatServiceImpl.class);

	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private DisablePlansService disablePlanService;
	
	@Autowired
	private StringUltis stringUltils;

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
	public SeatEntity findByCodeAndRoomCodeAndCinemaCode(String code, String roomCode, String cinemaCode)
			throws Exception {
		CinemaEntity cinemaEntity = cinemaService.findByCode(cinemaCode);
		RoomEntity roomEntity = roomService.findByCodeAndCinemaId(roomCode, cinemaEntity.getId());

		if (roomEntity == null || cinemaEntity == null)
			throw new Exception("The code given invalid");
		for (SeatEntity seat : roomEntity.getSeats()) {
			if (code.equals(seat.getCode()))
				return seat;
		}
		
		return null;
	}

	/**
	 * get next <String> character
	 * ex: A -> B
	 * 
	 * @return <String> next character of seat base on cinema code and room code
	 */
	@Override
	public String getNextSeatRow(String cinemaCode, String roomCode) throws Exception {
		List<SeatEntity> seats = this.findByCinemaCodeAndRoomCode(cinemaCode, roomCode);
		
		if (seats.isEmpty())
			return "A".toUpperCase();
		
		char lastSeatCode = seats.get(seats.size() - 1).getCode().toCharArray()[0];
		int asciivalue = lastSeatCode;
		if (asciivalue > 122 || asciivalue < 65) {
			if (asciivalue < 97 && asciivalue > 90) {
				throw new Exception("Invalid seat code");
			}
		}

		++asciivalue;

		return String.valueOf((char) asciivalue).toUpperCase();
	}

	@Override
	public List<SeatEntity> saveMany(List<SeatEntity> seats) {
		List<SeatEntity> saved = new ArrayList<SeatEntity>(seats.size());
		if (!seats.isEmpty())
			seats.forEach(seat -> {
				SeatEntity entity = this.save(seat);
				saved.add(entity);
			});

		return saved;
	}

	/**
	 * @param sorted list
	 */
	@Override
	public List<List<SeatDTO>> convertSeatToRender(List<SeatDTO> seatDTOs) {
		try {
			String startCharacterRow = "A";
			List<List<SeatDTO>> tableOfSeats = new ArrayList<List<SeatDTO>>();
			List<SeatDTO> columns = new ArrayList<SeatDTO>();
			for (SeatDTO seat : seatDTOs) {
				if (!seat.getCode().startsWith(startCharacterRow)) {
					tableOfSeats.add(columns);
					startCharacterRow = stringUltils.getNextCharacter(startCharacterRow);
					columns = new ArrayList<SeatDTO>();
				}
				
				columns.add(seat);
			}
			
			return tableOfSeats;
		} catch (Exception e) {
			logger.error(new StringBuilder("Hanle Seat to render failed cause: ")
					.append(e.getMessage()).toString());
			return null;
		}

	}

	@Override
	public SeatEntity update(SeatEntity seatEntity) {
		return seatRepository.update(seatEntity);
	}

	@Override
	public List<SeatEntity> findAllSeatNotOcupied(ScheduleEntity scheduleEntity, TicketDTO ticketDTO) {
		List<SeatEntity> seats = seatService.findByCinemaCodeAndRoomCode(
				ticketDTO.getCinema(), 
				ticketDTO.getRoom());
		List<SeatEntity> ocupiedSeats = disablePlanService.findAllSeatsOcupied(
				scheduleEntity.getStartTime().toString(),
				scheduleEntity.getEndTime().toString());
		
		for (SeatEntity seat : seats) {
			for (SeatEntity seatOcupied : ocupiedSeats) {
				if (seat.getId() == seatOcupied.getId()) {
					seats.remove(seat);
				}
			}
		}

		return seats;
	}

}
