package com.fsoft.F_Cinema.utils;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fsoft.F_Cinema.constants.StatusConstant;
import com.fsoft.F_Cinema.dto.CinemaDTO;
import com.fsoft.F_Cinema.dto.MovieDTO;
import com.fsoft.F_Cinema.dto.RoleDTO;
import com.fsoft.F_Cinema.dto.RoomDTO;
import com.fsoft.F_Cinema.dto.ScheduleDTO;
import com.fsoft.F_Cinema.dto.SeatDTO;
import com.fsoft.F_Cinema.dto.SeatParamsDTO;
import com.fsoft.F_Cinema.dto.TicketDTO;
import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.MovieEntity;
import com.fsoft.F_Cinema.entities.RoleEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.entities.TicketEntity;
import com.fsoft.F_Cinema.entities.UserEntity;

@Component
public class Converter {

	public Object convertTo(Object object) throws Exception {
		if (!(object instanceof Object))
			throw new Exception("Can not mapping 2 different type of object");

		return new ModelMapper().map(object, Object.class);
	}

	public UserEntity convertTo(UserDTO userDTO) {
		return new ModelMapper().map(userDTO, UserEntity.class);
	}

	public UserDTO convertTo(UserEntity userEntity) {
		return new ModelMapper().map(userEntity, UserDTO.class);
	}

	public RoleEntity convertTo(RoleDTO roleDTO) {
		return new ModelMapper().map(roleDTO, RoleEntity.class);
	}

	public MovieEntity convertTo(MovieDTO movieDTO) {
		MovieEntity movieBuilder = MovieEntity.builder()
				.name(movieDTO.getName())
				.code(movieDTO.getCode())
				.startTime(new Date())
				.endTime(new Date())
				.cast(movieDTO.getCast())
				.director(movieDTO.getDirector())
				.language(movieDTO.getLanguage())
				.length(movieDTO.getLength())
				.rating(movieDTO.getRating())
				.description(movieDTO.getDescription())
				.country(movieDTO.getCountry())
				.year(movieDTO.getYear())
				.numberOfTickets(movieDTO.getNumberOfTickets())
				.status(StatusConstant.ACTIVE.getValue())
				.image(movieDTO.getImage())
				.trailer(movieDTO.getTrailer())
				.build();

		return movieBuilder;
	}
	
	public MovieDTO convertTo(MovieEntity movieEntity) {
		return new ModelMapper().map(movieEntity, MovieDTO.class);
	}
	
	public RoomEntity convertTo(RoomDTO roomDTO) {
		return new ModelMapper().map(roomDTO, RoomEntity.class);
	}
	
	public CinemaEntity convertTo(CinemaDTO cinemaDTO) {
		return new ModelMapper().map(cinemaDTO, CinemaEntity.class);
	}
	
	public SeatEntity convertTo(SeatDTO seatDTO) {
		return new ModelMapper().map(seatDTO, SeatEntity.class);
	}
	
	public SeatEntity convertTo(SeatParamsDTO seatParamsDTO) {
		SeatDTO seatDTO = new SeatDTO();
		seatDTO.setCode(seatParamsDTO.getCode());
		seatDTO.setName(seatParamsDTO.getName());
		seatDTO.setStatus(seatParamsDTO.getStatus());
		seatDTO.setCreatedDate(seatParamsDTO.getCreatedDate());
		seatDTO.setCreatedBy(seatParamsDTO.getCreatedBy());
		seatDTO.setModifiedDate(seatParamsDTO.getModifiedDate());
		seatDTO.setModifiedBy(seatParamsDTO.getModifiedBy());
		return new ModelMapper().map(seatDTO, SeatEntity.class);
	}

	public SeatDTO convertTo(SeatEntity seatEntity) {
		return new ModelMapper().map(seatEntity, SeatDTO.class);
	}
	
	public RoomDTO convertTo(RoomEntity roomEntity) {
		return new ModelMapper().map(roomEntity, RoomDTO.class);
	}
	
	public ScheduleEntity convertTo(ScheduleDTO scheduleDTO) {
		return new ModelMapper().map(scheduleDTO, ScheduleEntity.class);
	}
	
	public ScheduleDTO convertTo(ScheduleEntity scheduleEntity) {
		return new ModelMapper().map(scheduleEntity, ScheduleDTO.class);
	}
	
	public TicketEntity convertTo(TicketDTO ticketDTO) {
		return new ModelMapper().map(ticketDTO, TicketEntity.class);
	}
	
}
