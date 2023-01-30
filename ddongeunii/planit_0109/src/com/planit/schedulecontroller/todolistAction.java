package com.planit.schedulecontroller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dto.TodoDTO;
import com.planit.dto.UserDTO;

public class todolistAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = new ActionTo();
		
		HttpSession session = req.getSession();

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");

		String todocontents = req.getParameter("todo");
		String userid = ((UserDTO) session.getAttribute("loginUser")).getUserid();

		GoalDAO gdao = new GoalDAO();
		TodoDTO tdto = new TodoDTO();
		
		tdto.setUserid(userid);
		tdto.setTodocontents(todocontents);
		
		transfer.setRedirect(false);
		
		
		if (gdao.addTodo(tdto)) {
				System.out.println("추가 성공");
				transfer.setPath("/schedule/todoview.tc");
			} else {
				transfer.setPath(req.getContextPath());
			}
			return transfer;
		}
}
