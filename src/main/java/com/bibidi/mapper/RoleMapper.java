package com.bibidi.mapper;

import com.bibidi.domain.RoleVO;

public interface RoleMapper {

	public int insertRole(RoleVO role);
	
	public RoleVO readRoleByRoleName(String roleName);
	
	public int deleteRoleByRoleName(String roleName);
}
