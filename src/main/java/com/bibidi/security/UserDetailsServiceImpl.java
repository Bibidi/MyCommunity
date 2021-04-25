package com.bibidi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bibidi.domain.UserVO;
import com.bibidi.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		log.warn("Load user by userId : " + userId);
		
		UserVO user = userMapper.findByUserId(userId);
		
		log.warn("queried by user mapper: " + user);
		
		return user == null ? null : new SecurityUser(user);
	}
	
}
