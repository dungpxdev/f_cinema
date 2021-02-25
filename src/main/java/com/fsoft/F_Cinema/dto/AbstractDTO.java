package com.fsoft.F_Cinema.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4485956048331103970L;

	private String createdBy;
	private String modifiedBy;
	private Date createdDate;
	private Date modifiedDate;

}
