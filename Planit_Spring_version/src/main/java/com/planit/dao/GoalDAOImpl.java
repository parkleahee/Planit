package com.planit.dao;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.planit.domain.GoalDTO;
import com.planit.domain.TodoDTO;

import lombok.Setter;

@Repository
public class GoalDAOImpl implements GoalDAO  {
	 @Setter(onMethod_ = @Autowired)
	    private SqlSession sqlSession;
	    
	    private static String namespace = "com.planit.mapper.GoalMapper.";
	   
	   @Override
	   public boolean addTodo(TodoDTO tdto) {
			return sqlSession.insert(namespace+"addTodo",tdto) == 1;
		}
	   
	   @Override
	   public int getMaxTodoNum(String userid) {
		   return sqlSession.selectOne(namespace+"getMaxTodoNum",userid);
	   }

	@Override
	public List<TodoDTO> getTodoList(String userid) {
		return sqlSession.selectList(namespace+"getTodoList",userid);
	}

	@Override
	public boolean deleteTodo(int todonum) {
		return sqlSession.delete(namespace+"deleteTodo",todonum)==1;
	}

	@Override
	public boolean importTodo(int todonum) {
		return sqlSession.update(namespace+"importTodo",todonum)==1;
	}

	@Override
	public boolean checkTodo(int todonum) {
		return sqlSession.update(namespace+"checkTodo",todonum)==1;
	}

	@Override
	public boolean addGoal(GoalDTO gdto) {
		return sqlSession.insert(namespace+"addGoal",gdto) == 1;
	}

	@Override
	public int getGoalnum(String userid, String goal) {
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		datas.put("goal", goal);
		return sqlSession.selectOne(namespace+"getGoalnum",datas);
	}

	@Override
	public List<GoalDTO> goalNow(String userid) {
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		System.out.println("아이디:"+userid);
		return sqlSession.selectList(namespace+"goalNow",datas);
	}

	@Override
	public int goalCnt(int goalnum) {
		return sqlSession.selectOne(namespace+"goalCnt",goalnum);
	}

	@Override
	public int getCheckGoal(int goalnum) {
		return sqlSession.selectOne(namespace+"getCheckGoal",goalnum);
	}

	@Override
	public List<GoalDTO> getGoalList(String userid) {
		return sqlSession.selectList(namespace+"getGoalList",userid);
	}

	@Override
	public boolean deleteGoal(int goalnum) {
		return sqlSession.delete(namespace+"deleteGoal",goalnum)==1;
	}

	@Override
	public boolean getGoal(GoalDTO gdto) {
		return sqlSession.insert(namespace+"getGoal",gdto) ==1;
	}
}
