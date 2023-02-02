package com.planit.userfrontcontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.GoalDAO;
import com.planit.dto.GoalDTO;
import com.planit.dto.UserDTO;

public class UserGoalListOk implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		GoalDAO gdao = new GoalDAO();
		
		HttpSession session = req.getSession();
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		List<GoalDTO>goalList = gdao.getGoalList(userid);
		List<GoalDTO>goalCntList = gdao.getGoalCntList(userid);
		
		req.setAttribute("goalList", goalList);
		req.setAttribute("goalCntList", goalCntList);
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(false);
		transfer.setPath("/app/user/goalview.jsp");
		
		return transfer;
		
	} 

}
