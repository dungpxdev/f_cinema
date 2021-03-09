package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.ScheduleDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;

@Service
public interface ScheduleService {

	ScheduleEntity save(ScheduleDTO scheduleDTO,
						MovieEntity movieEntity, 
						CinemaEntity cinemaEntity,
						RoomEntity roomEntity);

	List<ScheduleEntity> findByIds(Long movieId, Long roomId);

	ScheduleEntity findByCode(String code);

}
