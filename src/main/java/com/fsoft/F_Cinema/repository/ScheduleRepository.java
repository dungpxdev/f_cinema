package com.fsoft.F_Cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.ScheduleEntity;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

	@Query(value = "select * from schedules where movie_id = :movieId and room_id = :roomId",
			nativeQuery = true)
	List<ScheduleEntity> findByIds(@Param("movieId") Long movieId, @Param("roomId") Long roomId);
	
	ScheduleEntity findByCode(String code);
}
