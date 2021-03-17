package com.fsoft.F_Cinema.services.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.constants.TicketStatusConstant;
import com.fsoft.F_Cinema.dto.TicketDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.entities.TicketEntity;
import com.fsoft.F_Cinema.repository.TicketRepository;
import com.fsoft.F_Cinema.services.MovieService;
import com.fsoft.F_Cinema.services.TicketService;
import com.fsoft.F_Cinema.utils.CodeGenerateUtils;
import com.fsoft.F_Cinema.utils.Converter;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CodeGenerateUtils codeGeneratorUtils;
	
	@Autowired
	private Converter converter;

	@Override
	public Integer generate(List<?> items, ScheduleEntity scheduleEntity, TicketDTO ticketDTO) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (items.isEmpty())
			throw new Exception("Items to generate ticket is empty");
		
		CinemaEntity cinemaEntity = scheduleEntity.getRoom().getCinema();
		RoomEntity roomEntity = scheduleEntity.getRoom();
		MovieEntity movieEntity = scheduleEntity.getMovie();

		if (items.get(0) instanceof SeatEntity) {
			for (Object seat : items) {
				
				Method method = seat.getClass().getMethod("getCode");
				String seatCode = (String) method.invoke(seat);
				
				TicketEntity ticketEntity = new TicketEntity();
				ticketEntity.setStartTime(scheduleEntity.getStartTime());
				ticketEntity.setExpireTime(scheduleEntity.getEndTime());
				ticketEntity.setSeat((SeatEntity) seat);
				ticketEntity.setSchedule(scheduleEntity);
				ticketEntity.setCreatedBy(authentication.getName());
				ticketEntity.setCreatedDate(new Date());
				ticketEntity.setCode(codeGeneratorUtils.ticketCodeGenerator(
						cinemaEntity.getCode(),
						roomEntity.getCode(), 
						movieEntity.getCode(), 
						seatCode));
				ticketEntity.setStatus(TicketStatusConstant.AVAILABLE.getKey());
				ticketEntity.setPrice(
						(long) this.calulateTicketPrice(movieEntity.getPrice(), 
								Math.toIntExact(ticketDTO.getPrice())));

				ticketRepository.save(ticketEntity);
			}
			
			//update number of ticket in movie
			Long numberOfTickets = this.countByMovie(movieEntity.getId());
			numberOfTickets += items.size();
			movieService.updateTickets(numberOfTickets, movieEntity.getId());
			return 1;
		}

		return 0;
	}

	@Override
	public List<TicketEntity> findAllByStatus(String status) {
		List<TicketStatusConstant> constants = Arrays.asList(TicketStatusConstant.values());
		for (TicketStatusConstant constant : constants) {
			if (constant.getKey().equals(status.toUpperCase()))
				return ticketRepository.findByStatus(status.toUpperCase());
		}
		
		return null;
	}

	@Override
	public List<TicketEntity> findAllByCinema(String cinemaCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketEntity> findAllByRoom(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketEntity> findAllByMovie(String movieCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TicketDTO> mapping(List<TicketEntity> tickets) {
		List<TicketDTO> result = new ArrayList<TicketDTO>();
		for (TicketEntity ticket : tickets) {
			if (ticket.getSeat().getName() != null) {
				TicketDTO ticketDTO = new TicketDTO();
				ticketDTO.setMovieDTO(converter.convertTo(ticket.getSchedule().getMovie()));
				ticketDTO.setStartTime(ticket.getStartTime());
				ticketDTO.setExpireTime(ticket.getExpireTime());
				ticketDTO.setCinema(ticket.getSchedule().getRoom().getCinema().getName());
				ticketDTO.setRoom(ticket.getSchedule().getRoom().getName());
				ticketDTO.setSeat(ticket.getSeat().getName());
				
				result.add(ticketDTO);
			}
		}
		
		return result;
	}
	
	private int calulateTicketPrice(int moviePrice, int ticketPrice) {
		return moviePrice + ticketPrice;
	}

	@Override
	public Long countByMovie(Long movieId) {
		return ticketRepository.countByMovieId(movieId);
	}
}
