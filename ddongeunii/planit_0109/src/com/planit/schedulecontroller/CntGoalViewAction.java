package com.planit.schedulecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dto.GoalDTO;


public class CntGoalViewAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String goal = req.getParameter("goal");
		
		HttpSession session = req.getSession();
		
//		GoalDTO gdto = new GoalDTO();
		GoalDAO gdao = new GoalDAO();
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(true);
		//if문 꺼낸 goal.equals -> 
		
		if (goal.equals("goal1")) {
			System.out.println("들어옴");
			int goalnum= Integer.parseInt(req.getParameter("goalnum"));
			((GoalDTO)session.getAttribute("goal1")).setGoalnum(goalnum);
			//날짜 들어가있는지 확인해서 
			System.out.println(gdao.getCheckGoal(goalnum));
			if(gdao.getCheckGoal(goalnum)==0) {
				if(gdao.getGoal((GoalDTO)session.getAttribute("goal1"))) {
					System.out.println("들어옴2");
					int goalcnt = gdao.goalCnt(goalnum);
					System.out.println(goalcnt);
					GoalDTO gdto1 = (GoalDTO)session.getAttribute("goal1");	
					gdto1.setGoalcheck("f");
					gdto1.setGoalcnt(goalcnt);
					session.setAttribute("goal1", gdto1);
					transfer.setPath(req.getContextPath()+"/schedule/todoview.tc");
					}
			}else if(gdao.getCheckGoal(goalnum)==1) {
				transfer.setPath(req.getContextPath()+"/schedule/todoview.tc");
			}
		
		}else if(goal.equals("goal2")){
			System.out.println("2번 들어옴");
			int goalnum= Integer.parseInt(req.getParameter("goalnum"));
			((GoalDTO)session.getAttribute("goal2")).setGoalnum(goalnum);
		if(gdao.getGoal((GoalDTO)session.getAttribute("goal2"))) {
			System.out.println("2번 들어옴2");
			int goalcnt = gdao.goalCnt(goalnum);
			System.out.println(goalcnt);
			GoalDTO gdto2 = (GoalDTO)session.getAttribute("goal2");	
			gdto2.setGoalcheck("f");
			gdto2.setGoalcnt(goalcnt);
			session.setAttribute("goal2", gdto2);
			transfer.setPath("/schedule/todoview.tc");
			}
		}
		else {
				//localhost:9090/????
				transfer.setPath(req.getContextPath());
			}

		return transfer;
	}
	
	
	
}
