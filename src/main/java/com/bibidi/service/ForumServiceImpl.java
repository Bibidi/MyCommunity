package com.bibidi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibidi.domain.ForumVO;
import com.bibidi.mapper.ForumMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ForumServiceImpl implements ForumService {
	
	@Setter(onMethod_ = @Autowired)
	ForumMapper forumMapper;
	
	@Override
	public int registerForum(ForumVO forum) {

		log.info("register forum.........");
		return forumMapper.insertForum(forum);
	}

	@Override
	public ForumVO getForumByForumNumber(Long forumNumber) {

		log.info("get forum by forum number.........");
		return forumMapper.readForumByForumNumber(forumNumber);
	}

	@Override
	public int modifyForum(ForumVO forum) {

		log.info("modify forum...............");
		return forumMapper.updateForum(forum);
	}

	@Override
	public int deleteForumByForumNumber(Long forumNumber) {

		log.info("delete forum by forum number.............");
		return forumMapper.deleteForumByForumNumber(forumNumber);
	}
	
}
