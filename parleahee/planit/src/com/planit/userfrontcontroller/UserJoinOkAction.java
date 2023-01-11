package com.planit.userfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.action.Location;
import com.planit.dao.UserDAO;
import com.planit.dto.UserDTO;

public class UserJoinOkAction implements Action{

		@Override
		public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
			ActionTo transfer = new ActionTo();
			UserDAO udao = new UserDAO();
			String userid = req.getParameter("userid");
		    String userpw = req.getParameter("userpw");
		    String username = req.getParameter("username");
		    String gender = req.getParameter("gender");
		    String userdob = req.getParameter("userdob");
		    String userphone = req.getParameter("userphone");
		    String useremail = req.getParameter("useremail");
			String zipcode= req.getParameter("zipcode");
		    String addr = req.getParameter("addr");
		    String addrdetail = req.getParameter("addrdetail");
		    String addretc = req.getParameter("addretc");
			UserDTO udto = new UserDTO(userid, userpw, username, gender, userdob, userphone, useremail, zipcode, addr, addrdetail, addretc);
		    System.out.println(udto);
			transfer.setRedirect(true);
			if(udao.userJoin(udto)) {
				transfer.setPath(new Location().fowardPath(req, "/user/loginuser.us.tc"));		
			}else {				
				transfer.setPath(req.getContextPath());
			}
			return transfer;
		}
}
