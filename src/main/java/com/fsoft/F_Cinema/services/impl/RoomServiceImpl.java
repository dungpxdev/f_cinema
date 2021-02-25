package com.fsoft.F_Cinema.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.repository.RoomRepository;
import com.fsoft.F_Cinema.services.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Override
	public RoomEntity save(RoomEntity roomEntity) {
		return roomRepository.save(roomEntity);
	}

	@Override
	public List<RoomEntity> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public List<RoomEntity> findbyCinemaId(Long cinemaId) {
		return roomRepository.findbyCinemaid(cinemaId);
	}

	@Override
	public RoomEntity findByCodeAndCinemaId(String code, Long cinemaId) {
		return roomRepository.findByCodeAndCinemaId(code, cinemaId);
	}

	@Override
	public RoomEntity findById(Long id) {
		return roomRepository.findById(id).get();
	}

	@Override
	public RoomEntity findByCode(String code) {
		return roomRepository.findByCode(code);
	}

}
