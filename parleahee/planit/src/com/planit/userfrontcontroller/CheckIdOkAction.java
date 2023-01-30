package com.planit.userfrontcontroller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.UserDAO;

public class CheckIdOkAction implements Action{

	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String userid = req.getParameter("userid");
		UserDAO udao = new UserDAO();
		PrintWriter out = resp.getWriter();
		
		if(udao.checkId(userid)) {
			out.write("O");
		}
		else {
			out.write("X");
		}
		out.close();
		
		return null;
	}
	
}
