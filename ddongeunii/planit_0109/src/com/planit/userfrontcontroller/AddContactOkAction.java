package com.planit.userfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.ContactDAO;
import com.planit.dao.UserDAO;
import com.planit.dto.ContactDTO;
import com.planit.dto.UserDTO;

public class AddContactOkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = new ActionTo();
		ContactDAO cdao = new ContactDAO();
		ContactDTO cdto = new ContactDTO();
		
		HttpSession session = req.getSession();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String userid = ((UserDTO) session.getAttribute("loginUser")).getUserid();
		String contacttitle = req.getParameter("u_title");
		String contactcontents = req.getParameter("u_content");
		cdto.setUserid(userid);
		cdto.setContacttitle(contacttitle);
		cdto.setContactcontents(contactcontents);
		System.out.println(contacttitle);
		System.out.println(contactcontents);
//		gdto.setGoal(goal);
		transfer.setRedirect(false);
		int contactnum = 0;
		
		if (cdao.addcontact(cdto)) {
			System.out.println("추가 성공");
			contactnum = cdao.getLastNum(userid);
			req.setAttribute("userid", userid);
//			req.setAttribute("goal2", goal);
			transfer.setPath("/user/contgetview.tc?contactnum="+contactnum);
		} else {
			// localhost:9090/????
			transfer.setPath(req.getContextPath());
		}
		return transfer;
	}
}
