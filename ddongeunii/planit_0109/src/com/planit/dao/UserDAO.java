package com.planit.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.planit.dto.ContactDTO;
import com.planit.dto.TimeDTO;
import com.planit.dto.UserDTO;
import com.planit.mybatis.SqlMapConfig;

public class UserDAO {
	SqlSession sqlSession;
	public UserDAO() {
		sqlSession = SqlMapConfig.getFactory().openSession(true);}
	
	public UserDTO userLogin(String userid, String userpw) {
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		datas.put("userpw", userpw);
		return sqlSession.selectOne("User.userLogin",datas);
	}

	public boolean checkId(String userid) {
		// TODO Auto-generated method stub
		return (Integer)sqlSession.selectOne("User.checkId",userid) != 1;
	}

	public boolean userJoin(UserDTO udto) {
		return sqlSession.insert("User.userJoin",udto)==1;
		
	}

	
	public boolean modifyInfo(UserDTO loginUser) {
		return sqlSession.update("User.modifyInfo",loginUser)==1;
//		return true;
	}

	public boolean deleteInfo(String userid) {
		return sqlSession.delete("User.deleteInfo",userid)==1;
		//return true;
	}
	
}
