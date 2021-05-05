package com.bibidi.mapper;

import com.bibidi.domain.RoleVO;

public interface RoleMapper {

	public int insertRole(RoleVO role);
	
	public RoleVO readRoleByRoleNumber(Long roleNumber);
	
	public int updateRole(RoleVO role);
	
	public int deleteRole(Long roleNumber);
}
