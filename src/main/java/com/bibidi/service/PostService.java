package com.bibidi.service;

import java.util.List;

import com.bibidi.domain.PageVO;
import com.bibidi.domain.PostVO;

public interface PostService {

	public int registerPost(PostVO post);
	
	public PostVO getPostByPostNumber(Long postNumber);
	
	public List<PostVO> getPostsByForumSlug(String forumSlug, PageVO page);
	
	public int modifyPost(PostVO post);
	
	public int deletePostByPostNumber(Long postNumber);
}
