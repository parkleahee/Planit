package com.planit.dao;

import java.util.List;

import com.planit.domain.GoalDTO;
import com.planit.domain.TodoDTO;

public interface GoalDAO {

	public boolean addTodo(TodoDTO tdto);

	public int getMaxTodoNum(String userid);

	public List<TodoDTO> getTodoList(String userid);

	public boolean deleteTodo(int todonum);

	public boolean importTodo(int todonum);

	public boolean checkTodo(int todonum);

	public boolean addGoal(GoalDTO gdto);

	public int getGoalnum(String userid, String goal);

	public List<GoalDTO> goalNow(String userid);

	public int goalCnt(int goalnum);

	public int getCheckGoal(int goalnum);

	public List<GoalDTO> getGoalList(String userid);

	public boolean deleteGoal(int goalnum);

	public boolean getGoal(GoalDTO gdto);

}
