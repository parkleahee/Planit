package com.planit.schedulecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.TimeDAO;
import com.planit.dto.TimeDTO;
import com.planit.dto.UserDTO;

public class TimeTestAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		TimeDTO tdto = new TimeDTO();
		TimeDAO tdao = new TimeDAO();
		
		HttpSession session = req.getSession();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		String title = req.getParameter("timetitle");
		String start = req.getParameter("timestart");
		String end = req.getParameter("timeend");
		System.out.println(title);
		System.out.println(start);
		System.out.println(end);
		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 mm월 dd일");
//		String strstart = simpleDateFormat.format(start);
//		System.out.println(strstart);
		
		String[]startt=start.split(" ");
		String[]endd=end.split(" ");
		System.out.println(startt[0]);
		System.out.println(endd[0]);
		tdto.setUserid(userid);
		tdto.setTimetitle(title);
		tdto.setTimestart(start);
		tdto.setTimeend(end);
		
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(false);
		if(tdao.addTime(tdto)) {
			System.out.println("추가 성공");
//			req.setAttribute("goal2", goal);
			transfer.setPath("/schedule/timelist.tc");
		}
		else {
			//localhost:9090/????
			transfer.setPath(req.getContextPath());
		}
		
		
		return transfer;
	}
}
