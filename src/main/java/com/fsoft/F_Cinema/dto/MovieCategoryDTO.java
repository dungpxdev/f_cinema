package com.fsoft.F_Cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieCategoryDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191227421427664428L;

	private String name;
	private String code;
	private MovieDTO movie;

}
