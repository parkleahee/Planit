package com.planit.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.planit.domain.ChatDTO;

public interface ChatService {

   List<ChatDTO> getchatroomList(String userid, HttpServletRequest req);

   int makeChatRoomOk(String chatRoomName, String[] chatMember, HttpServletRequest req);

   HashMap<String, Object> chatroomEntranceOk(HttpServletRequest req, int chatroomnum);

   int makeChatRoom(String chatRoomName, String[] chatMember, HttpServletRequest req);

   

}