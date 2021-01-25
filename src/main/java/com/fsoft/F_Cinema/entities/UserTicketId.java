package com.fsoft.F_Cinema.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTicketId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4620672982028287070L;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "ticket_id")
	private Long ticketId;

}
