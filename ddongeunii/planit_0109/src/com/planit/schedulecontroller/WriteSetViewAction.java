package com.planit.schedulecontroller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.Action;
import com.planit.action.ActionTo;

public class WriteSetViewAction implements Action {

		@Override
		public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		System.out.println(start);
		System.out.println(end);
		
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 mm월 dd일");
//		String strstart = simpleDateFormat.format(start);
//		System.out.println(strstart);
		
		String[]startt=start.split(" ");
		String[]endd=end.split(" ");
		System.out.println(startt[0]);
		System.out.println(endd[0]);
		
		
		
		ActionTo transfer = new ActionTo();
		transfer.setRedirect(false);
		transfer.setPath("/app/schedule/writesetview.jsp");
		return transfer;
		}
	}

