package com.bibidi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibidi.domain.ForumVO;
import com.bibidi.domain.RoleVO;
import com.bibidi.mapper.ForumMapper;
import com.bibidi.mapper.RoleMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ForumServiceImpl implements ForumService {
	
	@Setter(onMethod_ = @Autowired)
	private ForumMapper forumMapper;
	
	@Setter(onMethod_ = @Autowired)
	private RoleMapper roleMapper;
	
	@Override
	public int registerForum(ForumVO forum) {

		log.info("register forum.........");
		
		int result = forumMapper.insertForum(forum);
		
		// 해당 게시판과 관련된 권한 추가
		if (result > 0) {
			RoleVO role = new RoleVO();
			role.setName("ROLE_ADMIN_" + forum.getSlug().toUpperCase());
			result *= roleMapper.insertRole(role);
			
			role.setName("ROLE_MANAGER_" + forum.getSlug().toUpperCase());
			result *= roleMapper.insertRole(role);
		}
		
		return result;
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
		
		int result = forumMapper.deleteForumByForumNumber(forumNumber);
		
		// 해당 게시판과 관련된 권한 삭제
		if (result > 0) {
			String slug = forumMapper
					.readForumByForumNumber(forumNumber)
					.getSlug()
					.toUpperCase();
			
			result *= roleMapper.deleteRoleByRoleName("ROLE_ADMIN_" + slug);
			result *= roleMapper.deleteRoleByRoleName("ROLE_MANAGER_" + slug);
		}
		return result;
	}
	
}
