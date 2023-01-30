package com.planit.schedulecontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.TimeDAO;
import com.planit.dto.TimeDTO;
import com.planit.dto.UserDTO;

public class timetlistAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		TimeDAO tdao = new TimeDAO();
		
		List<TimeDTO> timeList = tdao.getDetail(userid);
		
		req.setAttribute("timeList", timeList);

		
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(false);
		transfer.setPath("/app/schedule/timetableview.jsp");
		
		return transfer;
		
	}

	
	
	
}
