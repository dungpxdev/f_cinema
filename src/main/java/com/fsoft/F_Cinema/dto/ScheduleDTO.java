package com.fsoft.F_Cinema.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6369599256006136583L;

	private String name;
	private String code;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	private String movie;
	private String room;
	private String cinema;
	private String status;
	private Set<TicketDTO> tickets = new HashSet<TicketDTO>();
}
