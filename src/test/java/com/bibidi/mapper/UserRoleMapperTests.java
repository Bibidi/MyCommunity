package com.bibidi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.UserRoleVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserRoleMapperTests {

	@Setter(onMethod_ = @Autowired)
	private UserRoleMapper userRoleMapper;
	
	
	@Test
	public void testInsertUserRole() {
		UserRoleVO userRole = new UserRoleVO();
		userRole.setUserId("bibidi");
		userRole.setRoleName("ROLE_ADMIN_NOTICE");
		
		log.info("THE NUMBER OF INSERTED USER ROLES : " 
				+ userRoleMapper.insertUserRole(userRole));
	}
	
	@Test
	public void testDeleteUserRole() {
		UserRoleVO userRole = new UserRoleVO();
		userRole.setUserId("bibidi");
		userRole.setRoleName("ROLE_ADMIN_NOTICE");
				
		log.info("THE NUMBER OF DELETED USER ROLES : " 
				+ userRoleMapper.deleteUserRole(userRole));
	}
}
