package com.fsoft.F_Cinema.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.fsoft.F_Cinema.repository.UserRepositoryCustom;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
	
	Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findNewUsers() {
		try {
			StringBuilder sql = new StringBuilder("select top 5 u.username, u.email, u.created_date, r.name from users u ")
					.append("left join user_role ur on u.id = ur.user_id ")
					.append("right join roles r on r.id = ur.role_id ")
					.append("where r.name != N'ADMIN' ")
					.append("order by u.created_date desc");
			Query query = entityManager.createNativeQuery(sql.toString());
			return query.getResultList();
		} catch (Exception e) {
			logger.error(new StringBuilder("ERROR: Select new users failed cause: ")
					.append(e.getMessage()).toString());
			return null;
		}
	}

}
