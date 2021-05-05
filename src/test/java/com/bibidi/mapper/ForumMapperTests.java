package com.bibidi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.ForumVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ForumMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ForumMapper forumMapper;
	
	@Test
	public void testInsertForum() {
		ForumVO forum = new ForumVO();
		forum.setName("tName");
		forum.setDescription("tDescription");
		forum.setSlug("tSlug");
		
		log.info("THE NUMBER OF INSERTED FORUMS : "
				+ forumMapper.insertForum(forum));
	}
	
	@Test
	public void testReadForumByForumNumber() {
		
		log.info(forumMapper.readForumByForumNumber(1L));
	}
	
	@Test
	public void testUpdateForum() {
		
		ForumVO forum = new ForumVO();
		forum.setNumber(2L);
		forum.setName("updated name");
		forum.setDescription("updated description");
		forum.setSlug("updated slug");
		
		log.info("THE NUMBER OF UPDATED FORUMS : "
				+ forumMapper.updateForum(forum));
	}
	
	@Test
	public void testDeleteForumByForumNumber() {
		
		log.info("THE NUMBER OF DELETED FORUMS : "
				+ forumMapper.deleteForumByForumNumber(0L));
	}
}
