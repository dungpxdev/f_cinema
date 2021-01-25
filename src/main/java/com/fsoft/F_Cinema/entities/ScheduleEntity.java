package com.fsoft.F_Cinema.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "schedules")
public class ScheduleEntity extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "end_time")
	private Date endTime;

	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private MovieEntity movie;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private RoomEntity room;
	
	@OneToMany(mappedBy = "schedule")
	private Set<TicketEntity> tickets = new HashSet<TicketEntity>();

}
