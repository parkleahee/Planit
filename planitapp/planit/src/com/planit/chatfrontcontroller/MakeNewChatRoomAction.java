package com.planit.chatfrontcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.dao.ChatDAO;

public class MakeNewChatRoomAction implements Action{
@Override
public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	String chatroomname=req.getParameter("chatroomname");
	String[] member =  req.getParameterValues("ChatMember");
	ArrayList<String> memberList = new ArrayList<String>();
	for (int i = 0; i < member.length; i++) {
		memberList.add(member[i]);
	}
	ChatDAO cdao = new ChatDAO();
	int chatroomnum = cdao.getchatroomlastnum(chatroomname);
	ChatServer.getChatmembers().put(chatroomnum, memberList);
	return null;
}
}
