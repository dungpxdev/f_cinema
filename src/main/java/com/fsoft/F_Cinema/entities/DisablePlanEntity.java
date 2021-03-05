package com.fsoft.F_Cinema.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disable_plan")
public class DisablePlanEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7811716561414604846L;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "end_time")
	private Date endTime;
	
	@ManyToOne
	@JoinColumn(name = "schedule_id", nullable = false)
	private ScheduleEntity schedule;
	
	@ManyToOne
	@JoinColumn(name = "cinema_id", nullable = false)
	private CinemaEntity cinema;
	
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private RoomEntity room;
	
	@ManyToOne
	@JoinColumn(name = "seat_id", nullable = false)
	private SeatEntity seat;
	
	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private MovieEntity movie;
}
