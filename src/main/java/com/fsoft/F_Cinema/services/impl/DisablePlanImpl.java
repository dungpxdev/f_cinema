package com.fsoft.F_Cinema.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.DisablePlanEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.repository.DisablePlansRepository;
import com.fsoft.F_Cinema.services.DisablePlansService;

@Service
public class DisablePlanImpl implements DisablePlansService {
	
	@Autowired
	private DisablePlansRepository disablePlanRepository;

	@Override
	public List<SeatEntity> findAllSeatsOcupied(String fromDate, String toDate) {
		return disablePlanRepository.findAllSeatsOcupied(fromDate, toDate);
	}

	@Override
	public List<DisablePlanEntity> findNextDisable() {
		return disablePlanRepository.findNextDisable();
	}

}
