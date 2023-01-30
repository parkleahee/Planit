package com.planit.chatfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.Action;
import com.planit.action.ActionTo;

public class ChatroomEntranceOkAction implements Action{

	@Override
	public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		ActionTo transfer = new ActionTo();
		String chatroomnum = req.getParameter("chatroomnum");
		req.setAttribute("chatroomnum", chatroomnum);
		transfer.setRedirect(false);
		transfer.setPath("/app/chat/chatting.jsp");
		return transfer;
	}

}
