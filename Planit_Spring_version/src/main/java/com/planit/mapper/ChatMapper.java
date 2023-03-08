package com.planit.mapper;

import java.util.ArrayList;
import java.util.List;

import com.planit.domain.ChatDTO;

public interface ChatMapper {
   
   public List<ChatDTO> getchatroomList();
 
   public boolean makeChatRoom();

   public int getchatroomlastnum(); 

   public List<ChatDTO> getchatcont();

   public String getchatname();

   public ArrayList<String> getchatmembers();

   public boolean sendChat();
   
   public int getLastChat();
   
   public String insertChat();
   
   public int insertChatMember();
         
}