package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.DisablePlanEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;

@Service
public interface DisablePlansService {
	
	List<SeatEntity> findAllSeatsOcupied(String fromDate, String toDate);
	
	List<DisablePlanEntity> findNextDisable();
}
