package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.ScheduleEntity;

@Service
public interface TicketService {
	Integer generate(List<?> items, ScheduleEntity scheduleEntity) throws Exception;

}
