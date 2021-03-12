package com.fsoft.F_Cinema.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DisablePlanDTO extends AbstractDTO {
	/**
	* 
	*/
	private static final long serialVersionUID = -8397124903103693728L;

	private String movie;
	private String cinema;
	private String room;
	private String seat;
	private Date startDate;
	private Date expireDate;

}
