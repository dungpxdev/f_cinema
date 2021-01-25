package com.fsoft.F_Cinema.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
@Table(name = "users")
public class UserEntity extends BaseEntity {

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "phone")
	private String phone;

	@Column(name = "gender")
	private String gender;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "avatar")
	private String avatar;

	@Column(name = "address")
	private String address;

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "user_role", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();

	@OneToMany(mappedBy = "user")
	private Set<PaymentEntity> payments = new HashSet<PaymentEntity>();

	@OneToMany(mappedBy = "user")
	private Set<TicketEntity> tickets = new HashSet<TicketEntity>();

}
