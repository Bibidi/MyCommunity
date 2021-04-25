package com.bibidi.mapper;

import com.bibidi.domain.UserVO;

public interface UserMapper {

	public UserVO findByUserId(String userId);
	
}
