package com.fsoft.F_Cinema.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "tickets")
public class TicketEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9074677179425249489L;

	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "expire_time")
	private Date expireTime;

	@Column(name = "price")
	private Long price;

	@Column(name = "gate")
	private String gate;

	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "seat_id")
	private SeatEntity seat;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "schedule_id")
	private ScheduleEntity schedule;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticket_category_id", referencedColumnName = "id")
	private TicketCategoryEntity ticketCat;
}
