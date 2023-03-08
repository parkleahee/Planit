package com.planit.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.domain.FriendDTO;
import com.planit.domain.UserDTO;

public interface FriendService {

   public boolean friendconfirmviewok(String userid, HttpServletResponse resp, HttpServletRequest req) throws IOException;
   
   /* boolean friendconfirmviewok(String userid, HttpServletRequest req); */

   public boolean friendconfirmviewno(String userid, HttpServletResponse resp, HttpServletRequest req) throws IOException;
   
   /* boolean friendconfirmviewno(String userid, HttpServletRequest req); */

   public boolean friendblockok(String friendid, HttpServletResponse resp, HttpServletRequest req) throws IOException;
   
   /*boolean friendblockok(String friendid, String userid, HttpServletRequest req);*/

   public boolean friendblockend(String userid, HttpServletRequest req ,HttpServletResponse resp) throws IOException ;
   
   /* boolean friendblockend(String userid, HttpServletRequest req); */

   public List<FriendDTO> getConfirmList(String userid, HttpServletRequest req);

   public ArrayList<UserDTO> getfriendlsList(HttpServletRequest req);

   public ArrayList<UserDTO> getfriendBlockList(HttpServletRequest req);
   
   public boolean add(HttpServletRequest req);
   
   public ArrayList<UserDTO> getFriendList(HttpServletRequest req) throws Exception;

   public HashMap<String, Object> getFriendsDto(String[] friendIdList, String[] friends, HttpServletRequest req); 
   
   public String getFriendNAutho(int scnum);

   public String getFriendName(int scnum);

   public HashMap<String, Object> getModifyFriends(int scnum, String userid, String writer); 

}