package com.planit.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.domain.GoalDTO;
import com.planit.domain.ScheduleDTO;
import com.planit.domain.SetScBarDTO;
import com.planit.domain.TimeDTO;
import com.planit.domain.TodoDTO;

public interface ScheduleService {
	
	public boolean regist(TodoDTO todo);

	public int getMaxTodoNum(String userid);

	public List<TodoDTO> getList(String userid);

	public boolean deleteTodo(int todonum);

	public boolean importTodo(int todonum);

	public boolean checkTodo(int todonum);

	public boolean addgoal(String goal, HttpServletRequest req, HttpServletResponse resp);

	public List<TimeDTO> timeList(HttpServletRequest req);

	public boolean timeAdd(TimeDTO tdto,HttpServletRequest req);

	public boolean deleteTime(int timenum);

	public boolean cntGoal(String goal, int goalnum, HttpServletRequest req);
	
	public List<ScheduleDTO> gettogetherList(HttpServletRequest req);
	
	 public List<ScheduleDTO> getScheduleList(String newStart, HttpServletRequest req) throws Exception;

//	   public String getNewStart(String start);
//
//	   public String getNewEnd(String end);

	   public boolean insertCate(HttpServletRequest req, boolean modifycheck);

	   public List<SetScBarDTO> getScBarList(HttpServletRequest req);

	   public HashMap<String, Object> getScBar(int scnum);

	   public HashMap<String, Object> getMonga(int scnum);

	   public boolean scheduleremove(int scnum);
	   
	   public HashMap<String, Object> mainOk(HttpServletRequest req);

	   public String getScId(int scnum);

}
