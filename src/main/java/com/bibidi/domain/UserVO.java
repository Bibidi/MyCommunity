package com.bibidi.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class UserVO {
	private Long userNumber;
	private String userId;
	private String userPassword;
	private String userEmail;
	
	private String userNickname;
	private String userPicture;
	private Long userActivityPoint;
	
	private Date dateUserCreated;
	private Date dateUserEdited;
	
	private Boolean userEnabled;
	
	private List<RoleVO> roleList;
}
