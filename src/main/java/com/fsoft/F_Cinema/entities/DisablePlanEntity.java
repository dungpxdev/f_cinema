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
	@JoinColumn(name = "cinema_id")
	private CinemaEntity cinema;

	@ManyToOne
	@JoinColumn(name = "room_id")
	private RoomEntity room;

	@ManyToOne
	@JoinColumn(name = "seat_id")
	private SeatEntity seat;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private MovieEntity movie;
}
