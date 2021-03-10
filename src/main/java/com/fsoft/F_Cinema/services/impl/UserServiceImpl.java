package com.fsoft.F_Cinema.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fsoft.F_Cinema.constants.GenderConstant;
import com.fsoft.F_Cinema.constants.RoleConstant;
import com.fsoft.F_Cinema.dto.RoleDTO;
import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.CinemaEntity;
import com.fsoft.F_Cinema.entities.RoleEntity;
import com.fsoft.F_Cinema.entities.UserEntity;
import com.fsoft.F_Cinema.repository.UserRepository;
import com.fsoft.F_Cinema.services.CinemaService;
import com.fsoft.F_Cinema.services.RoleService;
import com.fsoft.F_Cinema.services.UserService;
import com.fsoft.F_Cinema.utils.Converter;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CinemaService cinemaService;
	
	@Autowired
	private Converter converter;

	/**
	 * Save new user to database User default role: GUEST
	 * 
	 * @param UserDTO
	 * @return UserEntity
	 */
	@Override
	public UserEntity save(UserEntity userEntity) {
		try {
			return userRepository.save(userEntity);
		} catch (Exception e) {
			logger.info(new StringBuilder("Insert user ")
					.append(userEntity.getEmail())
					.append(" failed cause error: ")
					.append(e.getMessage()).toString());
			return null;
		}

	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<UserDTO> findNewsUsers() {
		List<Object[]> userEntities = userRepository.findNewUsers();
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		for (Object[] user : userEntities) {
			UserDTO userDTO = new UserDTO();
			for (int i = 0; i < user.length; i++) {
				userDTO.setUsername(user[0].toString());
				userDTO.setEmail(user[1].toString());
				userDTO.setCreatedDate(new Date());
				Set<String> roles = new HashSet<String>();
				roles.add(user[3].toString());
				userDTO.setRoles(roles);
			}
			users.add(userDTO);
		}
		
		return users;
	}

	@Override
	public UserEntity createMember(UserDTO userDTO) throws Exception {
		List<String> errors = new ArrayList<String>();
		
		switch (userDTO.getGender()) {
		case "MALE":
			userDTO.setGender(GenderConstant.MALE.getKey());
			break;
		case "FEMALE":
			userDTO.setGender(GenderConstant.FEMALE.getKey());
			break;
		case "OTHERS":
			userDTO.setGender(GenderConstant.OTHERS.getKey());
			break;

		default:
			userDTO.setGender(GenderConstant.MALE.getKey());
			break;
		}
		
		String username = "";
		do {
			username = new StringBuilder(userDTO
					.getFirstname())
					.append(UUID.randomUUID())
					.toString()
					.split("-", 3)[0];
			UserEntity isExistingUser = userService.findByUsername(username);
			if (isExistingUser == null) {
				break;
			}
		} while (true);
		
		Set<RoleDTO> dtoRoles = new HashSet<RoleDTO>();
		userDTO.getRoles().forEach(role -> {
			RoleDTO roleDTO = new RoleDTO();
			switch (Integer.parseInt(role)) {
			case 2:
				roleDTO.setCode(RoleConstant.SCHEDULE_MANAGER.getValue());
				roleDTO.setName(RoleConstant.SCHEDULE_MANAGER.getKey());
				break;
			case 3:
				roleDTO.setCode(RoleConstant.TICKET_MANAGER.getValue());
				roleDTO.setName(RoleConstant.TICKET_MANAGER.getKey());
				break;
			case 4:
				roleDTO.setCode(RoleConstant.MOVIE_CATEGORY_MANAGER.getValue());
				roleDTO.setName(RoleConstant.MOVIE_CATEGORY_MANAGER.getKey());
				break;
			case 5:
				roleDTO.setCode(RoleConstant.MOVIE_MANAGER.getValue());
				roleDTO.setName(RoleConstant.MOVIE_MANAGER.getKey());
				break;
			case 6:
				roleDTO.setCode(RoleConstant.ROOM_MANAGER.getValue());
				roleDTO.setName(RoleConstant.ROOM_MANAGER.getKey());
				break;

			default:
				errors.add("403");
				break;
			}
			dtoRoles.add(roleDTO);
		});
		
		if (!errors.isEmpty())
			throw new Exception(errors.get(0).toString());//hard-code
		
		UserDTO userBuilder = UserDTO.builder()
				.createdBy(authentication.getName())
				.createdDate(new Date())
				.username(username)
				.firstname(userDTO.getFirstname())
				.lastname(userDTO.getLastname())
				.fullname(new StringBuilder(userDTO.getFirstname())
						.append(" ")
						.append(userDTO.getLastname())
						.toString())
				.email(new StringBuilder(username.toLowerCase())
						.append("@fmovie.com").toString())
				.password(passwordEncoder.encode(
						new StringBuilder(userDTO.getFirstname())
						.append("123@").toString()))
				.gender(userDTO.getGender())
				.build();
		
		Set<RoleEntity> roles = new HashSet<RoleEntity>();
		
		dtoRoles.forEach(role -> {
			RoleEntity roleEntity;
			try {
				roleEntity = (RoleEntity) converter.convertTo(role);
				if (roleService.findByCode(roleEntity.getCode()) == null) {
					roleEntity.setCreatedBy(authentication.getName());
					roleEntity.setCreatedDate(new Date());
					roleEntity = roleService.save(roleEntity);
				}
				roles.add(roleEntity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Set<CinemaEntity> cinemas = new HashSet<CinemaEntity>();
		CinemaEntity cinemaEntity = cinemaService.findByOwner(authentication);
		cinemas.add(cinemaEntity);
		
		UserEntity newUser = converter.convertTo(userBuilder);
		newUser.setRoles(roles);
		newUser.setCinemas(cinemas);
		
		userService.save(newUser);
		return newUser;
	}

}
