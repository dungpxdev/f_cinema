package com.fsoft.F_Cinema.services.impl;

import java.util.Date;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.constants.RoleConstant;
import com.fsoft.F_Cinema.converts.UserConverter;
import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.RoleEntity;
import com.fsoft.F_Cinema.entities.UserEntity;
import com.fsoft.F_Cinema.repository.IRoleRepository;
import com.fsoft.F_Cinema.repository.IUserRepository;
import com.fsoft.F_Cinema.services.IUserService;

@Service
public class UserService implements IUserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Save new user to database User default role: GUEST
	 * 
	 * @param UserDTO
	 * @return UserEntity
	 */
	@Override
	public UserEntity save(@Valid UserDTO user) {
		try {
			UserEntity userEntity = new UserConverter().convertToEnTiTy(user);
			userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
			userEntity.setCreatedDate(new Date());
			RoleEntity roleEntity = new RoleEntity();
			roleEntity.setCode(RoleConstant.GUEST.getValue());
			roleEntity.setName(RoleConstant.GUEST.getKey());
			userEntity.getRoles().add(roleEntity);
			roleRepository.save(roleEntity);
			return userRepository.save(userEntity);
		} catch (Exception e) {
			logger.info(new StringBuilder("Insert user ")
					.append(user.getEmail())
					.append(" failed cause error: ")
					.append(e.getMessage()).toString());
			return null;
		}

	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
