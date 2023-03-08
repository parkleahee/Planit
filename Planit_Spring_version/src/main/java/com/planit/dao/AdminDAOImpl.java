package com.planit.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.planit.domain.ContactDTO;

import lombok.Setter;

@Repository
public class AdminDAOImpl implements AdminDAO {
	@Setter(onMethod_ = @Autowired)
    private SqlSession sqlSession;
    
    private static String namespace = "com.planit.mapper.AdminMapper.";
	
	@Override
	public int getContactCnt(String keyword) {
		if (keyword == null) {
			return sqlSession.selectOne(namespace+"ContactCnt");
		} else {
			return sqlSession.selectOne(namespace+"getContactCntWithKey", keyword);
		}
	}

	@Override
	public List<ContactDTO> getContactList(int startRow, int pageSize, String keyword) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		List<ContactDTO> result = null;
		
		datas.put("startRow", startRow);
		datas.put("pageSize", pageSize);
		
		if(keyword == null) {
			result = sqlSession.selectList(namespace+"getContactList",datas);
		}
		else {
			datas.put("keyword", keyword);
			result = sqlSession.selectList(namespace+"getContactListWithKey",datas);
		}
		
		return result;
	}

	@Override
	public ContactDTO getDetail(int contactnum) {
		return sqlSession.selectOne(namespace+"getDetail",contactnum);
	}

}
