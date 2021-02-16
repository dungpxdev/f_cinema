package com.fsoft.F_Cinema.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5480598774141925278L;

	private String name;
	private String code;
	private String startTime;
	private String endTime;
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
	private MultipartFile poster;
	private String image;
	private String trailer;
	private MovieCategoryDTO movieCat;
	private Set<ScheduleDTO> schedules = new HashSet<ScheduleDTO>();

}
