package com.bibidi.service;

import com.bibidi.domain.UserVO;

public interface UserService {

	public int registerUser(UserVO user);
	
	public UserVO getUserByUserId(String userId);
	
	public int increaseUserActivityScoreByUserId(String userId, Long points);
	
	public int decreaseUserActivityScoreByUserId(String userId, Long points);
	
	public int deleteUserByUserId(String userId);
}
