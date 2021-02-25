package com.fsoft.F_Cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatParamsDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6185817957589650910L;

	private String cinemaCode;
	private String roomCode;
	private String name;
	private String code;
	private Integer status;

}
