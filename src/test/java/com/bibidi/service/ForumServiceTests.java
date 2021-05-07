package com.bibidi.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.ForumVO;
import com.bibidi.mapper.RoleMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ForumServiceTests {

	@Setter(onMethod_ = @Autowired)
	private ForumService forumService;
	
	@Setter
	private RoleMapper roleMapper;
	
	@Test
	public void testExist() {
		
		log.info(forumService);
		assertNotNull(forumService);
	}
	
	@Test
	public void testRegisterForum() {
		ForumVO forum = new ForumVO();
		forum.setName("tsName");
		forum.setDescription("tsDescription");
		forum.setSlug("tsSlug");
		
		log.info("THE NUMBER OF REGISTERED FORUMS : "
				+ forumService.registerForum(forum));
		
		log.info(roleMapper.readRoleByRoleName("ROLE_ADMIN_" + forum.getSlug().toUpperCase()));
		log.info(roleMapper.readRoleByRoleName("ROLE_MANAGER_" + forum.getSlug().toUpperCase()));
	}
	
	@Test
	public void testGetForumByForumNumber() {
		
		log.info(forumService.getForumByForumNumber(1L));
	}
	
	@Test
	public void testModifyForum() {
		ForumVO forum = new ForumVO();
		forum.setNumber(3L);
		forum.setName("updated name");
		forum.setDescription("updated description");
		forum.setSlug("updated slug");
		
		log.info("THE NUMBER OF MODIFIED FORUMS : " 
				+ forumService.modifyForum(forum));
	}
	
	@Test
	public void testDeleteForumByForumNumber() {
		log.info("THE NUMBER OF DELETED FORUMS : "
				+ forumService.deleteForumByForumNumber(0L));
	}
}
