package com.fsoft.F_Cinema.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO extends AbstractDTO {

	@NotNull(message = "Please provide your username")
	@NotEmpty(message = "Please provide your username")
	private String username;

	@NotNull(message = "Please provide your password")
	@NotEmpty(message = "Please provide your password")
	private String password;

	private String firstname;

	private String lastname;

	private String fullname;

	private String phone;

	private String gender;

	private Date dob;

	@Email(message = "Email format invalid")
	@NotNull(message = "Please provide your email")
	@NotEmpty(message = "Please provide your email")
	private String email;

	private String avatar;

}
