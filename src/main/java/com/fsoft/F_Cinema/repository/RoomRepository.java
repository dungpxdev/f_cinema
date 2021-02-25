package com.fsoft.F_Cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.RoomEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

	@Query(value = "select * from rooms where cinema_id = :cinemaId", 
		   nativeQuery = true)
	List<RoomEntity> findbyCinemaid(@Param("cinemaId") Long cinemaId);
	
	@Query(value = "select * from rooms where cinema_id = :cinemaId and code = :code",
		   nativeQuery = true)
	RoomEntity findByCodeAndCinemaId(
			@Param("code") String code,
			@Param("cinemaId") Long cinemaId);
	
	RoomEntity findByCode(String code);
}
