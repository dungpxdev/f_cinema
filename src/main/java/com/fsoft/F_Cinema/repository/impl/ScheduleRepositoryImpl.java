package com.fsoft.F_Cinema.repository.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.dto.ScheduleDTO;
import com.fsoft.F_Cinema.repository.ScheduleRepositoryCustom;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> search(ScheduleDTO scheduleDTO) {
		try {
			String sql = buildSearchWhereClause(scheduleDTO);
			Query query = entityManager.createNativeQuery(sql);

			return query.getResultList();
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	private String buildSearchWhereClause(ScheduleDTO scheduleDTO) {
		try {
			StringBuilder sql = new StringBuilder("select * from schedules where ");
			String sqlNewestData = " and start_time >= GETDATE()";

			if (StringUtils.isNotBlank(scheduleDTO.getCode())) {
				sql.append(" code = '")
				   .append(scheduleDTO.getCode())
				   .append("' ");
			}

			if (scheduleDTO.getStartTime() != null) {
				sql.append(" and ")
				   .append(" datediff(day, start_time, '")
				   .append(new SimpleDateFormat("yyyy-MM-dd")
						   .format(scheduleDTO.getStartTime()))
				   .append("') = 0 ");
			}

			if (StringUtils.isNotBlank(scheduleDTO.getMovie())) {
				sql.append(" and ")
				   .append(" movie_id = ")
				   .append(scheduleDTO.getMovie());
			}

			if (StringUtils.isNotBlank(scheduleDTO.getCreatedBy())) {
				sql.append(" and ")
				   .append(" created_by = '")
				   .append(scheduleDTO.getCreatedBy())
				   .append("' ");
			}
			
			if (StringUtils.isNotBlank(scheduleDTO.getStatus())) {
				sql.append(" and ")
				   .append(" status = '")
				   .append(scheduleDTO.getStatus())
				   .append("' ");
			}
			
			sql.append(sqlNewestData);
			
			if (sql.toString().split("where")[1].trim().startsWith("and")) {
				sql = new StringBuilder(sql.toString().replaceFirst("and", ""));
			}
			
			return sql.toString();
		} catch (Exception e) {
			return "select * from schedules where 1 = 1";
		}
	}

}
