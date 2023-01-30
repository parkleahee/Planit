package com.planit.userfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.ContactDAO;
import com.planit.dao.UserDAO;
import com.planit.dto.ContactDTO;

public class ContactGetOk implements Action{
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		int contactnum = Integer.parseInt(req.getParameter("contactnum"));
//		int page = Integer.parseInt(req.getParameter("page"));
//		String q = req.getParameter("q");
//		리스트를 돌려주기 위해서 넘겨주었던것 따로 데이터베이스 처리를 위한 것이 아니기 때문에 필요 없음 
		HttpSession session = req.getSession();
		
		ContactDAO cdao = new ContactDAO();
		ContactDTO cdto = cdao.getDetail(contactnum);
		// 현재 로그인 되어있는 유저아이디 
		String userid = (String)session.getAttribute("loginUser");
		//남이 들어갈때만 조회수가 증가할수 있도록
		
		
		req.setAttribute("cdto", cdto);
		//게시글의 정보만 가지고옴 
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(false);
		transfer.setPath(req.getContextPath()+"/app/user/getview.jsp");
		
		return transfer;
	}
}
