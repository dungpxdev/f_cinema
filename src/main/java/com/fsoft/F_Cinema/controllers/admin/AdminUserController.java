package com.fsoft.F_Cinema.controllers.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fsoft.F_Cinema.constants.GenderConstant;
import com.fsoft.F_Cinema.constants.RoleConstant;
import com.fsoft.F_Cinema.dto.RoleDTO;
import com.fsoft.F_Cinema.dto.UserDTO;
import com.fsoft.F_Cinema.entities.RoleEntity;
import com.fsoft.F_Cinema.entities.UserEntity;
import com.fsoft.F_Cinema.services.RoleService;
import com.fsoft.F_Cinema.services.UserService;
import com.fsoft.F_Cinema.utils.Converter;

/**
 * 
 * @author DungPX2
 * @Class AdminUserController
 * @since 2/2021
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private Converter converter;

	@GetMapping("/register")
	public String login(Model model) {

		return "index";
	}

	/**
	 * Create new member for cinema's staff
	 * new User is automatic generated username, password and email
	 * 	- 
	 * @param principal
	 * @param userDTO
	 * @return redirect/admin
	 */
	@PostMapping(path = { "/", "" })
	public String postStaff(Principal principal, @Valid UserDTO userDTO) {
		try {
			if (!(principal instanceof UsernamePasswordAuthenticationToken) || 
					!"admin".equals(principal.getName()))
				throw new Exception("You Don't have permission for this operation !");
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
				return "redirect:/admin?err=" + errors.get(0);
			
			UserDTO userBuilder = UserDTO.builder()
					.createdBy(principal.getName())
					.createdDate(new Date())
					.username(username)
					.firstname(userDTO.getFirstname())
					.lastname(userDTO.getLastname())
					.fullname(new StringBuilder(userDTO.getFirstname())
							.append(" ")
							.append(userDTO.getLastname())
							.toString())
					.email(new StringBuilder(username)
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
						roleEntity.setCreatedBy(principal.getName());
						roleEntity.setCreatedDate(new Date());
						roleEntity = roleService.save(roleEntity);
					}
					roles.add(roleEntity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			UserEntity newUser = converter.convertTo(userBuilder);
			newUser.setRoles(roles);
			userService.save(newUser);
			
			logger.info(new StringBuilder("INSERTED: New user ")
					.append(userDTO.getUsername())
					.toString());
			
		} catch (Exception e) {
			logger.info(new StringBuilder("INSERTED: New user ")
					.append(userDTO.getUsername())
					.append(" failed cause: ")
					.append(e.getMessage())
					.toString());
		}

		return "redirect:/admin";
	}
}
