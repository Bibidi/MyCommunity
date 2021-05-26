package com.bibidi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibidi.domain.Page;
import com.bibidi.domain.PostVO;
import com.bibidi.domain.SearchCriteria;
import com.bibidi.mapper.ForumMapper;
import com.bibidi.mapper.PostMapper;
import com.bibidi.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class PostServiceImpl implements PostService{

	@Setter(onMethod_ = @Autowired)
	private PostMapper postMapper;
	
	@Setter(onMethod_ = @Autowired)
	private ForumMapper ForumMapper;
	
	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	private static final Long SCORE = 30L;
	
	@Override
	public int registerPost(PostVO post) {
		
		log.info("register Post.............");
		
		int result = postMapper.insertPost(post);
		if (result > 0) {
			userMapper.increaseUserActivityScoreByUserNickname(post.getWriter(), SCORE);
		}
		
		return result;
	}

	@Override
	public PostVO getPostByPostNumber(Long postNumber) {
		
		log.info("get post by post number .............");
		
		postMapper.increasePostViewsByPostNumber(postNumber);
		return postMapper.readPostByPostNumber(postNumber);
	}

	@Override
	public List<PostVO> getPostsByForumSlug(String forumSlug, SearchCriteria searchCriteria) {
		
		log.info("get posts by forum slug..............");
		Long forumNumber = ForumMapper.readForumByForumSlug(forumSlug).getNumber();
		
		Long to = searchCriteria.getPageNumber() * searchCriteria.getContentQuantity();
		Long from = to - searchCriteria.getContentQuantity() + 1;
		
		return postMapper.readPostsByForumNumber(forumNumber, from, to);
	}
	
	@Override
	public long getTotalPostsCountByForumNumber(Long forumNumber) {
		
		log.info("get total posts count by forum number..................");
		
		return postMapper.readTotalPostsCountByForumNumber(forumNumber);
	}

	@Override
	public int modifyPost(PostVO post) {
		
		log.info("modify post.........................");
		return postMapper.updatePost(post);
	}

	@Override
	public int deletePostByPostNumber(Long postNumber) {
		
		log.info("delete post by post number..................");
		return postMapper.deletePostByPostNumber(postNumber);
	}

}
