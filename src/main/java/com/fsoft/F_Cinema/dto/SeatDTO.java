package com.fsoft.F_Cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4580596515816698457L;

	private String name;
	private String code;
	private Integer status;

}
