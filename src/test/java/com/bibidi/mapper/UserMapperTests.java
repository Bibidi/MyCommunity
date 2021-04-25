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
	public void testFindById() {
		UserVO user = userMapper.findByUserId("bibidi");
		
		log.info(user);
		
		user.getRoleList().forEach(role -> log.info(role));
	}
}
