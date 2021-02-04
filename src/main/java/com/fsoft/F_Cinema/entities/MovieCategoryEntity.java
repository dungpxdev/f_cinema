package com.fsoft.F_Cinema.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "movie_categories")
public class MovieCategoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2879224988886581589L;

	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true)
	private String code;

	@OneToOne(mappedBy = "movieCat")
	private MovieEntity movie;

}
