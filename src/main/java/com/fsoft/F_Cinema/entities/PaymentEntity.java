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
@Table(name = "payment")
public class PaymentEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5761589493361372204L;

	@Column(name = "total")
	private Long total;

	@Column(name = "method")
	private String method;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "expiration_date")
	private Date expirationDate;

	@Column(name = "cvv")
	private String cvv;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

}
