package com.fsoft.F_Cinema.entities;

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
@Table(name = "rooms")
public class RoomEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6828274552785108681L;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "status")
	private Integer status;

	@Column(name = "number_of_seat")
	private Integer numberOfSeat;

	@OneToMany(mappedBy = "room")
	private Set<ScheduleEntity> schedules = new HashSet<ScheduleEntity>();

	@ManyToOne
	@JoinColumn(name = "cinema_id", nullable = false)
	private CinemaEntity cinema;

	@OneToMany(mappedBy = "room")
	private Set<SeatEntity> seats = new HashSet<SeatEntity>();
	
	@OneToMany(mappedBy = "room")
	private Set<DisablePlanEntity> disablePlans = new HashSet<DisablePlanEntity>();

}
