package com.fsoft.F_Cinema.dto;

import java.util.HashSet;
import java.util.Set;

import com.fsoft.F_Cinema.entities.CinemaEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1199769817975737884L;

	private String name;
	private String code;
	private Integer status;
	private Integer numberOfSeat;
	private Set<ScheduleDTO> schedules = new HashSet<ScheduleDTO>();
	private CinemaEntity cinema;
	private Set<SeatDTO> seats = new HashSet<SeatDTO>();
}
