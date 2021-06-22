package com.bibidi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibidi.domain.UserRoleVO;
import com.bibidi.domain.UserVO;
import com.bibidi.mapper.UserMapper;
import com.bibidi.mapper.UserRoleMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServiceImpl implements UserService {
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UserRoleMapper userRoleMapper;

	@Override
	public int registerUser(UserVO user) {
		
		log.info("registerUser.........");
		
		int result = userMapper.insertUser(user);
		
		// 해당 사용자에게 사용자 권한 부여
		if (result > 0) {
			UserRoleVO userRole = new UserRoleVO();
			userRole.setUserId(user.getId());
			userRole.setRoleName("ROLE_USER");
			
			result = userRoleMapper.insertUserRole(userRole);
		}
		
		return result;
	}

	@Override
	public UserVO getUserByUserId(String userId) {

		log.info("getUserByUserId...........");
		return userMapper.readUserByUserId(userId);
	}

	@Override
	public int increaseUserActivityScoreByUserNickname(String userNickname, Long points) {
		
		log.info("increaseUserActivityScoreByUserId...............");
		return userMapper.increaseUserActivityScoreByUserNickname(userNickname, points);
	}

	@Override
	public int decreaseUserActivityScoreByUserNickname(String userNickname, Long points) {
		
		log.info("decreaseUserActivityScoreByUserId.................");
		return userMapper.decreaseUserActivityScoreByUserNickname(userNickname, points);
	}

	@Override
	public int deleteUserByUserId(String userId) {
		
		log.info("deleteUserByUserId................");
		return userMapper.deleteUserByUserId(userId);
	}
	
}
