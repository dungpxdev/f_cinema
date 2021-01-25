package com.fsoft.F_Cinema.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.F_Cinema.entities.RoleEntity;
import com.fsoft.F_Cinema.entities.UserEntity;
import com.fsoft.F_Cinema.repository.IUserRepository;

@Component
public class UserDetailServiceCustom implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	/**
	 * checking if exist email then client can login to their account
	 * 
	 * @param { String } email
	 * @return { UserDetails } User
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username);
		
		if (userEntity == null)
			throw new UsernameNotFoundException(username);

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (RoleEntity role : userEntity.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        
		return new User(userEntity.getUsername(), userEntity.getPassword(), grantedAuthorities);
	}

}
