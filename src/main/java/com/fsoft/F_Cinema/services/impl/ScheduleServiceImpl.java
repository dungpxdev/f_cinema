package com.fsoft.F_Cinema.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.constants.ScheduleStatusConstant;
import com.fsoft.F_Cinema.dto.ScheduleDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.repository.ScheduleRepository;
import com.fsoft.F_Cinema.services.ScheduleService;
import com.fsoft.F_Cinema.utils.CodeGenerateUtils;
import com.fsoft.F_Cinema.validation.DateValidation;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Autowired
	private CodeGenerateUtils codeGeneratorUtils;
	
	@Autowired
	private DateValidation dateValidation;

	@Override
	public List<ScheduleEntity> findByIds(Long movieId, Long roomId) {
		return scheduleRepository.findByIds(movieId, roomId);
	}

	@Override
	public ScheduleEntity findByCode(String code) {
		return scheduleRepository.findByCode(code);
	}

	@Override
	public ScheduleEntity save(ScheduleDTO scheduleDTO, 
			MovieEntity movieEntity, 
			CinemaEntity cinemaEntity,
			RoomEntity roomEntity) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext()
											.getAuthentication();
		scheduleDateValidate(scheduleDTO, movieEntity);

		ScheduleEntity scheduleEntity = scheduleBuild(scheduleDTO, 
				movieEntity, cinemaEntity, roomEntity, authentication);
		scheduleEntity.setStatus(ScheduleStatusConstant.WAITING.getKey());

		return scheduleRepository.save(scheduleEntity);
	}

	private ScheduleEntity scheduleBuild(ScheduleDTO scheduleDTO, 
			MovieEntity movieEntity, 
			CinemaEntity cinemaEntity,
			RoomEntity roomEntity, 
			Authentication authentication) {
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
		return scheduleEntity;
	}

	private void scheduleDateValidate(ScheduleDTO scheduleDTO, MovieEntity movieEntity) throws Exception {
		if (scheduleDTO.getStartTime().compareTo(new Date()) < 0)
			throw new Exception("The Time provide is passed");
		
		long diffInMinutes = Math.abs(scheduleDTO.getEndTime().getTime() - scheduleDTO.getStartTime().getTime());
		long scheduleDuration = TimeUnit.DAYS.convert(diffInMinutes, TimeUnit.MINUTES);
		if (scheduleDuration < movieEntity.getLength()) {
			throw new Exception("The Time provide is less than movie length");
		}
	    
		List<ScheduleEntity> schedules = this.findByDate(scheduleDTO.getStartTime());
		if (!schedules.isEmpty()) {
			List<Date> range1 = new ArrayList<Date>();
			range1.add(scheduleDTO.getStartTime());
			range1.add(scheduleDTO.getEndTime());
			for (ScheduleEntity schedule : schedules) {
				List<Date> range2 = new ArrayList<Date>();
				range2.add(schedule.getStartTime());
				range2.add(schedule.getEndTime());
				Boolean isOverlapped = dateValidation.isDateOverlapped(range1, range2);
				if (isOverlapped) {
					throw new Exception(new StringBuilder("Schedule provided is overlapped with ")
							.append(schedule.getCode()).toString());
				}
			}
		}
	}

	@Override
	public List<ScheduleEntity> findByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ScheduleEntity> schedules = scheduleRepository.findByDate(
											sdf.format(date).toString());
		return schedules;
	}

	@Override
	public List<ScheduleEntity> findNexts() {
		return scheduleRepository.findNexts();
	}

}
