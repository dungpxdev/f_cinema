package com.fsoft.F_Cinema.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fsoft.F_Cinema.entities.BaseEntity;
import com.fsoft.F_Cinema.entities.MovieCategoryEntity;
import com.fsoft.F_Cinema.entities.ScheduleEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5480598774141925278L;

	private String name;
	private String code;
	private Date startTime;
	private Date endTime;
	private String cast;
	private String director;
	private String language;
	private Integer length;
	private Integer rating;
	private String description;
	private String country;
	private Integer year;
	private Long numberOfTickets;
	private Integer status;
	private String image;
	private String trailer;
	private MovieCategoryEntity movieCat;
	private Set<ScheduleEntity> schedules = new HashSet<ScheduleEntity>();

}
