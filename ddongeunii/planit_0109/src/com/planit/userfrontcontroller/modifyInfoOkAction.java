package com.planit.userfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.UserDAO;
import com.planit.dto.UserDTO;

public class modifyInfoOkAction implements Action {
	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		UserDAO udao = new UserDAO();
		
		HttpSession session = req.getSession();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String userid = ((UserDTO) session.getAttribute("loginUser")).getUserid();
		String username = ((UserDTO) session.getAttribute("loginUser")).getUsername();
		String userdob = ((UserDTO) session.getAttribute("loginUser")).getUserdob();
		String useremail = req.getParameter("useremail");
	    String userpw = req.getParameter("password");
	    String userphone = req.getParameter("userphone");;
		String zipcode= req.getParameter("zipcode");
	    String addr = req.getParameter("addr");
	    String addrdetail = req.getParameter("addrdetail");
	    String addretc = req.getParameter("addretc");
	    System.out.println(userphone);
	    
	    UserDTO loginUser = new UserDTO();
	    
	    loginUser.setUserid(userid);
	    loginUser.setUserpw(userpw);
	    loginUser.setUsername(username);
	    loginUser.setUserdob(userdob);
	    loginUser.setUseremail(useremail);
	    loginUser.setUserphone(userphone);
	    loginUser.setZipcode(zipcode);
	    loginUser.setAddr(addr);
	    loginUser.setAddrdetail(addrdetail);
	    loginUser.setAddretc(addretc);
	    
	    ActionTo transfer = new ActionTo();
	    transfer.setRedirect(true);
	    
	    if(udao.modifyInfo(loginUser)) {
	    	session.setAttribute("loginUser",loginUser);
	    	System.out.println("수정 성공 !");
	    	transfer.setPath(req.getContextPath()+"/app/user/myinfoview.jsp");
	    }else {
	    	transfer.setPath(req.getContextPath());
	    }
		return transfer;
	}
}
