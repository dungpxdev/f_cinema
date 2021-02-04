package com.fsoft.F_Cinema.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fsoft.F_Cinema.dto.RoleDTO;
import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.RoleEntity;
import com.fsoft.F_Cinema.entities.UserEntity;

@Component
public class Converter {

	public Object convertTo(Object object) throws Exception {
		if (!(object instanceof Object))
			throw new Exception("Can not mapping 2 different type of object");

		return new ModelMapper().map(object, Object.class);
	}

	public UserEntity convertTo(UserDTO userDTO) {
		return new ModelMapper().map(userDTO, UserEntity.class);
	}
	
	public UserDTO convertTo(UserEntity userEntity) {
		return new ModelMapper().map(userEntity, UserDTO.class);
	}

	public RoleEntity convertTo(RoleDTO roleDTO) {
		return new ModelMapper().map(roleDTO, RoleEntity.class);
	}

}
