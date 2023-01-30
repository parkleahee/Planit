package com.planit.schedulecontroller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dto.UserDTO;

public class timetDeleteAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String timetitle = req.getParameter("timetitle");
		System.out.println(timetitle);
		
		GoalDAO gdao = new GoalDAO();
		HttpSession session = req.getSession();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		PrintWriter out = resp.getWriter();

		if (gdao.deleteTime(userid,timetitle)) {
			out.write("O");
		} else {
			out.write("X");
		}
		out.close();

		return null;
	}
}
