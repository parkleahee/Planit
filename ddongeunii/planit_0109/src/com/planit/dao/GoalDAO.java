package com.planit.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.planit.dto.GoalDTO;
import com.planit.dto.TodoDTO;
import com.planit.mybatis.SqlMapConfig;

public class GoalDAO {

	SqlSession sqlsession;
	public GoalDAO() {
		sqlsession = SqlMapConfig.getFactory().openSession(true);
	}
	public boolean addGoal(GoalDTO gdto) {
		return sqlsession.insert("Goal.addGoal",gdto) == 1;
	}
	
	public List<GoalDTO> goalDetail(String userid) {
		return sqlsession.selectList("Goal.goalDetail",userid);
	}
	
	public boolean updateCnt(int goalnum) {
		return sqlsession.update("Goal.updateCnt",goalnum)==1;
	}
	public boolean getGoal(GoalDTO goal) {
		return sqlsession.insert("Goal.getGoal",goal) ==1;
	}
	
	public int getGoalnum(String userid, String goal) {
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		datas.put("goal", goal);
		return sqlsession.selectOne("Goal.getGoalnum",datas);
	}
	
	public boolean addTodo(TodoDTO tdto) {
		return sqlsession.insert("Goal.addTodo",tdto) == 1;
	}
	
	public int goalCnt(int goalnum) {
		return sqlsession.selectOne("Goal.goalCnt",goalnum);
	}
	
	public boolean importTodo(String userid,int todonum) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("userid", userid);
		datas.put("todonum", todonum);
		return sqlsession.update("Goal.importTodo",datas)==1;
	}
	public boolean deleteTodo(String userid,int todonum) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("userid", userid);
		datas.put("todonum", todonum);
		return sqlsession.delete("Goal.deleteTodo",datas)==1;
	}
	public int getTodonum(String userid, String todocontents) {
		HashMap<String, String> datas = new HashMap<String, String>();
		datas.put("userid", userid);
		datas.put("todocontents",todocontents);
		return sqlsession.selectOne("Goal.getTodonum",datas);
	}
	
	public List<TodoDTO> getTodoList(String userid) {
		return sqlsession.selectList("Goal.getTodoList",userid);
	}
	public List<GoalDTO> getGoalList(String userid) {
		return sqlsession.selectList("Goal.getGoalList",userid);
	}
	public boolean deleteGoal(String userid, int goalnum) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("userid", userid);
		datas.put("goalnum", goalnum);
		return sqlsession.delete("Goal.deleteGoal",datas)==1;
	}
	public boolean checkTodo(String userid, int todonum) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("userid", userid);
		datas.put("todonum", todonum);
		return sqlsession.update("Goal.checkTodo",datas)==1;
	}
	public boolean deleteTime(String userid, String timetitle) {
		HashMap<String, Object> datas = new HashMap<String, Object>();
		datas.put("userid", userid);
		datas.put("timetitle", timetitle);
		return sqlsession.delete("Goal.deleteTime",datas)==1;
	}

}
