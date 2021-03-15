package com.fsoft.F_Cinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.RoleEntity;
import com.fsoft.F_Cinema.repository.RoleRepository;
import com.fsoft.F_Cinema.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleEntity save(RoleEntity roleEntity) {
		return roleRepository.save(roleEntity);
	}

	/**
	 *
	 */
	@Override
	public RoleEntity findByCode(Integer code) {
		return roleRepository.findByCode(code);
	}

	@Override
	public Long saveUserRole(Long userId, Long roleId) {
		return roleRepository.saveUserRole(userId, roleId);
	}

	@Override
	public RoleEntity findById(Long id) {
		return roleRepository.findById(id).get();
	}

}
