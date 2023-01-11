package com.planit.schedulecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.ActionTo;
import com.planit.totalfrontcontroller.FrontController;

public class ScheduleFrontController implements FrontController{

	@Override
	public ActionTo flow(HttpServletRequest req, HttpServletResponse resp, String command) {
		ActionTo transfer = null;
		switch (command) {
		case "/schedule/mainview.sc":
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/schedule/mainview.jsp");
			break;
		case "/schedule/writesetview.sc":
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/schedule/writesetview.jsp");
			break;
		case "/schedule/writeview.sc":
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/schedule/writeview.jsp");
		default:
			break;
		}
		return transfer;
	}
	
}
