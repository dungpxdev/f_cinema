package com.fsoft.F_Cinema.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6255197106749172119L;

	@NotEmpty(message = "Cinema name can not be empty")
	private String name;

	@NotEmpty(message = "Cinema code can not be empty")
	private String code;

	private String address;

	private Integer numberOfRoom;

}
