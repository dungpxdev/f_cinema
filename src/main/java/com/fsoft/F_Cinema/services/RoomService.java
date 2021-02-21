package com.fsoft.F_Cinema.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.RoomEntity;

@Service
public interface RoomService {
	RoomEntity save(RoomEntity roomEntity);
	
	List<RoomEntity> findAll();
	
	List<RoomEntity> findbyCinemaId(Long cinemaId);
}
