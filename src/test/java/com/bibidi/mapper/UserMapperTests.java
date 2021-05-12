package com.bibidi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.UserVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class UserMapperTests {

	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testInsertUser() {
		UserVO user = new UserVO();
		user.setId("tid");
		user.setPassword(passwordEncoder.encode("tpd"));
		user.setUserEmail("temail@t.com");
		user.setNickname("tnickname");
		user.setActivityScore(3000L);
		
		log.info("NUMBER OF INSERTED USERS : " 
				+ userMapper.insertUser(user));
	}
	
	@Test
	public void testReadUserByUserId() {
		UserVO user = userMapper.readUserByUserId("bibidi");
		
		log.info(user);
		
		user.getRoles().forEach(role -> log.info(role));
	}
	
	@Test
	public void testIncreaseUserActivityScoreByUserNickname() {
		log.info("NUMBER OF USERS WITH INCREASED USERS : " 
				+ userMapper.increaseUserActivityScoreByUserNickname("bibidi", 100L));
	}
	
	@Test
	public void testDecreaseUserActivityScoreByUserNickname() {
		log.info("NUMBER OF USERS WITH INCREASED USERS : " 
				+ userMapper.decreaseUserActivityScoreByUserNickname("bibidi", 10L));
	}
	
	@Test
	public void testDeleteUserByUserId() {
		log.info("NUMBER OF DELETED USER : " 
				+ userMapper.deleteUserByUserId("tid"));
	}
}
