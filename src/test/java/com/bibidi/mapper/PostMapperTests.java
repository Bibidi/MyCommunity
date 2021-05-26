package com.bibidi.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.PostVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class PostMapperTests {

	@Setter(onMethod_ = @Autowired)
	private PostMapper postMapper;
	
	
	@Test
	public void testInsertPost() {
		
		PostVO post = new PostVO();
		post.setForumNumber(1L);
		post.setTitle("new title");
		post.setContent("new content");
		post.setWriter("bibidi");
		
		log.info("THE NUMBER OF INSERTED POSTS : " + postMapper.insertPost(post));
	}
	
	@Test
	public void testReadPostByPostNumber() {
		
		log.info(postMapper.readPostByPostNumber(1L));
	}
	
	@Test
	public void testReadPostsByForumNumber() {
		
		postMapper
			.readPostsByForumNumber(1L, 1L, 10L)
			.forEach(post -> log.info(post));
	}
	
	@Test
	public void testReadTotalPostsCountByForumNumber() {
		
		log.info("THE TOTAL COUNT OF POSTS : " + postMapper.readTotalPostsCountByForumNumber(1L));
	}
	
	@Test
	public void testUpdatePost() {
		PostVO post = new PostVO();
		post.setNumber(1L);
		post.setTitle("updated title");
		post.setContent("updated content");
		
		log.info("THE NUMBER OF UPDATED POSTS : " + postMapper.updatePost(post));
	}
	
	@Test
	public void testIncreasePostViewsByPostNumber() {
		Long postNumber = 1L;
		log.info("THE NUMBER OF POSTS WHOSE VIEWS INCREASED : " + postMapper.increasePostViewsByPostNumber(postNumber));
	}
	
	@Test
	public void testDeletePostByPostNumber() {
		log.info("THE NUMBER OF DELETED POSTS : " + postMapper.deletePostByPostNumber(2L));
	}
}
