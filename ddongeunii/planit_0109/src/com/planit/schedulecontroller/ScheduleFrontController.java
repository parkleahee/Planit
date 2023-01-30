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
		case "/schedule/mainview":
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/schedule/mainview.jsp");
			break;
		case "/schedule/calDate":
			try {
				transfer = new WriteSetViewAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/timetest":
			try {
				transfer = new timetTestAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/timelist":
			try {
				transfer = new timetlistAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/timedelete":
			try {
				transfer = new timetDeleteAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/addgoalokaction" :
			try {
				transfer = new AddGoalOkAction().execute(req, resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/cntgoalview" :
			try {
				transfer = new CntGoalViewAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/todook":
			try {
				transfer = new todolistAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
			
		case "/schedule/todoview":
			try {
				transfer = new todoViewAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/todocheck":
			try {
				transfer = new todoCheckAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/todoimport":
			try {
				transfer = new todoImportAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/tododeleteok":
			try {
				transfer = new todoDeleteAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/writesetview":
			try {
				transfer = new WriteSetViewAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/writeview":
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/schedule/writeview.jsp");
		default:
			break;
		}
		return transfer;
	}
	
}
