package com.bibidi.service;

import com.bibidi.domain.ForumVO;

public interface ForumService {

	public int registerForum(ForumVO forum);
	
	public ForumVO getForumByForumNumber(Long forumNumber);
	
	public int modifyForum(ForumVO forum);
	
	public int deleteForumByForumNumber(Long forumNumber);
}
