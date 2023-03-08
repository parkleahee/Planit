package com.planit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.planit.domain.TimeDTO;

import lombok.Setter;

@Repository
public class TimeDAOImpl implements TimeDAO {
	@Setter(onMethod_ = @Autowired)
    private SqlSession sqlSession;
    
    private static String namespace = "com.planit.mapper.TimeMapper.";

    @Override
    public List<TimeDTO> getDetail(String userid) {
    	return sqlSession.selectList(namespace+"getDetail",userid);
    }

	@Override
	public boolean addTime(TimeDTO tdto) {
		return sqlSession.insert(namespace+"addTime",tdto)==1;
	}

	@Override
	public boolean deleteTime(int timenum) {
		return sqlSession.delete(namespace+"deleteTime",timenum)==1;
	}
    
}
