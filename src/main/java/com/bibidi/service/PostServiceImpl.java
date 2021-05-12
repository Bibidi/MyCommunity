package com.bibidi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibidi.domain.PageVO;
import com.bibidi.domain.PostVO;
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
			result = userMapper.increaseUserActivityScoreByUserNickname(post.getWriter(), SCORE);
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
	public List<PostVO> getPostsByForumSlug(String forumSlug, PageVO page) {
		
		log.info("get posts by forum slug..............");
		Long forumNumber = ForumMapper.readForumByForumSlug(forumSlug).getNumber();
		
		Long to = page.getNumber() * page.getSize();
		Long from = to - page.getSize() + 1;
		
		return postMapper.readPostsByForumNumber(forumNumber, from, to);
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
