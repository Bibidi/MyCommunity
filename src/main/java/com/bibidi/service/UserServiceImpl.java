package com.bibidi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bibidi.domain.UserVO;
import com.bibidi.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class UserServiceImpl implements UserService {
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;

	@Override
	public int registerUser(UserVO user) {
		
		log.info("registerUser.........");
		
		int countUserRegistered = userMapper.insertUser(user);
		
		return countUserRegistered;
	}

	@Override
	public UserVO getUserByUserId(String userId) {

		log.info("getUserByUserId...........");
		return userMapper.readUserByUserId(userId);
	}

	@Override
	public int increaseUserActivityScoreByUserId(String userId, Long points) {
		
		log.info("increaseUserActivityScoreByUserId...............");
		return userMapper.increaseUserActivityScoreByUserId(userId, points);
	}

	@Override
	public int decreaseUserActivityScoreByUserId(String userId, Long points) {
		
		log.info("decreaseUserActivityScoreByUserId.................");
		return userMapper.decreaseUserActivityScoreByUserId(userId, points);
	}

	@Override
	public int deleteUserByUserId(String userId) {
		
		log.info("deleteUserByUserId................");
		return userMapper.deleteUserByUserId(userId);
	}
	
}
