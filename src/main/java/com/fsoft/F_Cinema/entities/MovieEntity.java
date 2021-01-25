package com.fsoft.F_Cinema.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "start_time")
	private Date startTime;

	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "cast")
	private String cast;

	@Column(name = "director")
	private String director;

	@Column(name = "language")
	private String language;

	@Column(name = "length")
	private Integer length;

	@Column(name = "")
	private Integer rating;

	@Column(name = "description")
	private String description;

	@Column(name = "country")
	private String country;

	@Column(name = "year")
	private Integer year;

	@Column(name = "number_of_tickets")
	private Long numberOfTickets;

	@Column(name = "status")
	private Integer status;

	@Column(name = "image")
	private String image;

	@Column(name = "trailer")
	private String trailer;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_category_id", referencedColumnName = "id")
	private MovieCategoryEntity movieCat;

	@OneToMany(mappedBy = "movie")
	private Set<ScheduleEntity> schedules = new HashSet<ScheduleEntity>();

}
