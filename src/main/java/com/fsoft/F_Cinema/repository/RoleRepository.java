package com.fsoft.F_Cinema.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.RoleEntity;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
	RoleEntity findByCode(Integer code);

	@Modifying
	@Query(value = "insert into user_role (user_id, role_id) values (:user, :role)", nativeQuery = true)
	Long saveUserRole(@Param("user") Long user, @Param("role") Long role);
}
