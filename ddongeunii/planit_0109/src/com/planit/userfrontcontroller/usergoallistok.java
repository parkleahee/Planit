package com.planit.userfrontcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dto.GoalDTO;
import com.planit.dto.TodoDTO;
import com.planit.dto.UserDTO;

public class usergoallistok implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		GoalDAO gdao = new GoalDAO();
		
		List<GoalDTO>goalList = gdao.getGoalList(userid);
		List<GoalDTO>sgoalList = gdao.setGoalList(userid);
		
		req.setAttribute("goalList", goalList);
		req.setAttribute("sgoalList", sgoalList);
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(false);
		transfer.setPath("/app/user/goalview.jsp");
		
		return transfer;
		
	} 

}
