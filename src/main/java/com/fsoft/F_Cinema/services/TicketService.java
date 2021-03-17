package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.TicketDTO;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.entities.TicketEntity;

@Service
public interface TicketService {
	Integer generate(List<?> items, ScheduleEntity scheduleEntity, TicketDTO ticketDTO) throws Exception;
	
	List<TicketEntity> findAllByStatus(String stautus);
	
	List<TicketEntity> findAllByCinema(String cinemaCode);
	
	List<TicketEntity> findAllByRoom(Long id);
	
	List<TicketEntity> findAllByMovie(String movieCode);
	
	List<TicketDTO> mapping(List<TicketEntity> tickets);
	
	Long countByMovie(Long movieId);
}
