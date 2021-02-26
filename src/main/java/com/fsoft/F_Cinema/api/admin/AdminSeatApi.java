package com.fsoft.F_Cinema.api.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsoft.F_Cinema.constants.ApiResponseConstant;
import com.fsoft.F_Cinema.constants.SeatConstant;
import com.fsoft.F_Cinema.constants.SeatStatusConstant;
import com.fsoft.F_Cinema.dto.ApiResponseDTO;
import com.fsoft.F_Cinema.dto.SeatDTO;
import com.fsoft.F_Cinema.dto.SeatParamsDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.RoomEntity;
import com.fsoft.F_Cinema.entities.SeatEntity;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.RoomService;
import com.fsoft.F_Cinema.services.SeatService;
import com.fsoft.F_Cinema.utils.Converter;

@RestController
@RequestMapping(path = { "/api/v1/admin/seat" })
public class AdminSeatApi {
	
	Logger logger = LoggerFactory.getLogger(AdminSeatApi.class);

	@Autowired
	private SeatService seatService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private CinemaService cinemaService;

	@Autowired
	private Converter converter;

	@PostMapping(path = { "/", "" }, 
			produces = { MediaType.APPLICATION_JSON_VALUE }, 
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> fetchSeats(@RequestBody SeatParamsDTO params, Principal principal) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		List<String> errors = new ArrayList<String>();
		try {
			List<SeatEntity> seatEnties = seatService.findByCinemaCodeAndRoomCode(
					params.getCinemaCode(),
					params.getRoomCode());
			List<SeatDTO> seats = new ArrayList<SeatDTO>();
			seatEnties.forEach(seat -> {
				SeatDTO seatDTO = converter.convertTo(seat);
				seatDTO.setRoom(null);
				seats.add(seatDTO);
			});
			
			Comparator<SeatDTO> orderByCode = new Comparator<SeatDTO>() {
				@Override
				public int compare(SeatDTO o1, SeatDTO o2) {
					String[] code1 = o1.getCode().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
					String[] code2 = o2.getCode().split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
					int compareNum = code1[0].compareTo(code2[0]);
					if (compareNum == 0) {
						compareNum = Integer.valueOf(code1[1]).compareTo(Integer.valueOf(code2[1]));
					}
					
					return compareNum;
				}
			};
			Collections.sort(seats, orderByCode);
			
			List<List<SeatDTO>> result = seatService.convertSeatToRender(seats);

			apiResponse.setStatus(HttpStatus.OK);
			apiResponse.setMessage("Successful");
			apiResponse.setCreatedDate(new Date());
			apiResponse.setCreatedBy(principal.getName());

			Map<String, Object> response = new HashMap<String, Object>();
			response.put("seats", result);
			response.put("response", apiResponse);
			response.put("cinema", params.getCinemaCode());
			response.put("room", params.getRoomCode());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			apiResponse.setCreatedDate(new Date());
			apiResponse.setMessage(e.getMessage());
			errors.add(e.getMessage());

			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}
	
	/**
	 * Save seat into database
	 * 
	 * @param <SeatParamsDTO> params
	 * @param <Principal> principal
	 */
	@PostMapping(path = { "/add" },
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addSeat(@RequestBody SeatParamsDTO params, Principal principal) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		apiResponse.setCreatedDate(new Date());
		apiResponse.setCreatedBy(principal.getName());
		List<String> errors = new ArrayList<String>();
		try {
			//checking if seat already exist
			SeatEntity isExist = seatService.findByCodeAndRoomCodeAndCinemaId(
					params.getCode(),
					params.getRoomCode(), 
					params.getCinemaCode());
			
			if (isExist != null) {
				apiResponse.setMessage("Seat already exist");
				return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
			}
			
			CinemaEntity cinemaEntity = cinemaService.findByCode(params.getCinemaCode());
			RoomEntity roomEntity = roomService.findByCodeAndCinemaId(params.getRoomCode(),
					cinemaEntity.getId());
			//create seat entity and set some params needed
			SeatEntity seatEntity = converter.convertTo(params);
			seatEntity.setCreatedDate(new Date());
			seatEntity.setCreatedBy(principal.getName());
			seatEntity.setRoom(roomEntity);
			seatEntity.setStatus(SeatStatusConstant.AVAILABLE.getValue());
			
			seatService.save(seatEntity);
			
			apiResponse.setMessage("Seat created successful");
			apiResponse.setStatus(HttpStatus.OK);
			
			return new ResponseEntity<>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			apiResponse.setMessage("Adding seat failed cause some errors");
			apiResponse.setStatus(HttpStatus.BAD_REQUEST);
			errors.add(e.getMessage());
			apiResponse.setErrors(errors);

			return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}
	
	/**
	 * Create a row of seat and assign row code base on alphabet character
	 * 
	 * @param params
	 */
	@PostMapping(path = { "/create" },
			produces = { MediaType.APPLICATION_JSON_VALUE },
			consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createSeats(@RequestBody SeatParamsDTO params, Principal principal) {
		ApiResponseDTO apiResponse = new ApiResponseDTO();
		apiResponse.setCreatedDate(new Date());
		apiResponse.setCreatedBy(principal.getName());
		List<String> errors = new ArrayList<String>();
		try {
			String characterOfRow = seatService.getNextSeatRow(params.getCinemaCode(), params.getRoomCode());
			CinemaEntity cinemaEntity = cinemaService.findByCode(params.getCinemaCode());
			RoomEntity roomEntity = roomService.findByCodeAndCinemaId(params.getRoomCode(),
					cinemaEntity.getId());
			if (roomEntity == null) {
				apiResponse.setMessage("Save list of seats failed");
				apiResponse.setStatus(HttpStatus.BAD_REQUEST);
				return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
			}
			int rowSize = SeatConstant.NUMBERSEATOFROW.getValue();
			List<SeatEntity> newSeats = new ArrayList<SeatEntity>();
			for (int i = 0; i < rowSize; i++) {
				SeatEntity seatEntity = new SeatEntity();
				seatEntity.setCode((characterOfRow + (i + 1)));
				seatEntity.setCreatedBy(principal.getName());
				seatEntity.setCreatedDate(new Date());
				seatEntity.setRoom(roomEntity);
				newSeats.add(seatEntity);
			}
			List<SeatEntity> seatSaved = seatService.saveMany(newSeats);
			List<SeatDTO> seats = new ArrayList<SeatDTO>();
			for (SeatEntity seat : seatSaved) {
				seat.getRoom().setSeats(null);
				seat.getRoom().setCinema(null);
				SeatDTO seatDTO = converter.convertTo(seat);
				seats.add(seatDTO);
			}

			apiResponse.setMessage("List of seats save successfull");
			apiResponse.setStatus(HttpStatus.OK);

			Map<String, Object> result = new HashMap<String, Object>();
			result.put(ApiResponseConstant.DATA.getKey(), seats);
			result.put(ApiResponseConstant.RESPONSE.getKey(), apiResponse);
			
			//exclude some fieds not necessary to client
			roomEntity.setCinema(null);
			roomEntity.setSeats(null);
			roomEntity.setSchedules(null);
			cinemaEntity.setRooms(null);
			cinemaEntity.setUsers(null);
			
			result.put("room", roomEntity);
			result.put("cinema", cinemaEntity);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(new StringBuilder("CREATE ROW OF SEAT: Failed cause: ")
					.append(e.getMessage())
					.toString());
			errors.add(e.getMessage());
			apiResponse.setErrors(errors);
			apiResponse.setStatus(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
}
