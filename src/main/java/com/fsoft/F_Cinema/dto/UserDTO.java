package com.fsoft.F_Cinema.dto;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3058557665740988106L;
	
	private String username;

	private String password;

	private String firstname;

	private String lastname;

	private String fullname;

	private String phone;

	private String gender;

	private String address;

	private String dob;

	private String avatar;

	private String email;

	private Set<String> roles;
	
	private Set<String> cinemas;

	public void setFullname() {
		this.fullname = new StringBuilder(this.firstname)
				.append(" ")
				.append(this.lastname).toString();
	}

	@Builder
	public UserDTO(String createdBy, String modifiedBy, Date createdDate, Date modifiedDate, String username,
			String password, String firstname, String lastname, String fullname, String phone, String gender,
			String address, String dob, String avatar, String email, Set<String> roles, Set<String> cinemas) {
		super(createdBy, modifiedBy, createdDate, modifiedDate);
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.fullname = fullname;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
		this.dob = dob;
		this.avatar = avatar;
		this.email = email;
		this.roles = roles;
		this.cinemas = cinemas;
	}
}
