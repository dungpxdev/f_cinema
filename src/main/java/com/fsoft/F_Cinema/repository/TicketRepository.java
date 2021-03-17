package com.fsoft.F_Cinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.entities.TicketEntity;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

	List<TicketEntity> findByStatus(String status);
	
	@Query(value = "select count(*) from tickets t inner join schedules s on t.schedule_id = s.id where s.movie_id = :id",
		   nativeQuery = true)
	Long countByMovieId(@Param("id") Long id);
	
}
