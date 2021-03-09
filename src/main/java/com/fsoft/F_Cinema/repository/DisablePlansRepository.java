package com.fsoft.F_Cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.DisablePlanEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;

@Repository
public interface DisablePlansRepository extends JpaRepository<DisablePlanEntity, Long> {

	@Query(value = "select * from disable_plan where seat_id != null and start_time >= :fromDate  and end_time <= :toDate",
			nativeQuery = true)
	List<SeatEntity> findAllSeatsOcupied(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
