package com.bibidi.service;

import java.util.List;

import com.bibidi.domain.PostVO;
import com.bibidi.domain.SearchCriteria;

public interface PostService {

	public int registerPost(PostVO post);
	
	public PostVO getPostByPostNumber(Long postNumber);
	
	public List<PostVO> getPostsByForumSlug(String forumSlug, SearchCriteria searchCriteria);
	
	public long getTotalPostsCountByForumNumber(Long forumNumber);
	
	public int modifyPost(PostVO post);
	
	public int deletePostByPostNumber(Long postNumber);
}
