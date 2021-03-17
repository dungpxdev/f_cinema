package com.fsoft.F_Cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.ScheduleEntity;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long>, ScheduleRepositoryCustom {

	@Query(value = "select * from schedules where movie_id = :movieId and room_id = :roomId",
			nativeQuery = true)
	List<ScheduleEntity> findByIds(@Param("movieId") Long movieId, @Param("roomId") Long roomId);
	
	ScheduleEntity findByCode(String code);
	
	@Query(value = "select * from schedules where convert(varchar(10), start_time, 120) = :date",
		   nativeQuery = true)
	List<ScheduleEntity> findByDate(@Param("date") String date);
	
	@Query(value = "select * from schedules where start_time >= GETDATE()", 
		   nativeQuery = true)
	List<ScheduleEntity> findNexts();
	
	@Query(value = "select distinct id, start_time, created_by, code from schedules where start_time >= GETDATE()",
		   nativeQuery = true)
	List<ScheduleEntity> findAllNextSchedules();
}
