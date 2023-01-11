package com.planit.totalfrontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.ActionTo;
import com.planit.chatfrontcontroller.ChatFrontController;
import com.planit.schedulecontroller.ScheduleFrontController;
import com.planit.userfrontcontroller.UserFrontController;




public class TotalFrontController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// --> total ->  *.* -> sevlet
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String path = req.getContextPath();
		String command = uri.substring(path.length());
		String subject = command.split("\\.")[1];
		command = command.split("\\.")[0]+"."+command.split("\\.")[1];
		ActionTo transfer = null;
		System.out.println(command);
		switch (subject) {
		case "us":
				transfer = new UserFrontController().flow(req, resp, command);
			break;
		case "sc":
				transfer = new ScheduleFrontController().flow(req, resp, command);
			break;
		case "chat":
				transfer = new ChatFrontController().flow(req, resp, command);
		default:
			break;
		}
		
		if(transfer != null) {
			if(transfer.isRedirect()) {
				//Redirect 방식
				resp.sendRedirect(transfer.getPath());
			}
			else {
				//Forward 방식
				System.out.println(transfer.getPath());
				req.getRequestDispatcher(transfer.getPath()).forward(req, resp);
			}
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}
	
}
