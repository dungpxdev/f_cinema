package com.fsoft.F_Cinema.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.dto.ScheduleDTO;

@Repository
public interface ScheduleRepositoryCustom {
	
	List<Object[]> search(ScheduleDTO scheduleDTO);

}
