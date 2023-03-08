package com.planit.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.planit.domain.ContactDTO;
import com.planit.domain.GoalDTO;
import com.planit.domain.UserDTO;


public interface UserService {
	
	public UserDTO userLogin(String userid, String userpw,HttpServletRequest req) throws Exception;

	public void logout(HttpServletRequest req);

	public List<GoalDTO> goalList(HttpServletRequest req);

	public boolean withdrawOk(String userid, String userpw,HttpServletRequest req);

	public boolean deleteGoal(int goalnum);

	public boolean modifyInfo(UserDTO loginUser, HttpServletRequest req);

	public boolean addcontact(String contacttitle, String contactcontents, HttpServletRequest req);
	
	public boolean checkid(String userid);

	public boolean joinok(UserDTO udto);

	public String findid(String phone);

	public UserDTO findpw(String userid);
	
	public List<UserDTO> getFriendList(String keyword, HttpServletRequest req);
	
}
