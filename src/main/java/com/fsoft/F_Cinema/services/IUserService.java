package com.fsoft.F_Cinema.services;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.UserEntity;

@Service
public interface IUserService {
	/**
	 * 
	 * @param user
	 * @return
	 */
	UserEntity save(UserDTO user);

	UserEntity findByUsername(String username);
}
