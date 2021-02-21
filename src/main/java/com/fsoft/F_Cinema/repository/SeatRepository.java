package com.fsoft.F_Cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.SeatEntity;

@Repository
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

	/**
	 * 
	 * @param cinemaCode
	 * @param roomCode
	 * @return List<SeatEntity> = {seatId: <Long>, name: <String>, code: <String>, roomId: <Long>, cinemaId: <Long>}
	 */
	@Query(value = "select s.id as seatId, s.code, s.name, r.id as roomId, c.id as cinemaId from seat s inner join rooms r on s.room_id = r.id inner join cinema c on c.id = r.cinema_id where c.code = :cinemaCode and r.code = :roomCode",
			nativeQuery = true)
	List<SeatEntity> findByCinemaCodeAndRoomCode(@Param("cinemaCode") String cinemaCode, @Param("roomCode") String roomCode);
}
