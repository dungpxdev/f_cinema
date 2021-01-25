package com.fsoft.F_Cinema.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.UserEntity;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {
	/**
	 * Find user by username
	 * 
	 * @param username
	 * @return UserEntity
	 */
	UserEntity findByUsername(String username);
}
