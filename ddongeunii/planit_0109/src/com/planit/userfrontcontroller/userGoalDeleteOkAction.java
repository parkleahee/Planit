package com.planit.userfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dto.UserDTO;

public class userGoalDeleteOkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int goalnum = Integer.parseInt(req.getParameter("goalnum"));
		
		GoalDAO gdao = new GoalDAO();
		HttpSession session = req.getSession();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(true);
		if(gdao.deleteGoal(userid,goalnum)) {
			transfer.setPath(req.getContextPath()+"/user/usergoal.tc");
		}
		else {
			transfer.setPath(req.getContextPath()+"/user/usergoal.tc?goalnum="+goalnum);
		}
		return transfer;
	}
}
