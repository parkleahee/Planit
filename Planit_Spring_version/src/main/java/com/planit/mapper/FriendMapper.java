package com.planit.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.planit.domain.FriendDTO;
import com.planit.domain.GroupDTO;
import com.planit.domain.UserDTO;

public interface FriendMapper {

   public boolean friendOk();
   
   public boolean friendNo();

   public boolean friendBlock();

   public boolean friendBlockEnd();
   
   public List<FriendDTO> getConfirmList();
   
   public List<FriendDTO> getConfirmListWithKey();
   
   public List<FriendDTO> getFriendlsList();

   public List<FriendDTO> getFriendlsListWithKey();
   
   public List<FriendDTO> getFriendBlockList();
   
   public List<FriendDTO> getFriendBlockListWithKey();
   
   public boolean add();
   
   public List<FriendDTO> getFrlsList();
   
   public List<FriendDTO> getFrlsListWithKey();
   
   public ArrayList<UserDTO> getFriendList();

   public HashMap<String, UserDTO> getFriendMap();
   
   public List<GroupDTO> getFriendNAutho();

   public List<String> getFriendName();


}