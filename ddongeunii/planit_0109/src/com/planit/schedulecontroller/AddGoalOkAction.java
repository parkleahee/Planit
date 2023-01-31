package com.planit.schedulecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dto.GoalDTO;
import com.planit.dto.UserDTO;

public class AddGoalOkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = new ActionTo();
		
		
		GoalDTO gdto = new GoalDTO();
		GoalDAO gdao = new GoalDAO();
		
		HttpSession session = req.getSession();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		String goal = req.getParameter("usergoal1");
		gdto.setUserid(userid);
		gdto.setGoal(goal);
		
//		gdto.setGoal(goal);
		transfer.setRedirect(false);
		
		GoalDTO gdto1 = (GoalDTO)session.getAttribute("goal1");
		GoalDTO gdto2 = (GoalDTO)session.getAttribute("goal2");
		
		int goalnum = 0;
		
		if(gdao.addGoal(gdto)) {
			if(gdto1 == null) {
				goalnum = gdao.getGoalnum(userid,goal);
				System.out.println(goalnum);
				gdto1 = new GoalDTO();
				gdto1.setUserid(userid);
				gdto1.setGoal(goal);
				gdto1.setGoalnum(goalnum);
				gdto1.setGoalcheck("t");
				session.setAttribute("goal1", gdto1);
				System.out.println("추가 성공");
			}else {
				goalnum = gdao.getGoalnum(userid,goal);
				System.out.println(goalnum);
				gdto2 = new GoalDTO();
				gdto2.setUserid(userid);
				gdto2.setGoal(goal);
				gdto2.setGoalnum(goalnum);
				gdto2.setGoalcheck("t");
				session.setAttribute("goal2", gdto2);
				
				System.out.println("추가 성공");
			}
			transfer.setPath("/schedule/todoview.tc");
		}
		else {
			//localhost:9090/????
			transfer.setPath(req.getContextPath());
		}
		return transfer;
	}

	}

