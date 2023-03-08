package com.planit.mapper;

import java.util.List;

import com.planit.domain.UserDTO;

public interface UserMapper {
	public UserDTO userLogin();
	public boolean addcontact();
	public boolean deleteInfo();
	public boolean modifyInfo();
	public int checkId();
	public String findid();
	public String findpw();
	public List<UserDTO> getFriendListWithKey();
	
	}
