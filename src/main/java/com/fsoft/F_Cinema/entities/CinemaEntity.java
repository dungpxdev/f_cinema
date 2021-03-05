package com.fsoft.F_Cinema.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cinema")
public class CinemaEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251325890843119554L;

	@Nationalized
	@Column(name = "name")
	private String name;

	@Column(name = "code", unique = true)
	private String code;

	@Column(name = "number_of_room")
	private Integer numberOfRoom;

	@Nationalized
	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "cinema")
	private Set<RoomEntity> rooms = new HashSet<RoomEntity>();
	
	@ManyToMany(mappedBy = "cinemas")
	private Set<UserEntity> users = new HashSet<UserEntity>();
	
	@OneToMany(mappedBy = "cinema")
	private Set<DisablePlanEntity> disablePlans = new HashSet<DisablePlanEntity>();

}
