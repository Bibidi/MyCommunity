package com.bibidi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.RoleVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RoleMapperTests {

	@Setter(onMethod_ = @Autowired)
	private RoleMapper roleMapper;
	
	@Test
	public void testInsertRole() {
		RoleVO role = new RoleVO();
		role.setName("tName");
		
		log.info("THE NUMBER OF INSERTED ROLES : " + roleMapper.insertRole(role));
	}
	
	@Test
	public void testReadRoleByRoleName() {
		
		log.info(roleMapper.readRoleByRoleName("ROLE_ADMIN"));
	}
	
	@Test
	public void testDeleteRoleByRoleName() {
		
		log.info("THE NUMBER OF DELETED ROLES : " + roleMapper.deleteRoleByRoleName("tName"));
	}
}
