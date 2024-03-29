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

import org.hibernate.annotations.Nationalized;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2303284766282907280L;
	
	@Builder
	public MovieEntity(String name, String code, Date startTime, Date endTime, String cast, String director,
			String language, Integer length, Integer rating, String description, String country, Integer year,
			Long numberOfTickets, String status, String image, String trailer, MovieCategoryEntity movieCat,
			Set<ScheduleEntity> schedules) {
		this.name = name;
		this.code = code;
		this.startTime = startTime;
		this.endTime = endTime;
		this.cast = cast;
		this.director = director;
		this.language = language;
		this.length = length;
		this.rating = rating;
		this.description = description;
		this.country = country;
		this.year = year;
		this.numberOfTickets = numberOfTickets;
		this.status = status;
		this.image = image;
		this.trailer = trailer;
		this.movieCat = movieCat;
		this.schedules = schedules;
	}

	@Nationalized
	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true)
	private String code;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "start_time")
	private Date startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "end_time")
	private Date endTime;

	@Nationalized
	@Column(name = "cast")
	private String cast;

	@Nationalized
	@Column(name = "director")
	private String director;

	@Column(name = "language")
	private String language;

	@Column(name = "length")
	private Integer length;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "description",
			columnDefinition = "nvarchar",
			length = 4000)
	private String description;

	@Column(name = "country")
	private String country;

	@Column(name = "year")
	private Integer year;

	@Column(name = "number_of_tickets")
	private Long numberOfTickets;

	@Column(name = "status")
	private String status;

	@Column(name = "image")
	private String image;

	@Column(name = "trailer")
	private String trailer;

	@Column(name = "price")
	private Integer price;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_category_id", referencedColumnName = "id")
	private MovieCategoryEntity movieCat;

	@OneToMany(mappedBy = "movie")
	private Set<ScheduleEntity> schedules = new HashSet<ScheduleEntity>();
	
	@OneToMany(mappedBy = "movie")
	private Set<DisablePlanEntity> disablePlans = new HashSet<DisablePlanEntity>();

}
