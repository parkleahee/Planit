package com.planit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planit.dao.ChatDAO;
import com.planit.dao.ChatDAOImpl;
import com.planit.dao.FriendDAO;
import com.planit.dao.FriendDAOImpl;
import com.planit.domain.ChatDTO;
import com.planit.domain.FriendDTO;
import com.planit.domain.UserDTO;

import lombok.Setter;
@Service
public class ChatServiceImpl implements ChatService{
   @Setter(onMethod_=@Autowired)
   FriendDAO fdao = new FriendDAOImpl();
   
   @Setter(onMethod_=@Autowired)
   ChatDAO cdao = new ChatDAOImpl();
   
   @Override
   public List<ChatDTO> getchatroomList(String userid, HttpServletRequest req) {      
      
      HttpSession session = req.getSession();
      userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();      
      
      List<FriendDTO> friendlsList = fdao.getfrlsList(userid);
      ArrayList<UserDTO> realfrilist = new ArrayList<UserDTO>();
      for (int i = 0; i < friendlsList.size(); i++) {
         UserDTO udto = new UserDTO();
         if(friendlsList.get(i).getUserid().equals(userid)) {
            udto.setUserid(friendlsList.get(i).getFriendid());
            udto.setUsername(friendlsList.get(i).getFriendname());
         }else {
            udto.setUserid(friendlsList.get(i).getUserid());
            udto.setUsername(friendlsList.get(i).getUsername());
         }
         realfrilist.add(udto);
      }
      
      req.setAttribute("friendlsList", realfrilist);
      req.setAttribute("userid", userid);
      
      List<ChatDTO> chatroomList = cdao.getchatroomList(userid);
      req.setAttribute("chatroomList", chatroomList);
      
      return  chatroomList;
   }

@Override
   public int makeChatRoomOk(String chatRoomName, String[] member, HttpServletRequest req) {
      ArrayList<String> memberList = new ArrayList<String>();
      memberList.add(((UserDTO)req.getSession().getAttribute("loginUser")).getUserid());
      for (int i = 0; i < member.length; i++) {
         System.out.println(member[i]);
         memberList.add(member[i]);
      } 
      if (cdao.makeChatRoom(chatRoomName,memberList)) {
         int chatroomnum = cdao.getchatroomlastnum();
         return chatroomnum;
      }else {
         return -1;
         }
   }

   @Override
   public HashMap<String, Object> chatroomEntranceOk(HttpServletRequest req, int chatroomnum) {
        String userid = ((UserDTO)req.getSession().getAttribute("loginUser")).getUserid();
         List<ChatDTO> chatroomList = cdao.getchatroomList(userid);
         List<ChatDTO> chatcontent = cdao.getchatcont(chatroomnum);
         String chatroomname = cdao.getchatname(chatroomnum);
         HashMap<String, Object> data = new HashMap<String, Object>();
         data.put("chatroomList", chatroomList);
         data.put("chatcontent", chatcontent);
         data.put("chatroomname", chatroomname);
      return data;
   }

   @Override
   public int makeChatRoom(String chatRoomName, String[] member, HttpServletRequest req) {
      ArrayList<String> memberList = new ArrayList<String>();
      memberList.add(((UserDTO)req.getSession().getAttribute("loginUser")).getUserid());
      for (int i = 0; i < member.length; i++) {
         System.out.println(member[i]);
         memberList.add(member[i]);
      } 
      if (cdao.makeChatRoom(chatRoomName,memberList)) {
         int chatroomnum = cdao.getchatroomlastnum();
         return chatroomnum;
      }else {
         return -1;
         }
   }

}