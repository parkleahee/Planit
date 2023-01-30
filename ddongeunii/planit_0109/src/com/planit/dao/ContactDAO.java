package com.planit.dao;

import org.apache.ibatis.session.SqlSession;

import com.planit.dto.ContactDTO;
import com.planit.mybatis.SqlMapConfig;

public class ContactDAO {
	SqlSession sqlsession;
	public ContactDAO() {
		sqlsession = SqlMapConfig.getFactory().openSession(true);
	}
	public boolean addcontact(ContactDTO cdto) {
		return sqlsession.insert("Contact.userContact",cdto)==1;
	}
	public int getLastNum(String userid) {
		return sqlsession.selectOne("Contact.getLastNum",userid);
	}

	public ContactDTO getDetail(int contactnum) {
		return sqlsession.selectOne("Contact.getDetail",contactnum);
	}
}
