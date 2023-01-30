package com.planit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.planit.dto.ChatDTO;
import com.planit.mybatis.SqlMapConfig;

public class ChatDAO {
		SqlSession sqlSession;
	public ChatDAO() {
		sqlSession = SqlMapConfig.getFactory().openSession(true);
	}
	
	public int getchatroomlastnum() {
		return (Integer)sqlSession.selectOne("Chat.getchatroomlastnum");
	}

	public ArrayList<String> getchatmembers(int chatroomnum) {
		List<String> memberslist = sqlSession.selectList("Chat.getchatmembers",chatroomnum);
		ArrayList<String> members = new ArrayList<String>();
		members.addAll(memberslist);
		return members;
	}

	public boolean sendChat(String message, int chatroomnum, ArrayList<String> chatmember, String loginUser) {
		sqlSession.insert("Chat.insertChat",message);
		int chatnum = sqlSession.selectOne("Chat.getLastChat");
		System.out.println(loginUser);
		try {
			for (int i = 0; i < chatroomnum; i++) {
				ChatDTO cdto = new ChatDTO();
				cdto.setChatroomnum(chatroomnum);
				cdto.setChatnum(chatnum);
				cdto.setFromUser(loginUser);
				System.out.println(loginUser);
				//cdto.setToUser(chatmember.get(i));
				cdto.setToUser("banana");
				int result = sqlSession.insert("Chat.sendChat",cdto);
				System.out.println(result);
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean makeChatRoom(String chatroomname, ArrayList<String> memberlist) {
		try {
			sqlSession.insert("Chat.makeChatRoom",chatroomname);
			int chatroomnum = getchatroomlastnum();
			HashMap<String, String> datas = new HashMap<String, String>();
			datas.put("chatroomnum", chatroomnum+"");
			for (int i = 0; i < memberlist.size(); i++) {
				datas.put("userid",memberlist.get(i));
				sqlSession.insert("Chat.insertChatMember",datas);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
