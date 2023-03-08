package com.planit.dao;

import java.util.List;

import com.planit.domain.ContactDTO;
import com.planit.domain.UserDTO;
//데이터 확인
public interface UserDAO {
	
	public UserDTO userLogin(String userid, String userpw);

	public boolean addcontact(ContactDTO cdto);

	public boolean deleteInfo(String userid, String userpw);

	public boolean modifyInfo(UserDTO loginUser);
	
	public boolean checkid(String userid);

	public boolean joinok(UserDTO udto);

	public String findid(String phone);

	public UserDTO findpw(String userid);
	
	public List<UserDTO> getFriendList(String keyword, String loginUser);
		
}
