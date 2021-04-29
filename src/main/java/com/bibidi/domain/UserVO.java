package com.bibidi.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserVO {
	private Long number;
	private String id;
	private String password;
	private String userEmail;
	
	private String nickname;
	private String picture;
	private Long activityScore;
	
	private Date dateRegistered;
	private Date dateModified;
	
	private Boolean enabled;
	
	private List<RoleVO> roles;
}
