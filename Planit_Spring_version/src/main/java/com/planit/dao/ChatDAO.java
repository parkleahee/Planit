package com.planit.dao;

import java.util.ArrayList;
import java.util.List;

import com.planit.domain.ChatDTO;

public interface ChatDAO {

   public void Testsql();
   
   public List<ChatDTO> getchatroomList(String userid);

   public boolean makeChatRoom(String chatRoomName, ArrayList<String> memberList);

   public int getchatroomlastnum();

   public List<ChatDTO> getchatcont(int chatroomnum);

   public String getchatname(int chatroomnum);

   public ArrayList<String> getchatmembers(int chatroomnum);

   public boolean sendChat(String message, int chatroomnum, ArrayList<String> arrayList, String loginUser);

}