package com.planit.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.planit.dto.ChatDTO;
import com.planit.mybatis.SqlMapConfig;

public class ChatDAO {
		SqlSession sqlSession;
	public ChatDAO() {
		sqlSession = SqlMapConfig.getFactory().openSession(true);
	}
	
	public int getchatroomlastnum(String chatroomname) {
		return 0;
	}

	public ArrayList<String> getchatmembers(int chatroomnum) {
		return null;
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

}
