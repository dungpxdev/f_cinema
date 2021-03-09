package com.fsoft.F_Cinema.services.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.entities.TicketEntity;
import com.fsoft.F_Cinema.repository.TicketRepository;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private CinemaService cinemaService;

	@Override
	public Integer generate(List<?> items, ScheduleEntity scheduleEntity) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (items.isEmpty())
			throw new Exception("Items to generate ticket is empty");
		
		CinemaEntity cinemaEntity = cinemaService.findById(scheduleEntity.getMovie().getId());

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
				ticketEntity.setCode(new StringBuilder(cinemaEntity.getCode())
						.append(seatCode)
						.append(UUID.randomUUID().toString().split("-")[0])
						.toString().toUpperCase());

				ticketRepository.save(ticketEntity);
			}
			return 1;
		}

		return 0;
	}
	
}
