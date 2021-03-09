package com.fsoft.F_Cinema.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fsoft.F_Cinema.constants.ApiResponseConstant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO extends AbstractDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2581795548658833214L;
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	private List<String> errors;
	private HttpStatus status;
	private String message;

	public Map<String, Object> apiResponseBuilder(List<String> errors, 
													HttpStatus status, 
													String message, 
													Object data) {
		Map<String, Object> result = new HashMap<String, Object>();
		this.setCreatedBy(authentication.getName());
		this.setCreatedDate(new Date());
		this.status = status;
		this.errors = errors;
		this.message = message;

		result.put(ApiResponseConstant.DATA.getKey(), data);
		result.put(ApiResponseConstant.RESPONSE.getKey(), this);
		
		return result;
	}
}
