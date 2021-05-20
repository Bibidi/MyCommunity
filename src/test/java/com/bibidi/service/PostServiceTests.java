package com.bibidi.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.bibidi.domain.PostVO;
import com.bibidi.domain.SearchCriteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class PostServiceTests {

	@Setter(onMethod_ = @Autowired)
	private PostService postService;
	
	
	@Test
	public void testNotNull() {
		
		assertNotNull(postService);
		log.info(postService);
	}
	
	@Test
	public void testRegisterPost() {
		PostVO post = new PostVO();
		post.setForumNumber(1L);
		post.setTitle("new title");
		post.setContent("new content");
		post.setWriter("bibidi");
		
		log.info("THE NUMBER OF REGISTERED POSTS : " + postService.registerPost(post));
	}
	
	@Test
	public void testGetPostByPostNumber() {
		
		log.info(postService.getPostByPostNumber(3L));
	}
	
	@Test
	public void testGetPostsByForumSlug() {
		
		String forumSlug = "notice";
		SearchCriteria searchCriteria = new SearchCriteria(2L, 20L);
		
		log.info("searchCriteria : " + searchCriteria);
		
		postService
			.getPostsByForumSlug(forumSlug, searchCriteria)
			.forEach(post -> log.info(post));
	}
	
	@Test
	public void testModifyPost() {
		
		PostVO post = new PostVO();
		post.setNumber(2L);
		post.setTitle("updated title");
		post.setContent("updated content");
		
		log.info("THE NUMBER OF MODIFIED POSTS : " + postService.modifyPost(post));
	}
	
	@Test
	public void testDeletePostByPostNumber() {
		
		log.info("THE NUMBER OF DELETED POSTS : " + postService.deletePostByPostNumber(4L));
	}
}
