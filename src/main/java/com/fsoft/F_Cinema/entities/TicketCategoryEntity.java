package com.fsoft.F_Cinema.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ticket_categories")
public class TicketCategoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2810756407931602169L;

	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true)
	private String code;

	@OneToOne(mappedBy = "ticketCat")
	private TicketEntity ticket;
}
