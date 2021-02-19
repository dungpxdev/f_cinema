package com.fsoft.F_Cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
	/**
	 * Find user by username
	 * 
	 * @param username
	 * @return UserEntity
	 */
	UserEntity findByUsername(String username);
}
