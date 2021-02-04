package com.fsoft.F_Cinema.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 820604150119042973L;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private Integer code;

	@ManyToMany(mappedBy = "roles")
	private Set<UserEntity> roles = new HashSet<UserEntity>();
}
