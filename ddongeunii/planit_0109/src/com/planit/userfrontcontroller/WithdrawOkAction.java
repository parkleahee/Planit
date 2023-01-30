package com.planit.userfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.UserDAO;
import com.planit.dto.UserDTO;

public class WithdrawOkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		UserDAO udao = new UserDAO();
		String userid = req.getParameter("userid");
		String userpw = req.getParameter("userpw");
		System.out.println(userid);
		
		HttpSession session = req.getSession();

		ActionTo transfer = new ActionTo();
		transfer.setRedirect(true);
		
		if(udao.deleteInfo(userid)) {
			session.invalidate();
			System.out.println("탈퇴성공->db확인하세요");
			transfer.setPath(req.getContextPath()+"/app/user/Index.jsp");			
		}else {
			transfer.setPath(req.getContextPath());
		}
		return transfer;
	}
}
