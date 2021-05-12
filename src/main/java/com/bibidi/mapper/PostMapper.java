package com.bibidi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bibidi.domain.PageVO;
import com.bibidi.domain.PostVO;

public interface PostMapper {
	
	public int insertPost(PostVO post);
	
	public PostVO readPostByPostNumber(Long postNumber);
	
	public List<PostVO> readPostsByForumNumber(@Param("forumNumber") Long forumNumber, @Param("from") Long from, @Param("to") Long to);
	
	public int updatePost(PostVO post);
	
	public int increasePostViewsByPostNumber(Long postNumber);
	
	public int deletePostByPostNumber(Long postNumber);
}
