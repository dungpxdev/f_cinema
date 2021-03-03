package com.fsoft.F_Cinema.services;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.ScheduleEntity;

@Service
public interface ScheduleService {

	ScheduleEntity save(ScheduleEntity scheduleEntity);

}
