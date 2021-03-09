package com.fsoft.F_Cinema.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedules")
@EqualsAndHashCode(of = { "movie", "room", "tickets", "disablePlans" },
				callSuper = false)
public class ScheduleEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4325745940783371239L;

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

	@OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
	private Set<TicketEntity> tickets = new HashSet<TicketEntity>();

	@OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
	private Set<DisablePlanEntity> disablePlans = new HashSet<DisablePlanEntity>();
}
