package com.fsoft.F_Cinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.repository.ScheduleRepository;
import com.fsoft.F_Cinema.services.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public ScheduleEntity save(ScheduleEntity scheduleEntity) {
		return scheduleRepository.save(scheduleEntity);
	}

}
