package com.fsoft.F_Cinema.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.ScheduleDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.repository.ScheduleRepository;
import com.fsoft.F_Cinema.services.ScheduleService;
import com.fsoft.F_Cinema.utils.CodeGenerateUtils;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private CodeGenerateUtils codeGeneratorUtils;

	@Override
	public List<ScheduleEntity> findByIds(Long movieId, Long roomId) {
		return scheduleRepository.findByIds(movieId, roomId);
	}

	@Override
	public ScheduleEntity findByCode(String code) {
		return scheduleRepository.findByCode(code);
	}

	@Override
	public ScheduleEntity save(ScheduleDTO scheduleDTO, MovieEntity movieEntity, CinemaEntity cinemaEntity,
			RoomEntity roomEntity) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ScheduleEntity scheduleEntity = new ScheduleEntity();
		String code = codeGeneratorUtils.scheduleCodeGenerator(
				cinemaEntity.getCode(),
				roomEntity.getCode(),
				movieEntity.getCode());
		
		scheduleEntity.setCode(code);
		scheduleEntity.setName(scheduleDTO.getName());
		scheduleEntity.setCreatedDate(new Date());
		scheduleEntity.setCreatedBy(authentication.getName());
		scheduleEntity.setMovie(movieEntity);
		scheduleEntity.setStartTime(scheduleDTO.getStartTime());
		scheduleEntity.setEndTime(scheduleDTO.getEndTime());
		scheduleEntity.setRoom(roomEntity);
		
		return scheduleRepository.save(scheduleEntity);
	}

}
