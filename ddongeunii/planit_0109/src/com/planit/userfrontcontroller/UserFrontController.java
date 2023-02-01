package com.planit.userfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.ActionTo;
import com.planit.action.ExceptionPrint;
import com.planit.schedulecontroller.CntGoalViewAction;
import com.planit.totalfrontcontroller.FrontController;

public class UserFrontController implements FrontController{

	@Override
	public ActionTo flow(HttpServletRequest req, HttpServletResponse resp, String command) {
		ActionTo transfer = null;
		ExceptionPrint ep = new ExceptionPrint();
		System.out.println("userfront" +command);
		switch (command) {
		case "/user/loginuser":
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/user/loginview.jsp");
			break;
		case "/user/joinuser" :
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/user/joinview.jsp");
			break;
		case "/user/loginok" : 
			try {
				transfer = new UserLoginOkAction().execute(req,resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;
		case "/user/checkidok" :
			try {
				new CheckIdOkAction().execute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ep.exceptionPrint(e, command);
			}
			break;
		case "/user/joinok" :
			try {
				transfer = new UserJoinOkAction().execute(req, resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;
		
		case "/user/usergoal" :
			try {
				transfer = new UserGoalListOk().execute(req,resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;
		case "/user/goallist" :
			try {
				transfer = new UserGoalListOk().execute(req,resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;
		case "/user/deletegoal" :
			try {
				transfer = new UserGoalDeleteOkAction().execute(req,resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;
		case "/user/usermyinfo" :
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/user/myinfoview.jsp");
			break;
		case "/user/usermyinfook":
			try {
				transfer = new modifyInfoOkAction().execute(req,resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;
		case "/user/usercontact" :
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/user/contactview.jsp");
			break;
		case "/user/usercontactok":
			try {
				transfer = new AddContactOkAction().execute(req,resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;	
		case "/user/contgetview":
			try {
				transfer = new ContactGetOk().execute(req,resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;
		case "/user/userwithdraw" :
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/user/withdrawview.jsp");
			break;
		case "/user/userwithdrawok" :
			try {
				transfer = new WithdrawOkAction().execute(req,resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;	
		case "/user/userlogout":
			req.getSession().removeAttribute("loginUser");
			transfer = new ActionTo();
			transfer.setPath("/");
			transfer.setRedirect(false);
			break;
		default:
			break;
		}
		
		return transfer;
	}

}
