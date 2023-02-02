package com.planit.schedulecontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.ActionTo;
import com.planit.totalfrontcontroller.FrontController;


public class ScheduleFrontController implements FrontController{

	@Override
	public ActionTo flow(HttpServletRequest req, HttpServletResponse resp, String command) {
		ActionTo transfer = new ActionTo();
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
				transfer = new TimeTestAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/timelist":
			try {
				transfer = new TimeListAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/timedelete":
			try {
				new TimeDeleteAction().execute(req,resp);
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
				new TodoListOk().execute(req, resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
			
		case "/schedule/todoview":
			try {
				transfer = new TodoViewOk().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/todocheck":
			try {
				new TodoCheckOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/todoimport":
			try {
				new TodoImportOkAction().execute(req,resp);
			} catch (Exception e) {
				System.out.println(e);
			}
			break;
		case "/schedule/tododeleteok":
			try {
				new TodoDeleteOkAction().execute(req,resp);
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
		System.out.println(transfer.getPath());
		return transfer;
	}
	
}
