package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.UserEntity;

@Service
public interface UserService {
	/**
	 * 
	 * @param user
	 * @return
	 */
	UserEntity save(UserEntity userEntity);

	UserEntity findByUsername(String username);
	
	List<UserDTO> findNewsUsers();
	
	UserEntity createMember(UserDTO userDTO) throws Exception;

}
