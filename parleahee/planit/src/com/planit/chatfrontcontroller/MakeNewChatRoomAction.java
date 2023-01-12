package com.planit.chatfrontcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.Action;
import com.planit.action.ActionTo;
import com.planit.action.Location;
import com.planit.dao.ChatDAO;
import com.planit.dto.UserDTO;

public class MakeNewChatRoomAction implements Action{
@Override
public ActionTo execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	ActionTo transfer = new ActionTo();
	String chatRoomName=req.getParameter("chatRoomName");
	String[] member =  req.getParameterValues("chatMember");
	ArrayList<String> memberList = new ArrayList<String>();
	memberList.add(((UserDTO)req.getSession().getAttribute("loginUser")).getUserid());
	transfer.setRedirect(true);
	for (int i = 0; i < member.length; i++) {
		memberList.add(member[i]);
	} 
	ChatDAO cdao = new ChatDAO();
	if (cdao.makeChatRoom(chatRoomName,memberList)) {
		int chatroomnum = cdao.getchatroomlastnum();
		String path = new Location().fowardPath(req, "/app/chat/chatting.jsp?chatroomnum="+chatroomnum);
		transfer.setPath(path);
		return transfer;
	}else {
		transfer.setPath("/");
		return transfer;
	}
}
}
