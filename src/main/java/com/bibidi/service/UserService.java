package com.bibidi.service;

import com.bibidi.domain.UserVO;

public interface UserService {

	public int registerUser(UserVO user);
	
	public UserVO getUserByUserId(String userId);
	
	public int increaseUserActivityScoreByUserNickname(String userNickname, Long points);
	
	public int decreaseUserActivityScoreByUserNickname(String userNickname, Long points);
	
	public int deleteUserByUserId(String userId);
}
