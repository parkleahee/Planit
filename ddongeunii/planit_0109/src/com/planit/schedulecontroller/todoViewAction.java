package com.planit.schedulecontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dto.TodoDTO;
import com.planit.dto.UserDTO;

public class todoViewAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		GoalDAO gdao = new GoalDAO();
		
		List<TodoDTO>todoList = gdao.getTodoList(userid);
		
		req.setAttribute("todoList", todoList);
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(false);
		transfer.setPath("/app/schedule/mainview.jsp");
		
		return transfer;
		
	} 

}
