package com.planit.chatfrontcontroller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Case;

import com.planit.action.ActionTo;
import com.planit.action.ExceptionPrint;
import com.planit.totalfrontcontroller.FrontController;

public class ChatFrontController implements FrontController{
	//채팅방 만들기
	@Override
	public ActionTo flow(HttpServletRequest req, HttpServletResponse resp, String command) {
		ActionTo transfer = null;
		ExceptionPrint ep = new ExceptionPrint();
		switch (command) {
		case "/chat/makeChatRoomOk.chat":
			try {
				transfer = new MakeNewChatRoomAction().execute(req, resp);
			} catch (Exception e) {
				ep.exceptionPrint(e, command);
			}
			break;
		case "/chat/makeChatRoom.chat":
			transfer = new ActionTo();
			transfer.setRedirect(false);
			transfer.setPath("/app/chat/makechat.jsp");
			break;
		case "/chat/ChatroomEntranceOk.chat" :
			try {
				transfer = new ChatroomEntranceOkAction().execute(req, resp);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ep.exceptionPrint(e, command);
			}
		default:
			break;
		}
		
		return transfer;
	}

	
}
