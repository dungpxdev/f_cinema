package com.fsoft.F_Cinema.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
@Table(name = "seat")
public class SeatEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6110362291696885344L;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "status")
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private RoomEntity room;

	@OneToMany(mappedBy = "seat")
	private List<TicketEntity> tickets = new ArrayList<TicketEntity>();

	@OneToMany(mappedBy = "seat")
	private Set<DisablePlanEntity> disablePlans = new HashSet<DisablePlanEntity>();
}
