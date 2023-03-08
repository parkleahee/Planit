package com.planit.service;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.planit.dao.UserDAO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserServiceTest {
	@Setter(onMethod_ = @Autowired)
	private UserDAO userdao;
	
	@Setter(onMethod_ = @Autowired)
	private SqlSession sqlsession;
	
	@Test
	public void loginTest() throws Exception {
		log.info("result : "+userdao.userLogin("apple", "1111"));
		log.info(sqlsession);
	}
}
