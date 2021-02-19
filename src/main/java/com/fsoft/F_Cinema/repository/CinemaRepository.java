package com.fsoft.F_Cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.CinemaEntity;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {

	@Query(value = "select top 1 * from cinema c left join user_cinema uc on c.id = uc.cinema_id where uc.user_id = :userId",
		   nativeQuery = true)
	CinemaEntity findByUserId(@Param("userId") Long id);
}
