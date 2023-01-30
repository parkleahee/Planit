package com.planit.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.planit.dto.TimeDTO;
import com.planit.mybatis.SqlMapConfig;

public class TimeDAO {
	SqlSession sqlsession;
	public TimeDAO() {
		sqlsession = SqlMapConfig.getFactory().openSession(true);
	}
	public boolean addTime(TimeDTO tdto) {
		return sqlsession.insert("Time.addTime",tdto)==1;
	}
	public List<TimeDTO> getDetail(String userid) {
		return sqlsession.selectList("Time.getDetail",userid);
	}
}
