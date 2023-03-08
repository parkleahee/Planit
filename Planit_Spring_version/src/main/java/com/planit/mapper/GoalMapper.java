package com.planit.mapper;

import java.util.List;

import com.planit.domain.TodoDTO;

public interface GoalMapper {
	public boolean addTodo();
	public int getMaxTodoNum();
	public List<TodoDTO> getTodoList();
	public boolean deleteTodo();
	public boolean deleteGoal();
	public boolean getGoal();
}
