package com.fsoft.F_Cinema.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4877000215964852285L;

	private String name;
	private String code;
	private Date startTime;
	private Date expireTime;
	private Long price;
	private String gate;
	private String seat;
	private Long quantity;
	private UserDTO user;
	private String schedule;
	private String room;
	private String cinema;
	private TicketCategoryDTO ticketCat;
	private MovieDTO movieDTO;
}
