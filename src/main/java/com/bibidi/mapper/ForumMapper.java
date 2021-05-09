package com.bibidi.mapper;

import com.bibidi.domain.ForumVO;

public interface ForumMapper {

	public int insertForum(ForumVO forum);
	
	public ForumVO readForumByForumNumber(Long forumNumber);
	
	public ForumVO readForumByForumSlug(String forumSlug);
	
	public int updateForum(ForumVO forum);
	
	public int deleteForumByForumNumber(Long forumNumber);
}
