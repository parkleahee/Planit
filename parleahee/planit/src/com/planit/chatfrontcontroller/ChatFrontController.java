package com.planit.chatfrontcontroller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Case;

import com.planit.action.ActionTo;
import com.planit.totalfrontcontroller.FrontController;

public class ChatFrontController implements FrontController{
	//채팅방 만들기
	@Override
	public ActionTo flow(HttpServletRequest req, HttpServletResponse resp, String command) {
		ActionTo transfer = new ActionTo();
		
		
		
		return null;
	}

	
}
