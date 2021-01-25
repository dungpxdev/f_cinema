package com.fsoft.F_Cinema.entities;

import java.util.ArrayList;
import java.util.List;

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

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private Integer code;

	@ManyToMany(mappedBy = "roles")
	private List<UserEntity> roles = new ArrayList<UserEntity>();
}
