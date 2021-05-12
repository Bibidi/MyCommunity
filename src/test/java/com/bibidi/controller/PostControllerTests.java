package com.bibidi.controller;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bibidi.domain.PostVO;
import com.bibidi.service.PostService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class PostControllerTests {

	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;
	
	@Setter(onMethod_ = @Autowired)
	private PostService postService;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testGetPostsByForumSlug() throws Exception {
		
		String forumSlug = "notice";
		
		URI uri = new URI("/posts/" + forumSlug);
		
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.request("GET", uri))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	@Test
	public void testGetPostByPostNumber() throws Exception {
		
		String forumSlug = "notice";
		Long postNumber = 1L;
		
		URI uri = new URI("/posts/" + forumSlug + "/" + postNumber);
		
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.request("GET", uri))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	@Test
	public void testGetPostRegistrationForm() throws Exception {
		
		String forumSlug = "notice";
		URI uri = new URI("/posts/" + forumSlug + "/registration");
		
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.request("GET", uri))
				.andReturn()
				.getModelAndView()
				.getViewName());
	}
	
	@Test
	public void testPostPostRegistrationForm() throws Exception {
		
		String forumSlug = "notice";
		
		URI uri = new URI("/posts/" + forumSlug);
		
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.request("POST", uri)
						.param("title", "mock test title")
						.param("content", "mock test content")
						.param("writer", "bibidi"))
				.andReturn()
				.getModelAndView()
				.getViewName());
	}
	
	@Test
	public void testPatchPost() throws Exception {
		
		String forumSlug = "notice";
		Long postNumber = 3L;
		
		URI uri = new URI("/posts/" + forumSlug + "/" + postNumber);
		
		PostVO postBeforePatch = postService.getPostByPostNumber(postNumber);
		
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.request("PATCH", uri)
						.param("number", postNumber.toString())
						.param("title", "test patch post")
						.param("content", "patch post content"))
				.andReturn()
				.getModelAndView()
				.getViewName());
		
		PostVO postAfterPatch = postService.getPostByPostNumber(postNumber);
		
		log.info(postBeforePatch);
		log.info(postAfterPatch);
	}
	
	@Test
	public void testDeletePost() throws Exception {
		
		String forumSlug = "notice";
		Long postNumber = 5L;
		
		URI uri = new URI("/posts/" + forumSlug + "/" + postNumber);
		
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.request("DELETE", uri))
				.andReturn()
				.getModelAndView()
				.getViewName());
	}
}
