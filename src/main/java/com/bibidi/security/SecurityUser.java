package com.bibidi.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.bibidi.domain.UserVO;

import lombok.Getter;

@Getter
public class SecurityUser extends User {
	
	private static final long serialVersionUID = 1L;
	
	private UserVO user;
	
	public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public SecurityUser(UserVO userVO) {
		super(userVO.getId(), userVO.getPassword(), userVO.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList()));
		
		this.user = userVO;
	}
}
