package com.bibidi.mapper;

import org.apache.ibatis.annotations.Param;

import com.bibidi.domain.UserVO;

public interface UserMapper {
	
	public int insertUser(UserVO user);
	
	public UserVO readUserByUserId(String userId);
	
	public int increaseUserActivityScoreByUserNickname(@Param("userNickname") String userNickname, @Param("points") Long points);
	
	public int decreaseUserActivityScoreByUserNickname(@Param("userNickname") String userNickname, @Param("points") Long points);
	
	public int deleteUserByUserId(String userId);
}
