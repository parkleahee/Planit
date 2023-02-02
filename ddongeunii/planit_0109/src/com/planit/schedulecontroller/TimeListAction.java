package com.planit.schedulecontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dao.TimeDAO;
import com.planit.dto.GoalDTO;
import com.planit.dto.TimeDTO;
import com.planit.dto.TodoDTO;
import com.planit.dto.UserDTO;

public class TimeListAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		TimeDAO tdao = new TimeDAO();
		GoalDAO gdao = new GoalDAO();
		
		List<TimeDTO> timeList = tdao.getDetail(userid);
		List<TodoDTO>todoList = gdao.getTodoList(userid);
		List<GoalDTO>goalList = gdao.getGoalList(userid);
//		List<GoalDTO>sgoalList = gdao.setGoalList(goalnum);
		
		req.setAttribute("todoList", todoList);
		req.setAttribute("goalList", goalList);
		req.setAttribute("timeList", timeList);

		
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(false);
		transfer.setPath("/app/schedule/timetableview.jsp");
		
		return transfer;
		
	}

	
	
	
}
