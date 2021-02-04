package com.fsoft.F_Cinema.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.UserEntity;
import com.fsoft.F_Cinema.repository.UserRepository;
import com.fsoft.F_Cinema.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	/**
	 * Save new user to database User default role: GUEST
	 * 
	 * @param UserDTO
	 * @return UserEntity
	 */
	@Override
	public UserEntity save(UserEntity userEntity) {
		try {
			return userRepository.save(userEntity);
		} catch (Exception e) {
			logger.info(new StringBuilder("Insert user ")
					.append(userEntity.getEmail())
					.append(" failed cause error: ")
					.append(e.getMessage()).toString());
			return null;
		}

	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<UserDTO> findNewsUsers() {
		List<Object[]> userEntities = userRepository.findNewUsers();
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		for (Object[] user : userEntities) {
			UserDTO userDTO = new UserDTO();
			for (int i = 0; i < user.length; i++) {
				userDTO.setUsername(user[0].toString());
				userDTO.setEmail(user[1].toString());
				userDTO.setCreatedDate(new Date());
				Set<String> roles = new HashSet<String>();
				roles.add(user[3].toString());
				userDTO.setRoles(roles);
			}
			users.add(userDTO);
		}
		
		return users;
	}

}
