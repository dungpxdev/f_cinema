package com.fsoft.F_Cinema.converts;

import org.modelmapper.ModelMapper;

import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.UserEntity;

public class UserConverter {

	public UserEntity convertToEnTiTy(UserDTO userDTO) {
		return new ModelMapper().map(userDTO, UserEntity.class);
	}

	public UserDTO convertToDTO(UserEntity userEntity) {
		return new ModelMapper().map(userEntity, UserDTO.class);
	}
}
