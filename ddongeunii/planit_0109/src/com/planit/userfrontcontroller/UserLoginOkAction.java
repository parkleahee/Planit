package com.planit.userfrontcontroller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dao.UserDAO;
import com.planit.dto.GoalDTO;
import com.planit.dto.UserDTO;

public class UserLoginOkAction implements Action{

	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = new ActionTo();
		UserDAO udao = new UserDAO();
		GoalDAO gdao = new GoalDAO();
		HttpSession session = req.getSession();
		System.out.println("loginex");
		String userid = req.getParameter("userid");
		String userpw = req.getParameter("userpw");
		System.out.println("userid"+userid);
		System.out.println("userpw"+userpw);
		UserDTO loginUser = udao.userLogin(userid,userpw);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		transfer.setRedirect(false);
		System.out.println(loginUser);
		if (loginUser != null) {
			System.out.println("로그인 성공");
			session.setAttribute("loginUser", loginUser);
			// 가장 최근의 두개의 목표 -> 30일이 안된 목표인지 
			//  로그인하기전에 시작일로 부터 30일이 지났는지 안지났는지 확인해야함 
			
			gdao.goalNow(userid).forEach((value)->{
				int goalnum = value.getGoalnum();
				int goalcnt = gdao.goalCnt(goalnum);
				//db에 오늘 날짜가 있는지 없는지 확인
				if(gdao.getCheckGoal(goalnum)==1) {
					value.setGoalcheck("f");
					value.setGoalcnt(goalcnt);
				}else if(gdao.getCheckGoal(goalnum)==0) {
					value.setGoalcheck("t");
				}
				if (session.getAttribute("goal1")==null) {
					session.setAttribute("goal1", value);					
				}else {
					session.setAttribute("goal2", value);					
				}
			}
					);
			transfer.setPath("/schedule/todoview.tc");
		}else {
			transfer.setPath("/app/user/loginview.jsp");
			System.out.println("로그인 실패");
		}
		return transfer;
	}

	
}
