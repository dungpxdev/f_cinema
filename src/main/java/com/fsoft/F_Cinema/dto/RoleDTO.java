package com.fsoft.F_Cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -467530505770168231L;
	
	private String name;
	private Integer code;

}
