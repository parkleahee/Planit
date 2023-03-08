package com.planit.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.planit.domain.ContactDTO;
import com.planit.domain.UserDTO;

import lombok.Setter;

@Repository
public class UserDAOImpl implements UserDAO {
	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlSession;

	private static String namespace = "com.planit.mapper.UserMapper.";

	@Override
	public UserDTO userLogin(String userid, String userpw) {
		System.out.println("에스큐sql : " + sqlSession);
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		datas.put("userpw", userpw);
		return sqlSession.selectOne(namespace + "userLogin", datas);
	}

	@Override
	public boolean checkid(String userid) {
		// TODO Auto-generated method stub
		return (Integer) sqlSession.selectOne(namespace + "checkId", userid) != 1;
	}

	@Override
	public boolean joinok(UserDTO udto) {
		System.out.println("join_userdto : " + udto);
		return sqlSession.insert(namespace + "userJoin", udto) == 1;
	}

	@Override
	public String findid(String phone) {
		return sqlSession.selectOne(namespace + "findid", phone);
	}

	@Override
	public UserDTO findpw(String userid) {
		return sqlSession.selectOne(namespace + "findpw", userid);
	}

	@Override
	public boolean addcontact(ContactDTO cdto) {
		return sqlSession.insert(namespace + "userContact", cdto) == 1;
	}

	@Override
	public boolean deleteInfo(String userid, String userpw) {
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		datas.put("userpw", userpw);
		return sqlSession.delete(namespace + "deleteInfo", datas) == 1;
	}

	@Override
	public boolean modifyInfo(UserDTO loginUser) {
		return sqlSession.update(namespace + "modifyInfo", loginUser) == 1;
	}
	
	@Override
	public List<UserDTO> getFriendList(String keyword, String loginUser) {
	   HashMap<String, Object> datas = new HashMap<String, Object>();
	   List<UserDTO> result = null;
	   
	   datas.put("loginUser", loginUser);
	   
	   if(keyword == loginUser) {
	      result = sqlSession.selectList(namespace + "getFriendList",datas);
	   }
	   else {
	      datas.put("keyword", keyword);
	      result = sqlSession.selectList(namespace + "getFriendListWithKey",datas);
	   }
	   
	   return result;
	}

}