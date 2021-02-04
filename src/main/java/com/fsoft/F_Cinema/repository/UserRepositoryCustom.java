package com.fsoft.F_Cinema.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom {
	List<Object[]> findNewUsers();
}
