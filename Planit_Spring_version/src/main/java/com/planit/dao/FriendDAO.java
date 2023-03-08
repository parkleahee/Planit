package com.planit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.planit.domain.FriendDTO;
import com.planit.domain.GroupDTO;
import com.planit.domain.UserDTO;

public interface FriendDAO {
   public boolean friendOk(String userid);

   public boolean friendNo(String userid);

   public boolean friendblock(String friendid, String userid);

   public boolean friendblockend(String userid);

   public List<FriendDTO> getconfirmList(String userid);

   public List<FriendDTO> getfriendlsList(String userid);

   public List<FriendDTO> getfriendBlockList(String userid);

   public boolean add(FriendDTO friend);

   public List<FriendDTO> getfrlsList(String userid);
   
   public ArrayList<UserDTO> getFriendList(String userid);

   public HashMap<String, UserDTO> getFriendMap(String userid);
   
   public List<GroupDTO> getFriendNAutho(int scnum);

   public List<String> getFriendName(int scnum);
   

}