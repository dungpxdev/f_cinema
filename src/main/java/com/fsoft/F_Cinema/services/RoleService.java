package com.fsoft.F_Cinema.services;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.RoleEntity;

@Service
public interface RoleService {
	RoleEntity save(RoleEntity roleEntity);

	RoleEntity findByCode(Integer code);

	Long saveUserRole(Long userId, Long roleId);
}
