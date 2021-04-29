package com.bibidi.service;

import static org.junit.Assert.assertNotNull;

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
public class UserServiceTests {

	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testExist() {
		
		log.info(userService);
		log.info(passwordEncoder);
		assertNotNull(userService);
		assertNotNull(passwordEncoder);
	}
	
	@Test
	public void testRegisterUser() {
		UserVO user= new UserVO();
		user.setId("tid");
		user.setPassword(passwordEncoder.encode("tpwd"));
		user.setUserEmail("temail@t.com");
		user.setNickname("tnickname");
		user.setActivityScore(3000L);
		
		log.info("NUMBER OF REGISTERED USERS : "
				+ userService.registerUser(user));
	}
	
	@Test
	public void testGetUserByUserId() {
		UserVO user = userService.getUserByUserId("bibidi");
		
		log.info(user);
		user.getRoles().forEach(role -> log.info(role));
	}
	
	@Test
	public void testIncreaseUserActivityScoreByUserId() {
		log.info("NUMBER OF USERS WITH INCREASED ACTIVITY SCORE : "
				+ userService.increaseUserActivityScoreByUserId("bibidi", 100L));
	}
	
	@Test
	public void testDecreaseUserActivityScoreByUserId() {
		log.info("NUMBER OF USERS WITH INCREASED ACTIVITY SCORE : "
				+ userService.decreaseUserActivityScoreByUserId("bibidi", 100L));
	}
	
	@Test
	public void testDeleteUserByUserId() {
		log.info("NUMBER OF DELETED USERS : "
				+ userService.deleteUserByUserId("tid"));
	}
}
