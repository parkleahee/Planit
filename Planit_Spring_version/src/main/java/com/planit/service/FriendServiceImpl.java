package com.planit.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import com.planit.dao.FriendDAO;
import com.planit.dao.FriendDAOImpl;
import com.planit.domain.FriendDTO;
import com.planit.domain.GroupDTO;
import com.planit.domain.UserDTO;
import com.planit.mapper.FriendMapper;

import lombok.Setter;

@Service
public class FriendServiceImpl implements FriendService {
   @Setter(onMethod_=@Autowired)
   FriendDAO fdao = new FriendDAOImpl();
   
   @Override
   public boolean friendconfirmviewok(String userid, HttpServletResponse resp, HttpServletRequest req) throws IOException {   
//      return fdao.friendOk(userid);
      userid = req.getParameter("userid");
      PrintWriter out = resp.getWriter();
      
      if(fdao.friendOk(userid)) {
         out.write("O");
      }
      else {
         out.write("X");
      }
      out.close();
      return true;
   }
   
   /*@Override
   public boolean friendconfirmviewok(String userid, HttpServletRequest req) {   
      userid = req.getParameter("userid");
      return fdao.friendOk(userid);
   }*/
   
   @Override
   public boolean friendconfirmviewno(String userid, HttpServletResponse resp, HttpServletRequest req) throws IOException{
//      return fdao.friendNo(userid);
      userid = req.getParameter("userid");
      PrintWriter out = resp.getWriter();      
      if(fdao.friendNo(userid)) {
         out.write("O");
      }
      else {
         out.write("X");
      }
      out.close();
      
      return true;
   }
   
   
   /*@Override
   public boolean friendconfirmviewno(String userid,HttpServletRequest req) {
      userid = req.getParameter("userid");
      return fdao.friendNo(userid);
   }*/

//   @Override
//   public boolean friendblockok(String friendid, String userid) {
//      HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//      friendid = req.getParameter("userid");
//      HttpSession session = req.getSession();
//      userid =((UserDTO)session.getAttribute("loginUser")).getUserid();
//      if(fdao.friendblock(friendid, userid)) {
//         
//      }
//      return fdao.friendblock(friendid, userid);
//      
//   }
   
   /*@Override
   public boolean friendblockok(String friendid, String userid, HttpServletRequest req) {
      friendid = req.getParameter("userid");
      HttpSession session = req.getSession();
      userid =((UserDTO)session.getAttribute("loginUser")).getUserid();
      return fdao.friendblock(friendid, userid);      
   }*/

   @Override
   public boolean friendblockend(String userid, HttpServletRequest req ,HttpServletResponse resp) throws IOException  {
//      return fdao.friendblockend(userid);
      userid = req.getParameter("userid");
      PrintWriter out = resp.getWriter();
      
      if(fdao.friendblockend(userid)) {
         out.write("O");
      }
      else {
         out.write("X");
      }
      out.close();
      
      return true;
   }
   

   /*@Override
   public boolean friendblockend(String userid, HttpServletRequest req) {
      userid = req.getParameter("userid");
      return fdao.friendblockend(userid);
   }*/
   
   @Override
   public List<FriendDTO> getConfirmList(String userid, HttpServletRequest req) {
//      String userid = req.getParameter("userid");
      HttpSession session = req.getSession();
      userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
      
      
      List<FriendDTO> confirmList = fdao.getconfirmList(userid);
      
      req.setAttribute("confirmList", confirmList);
      req.setAttribute("userid", userid);
      
      return confirmList;
   }

   @Override
   public ArrayList<UserDTO> getfriendlsList(HttpServletRequest req) {
      HttpSession session = req.getSession();
      String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();      
      
      List<FriendDTO> friendlsList = fdao.getfriendlsList(userid); // 친구랑 나랑 함께 담겨있는 dto리스트
      ArrayList<UserDTO> realfrilist = new ArrayList<UserDTO>(); // 친구만 추출한 리스트
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
      
//      req.setAttribute("friendlsList", realfrilist);
//      req.setAttribute("userid", userid);
      // 데이터 오류가 발생할 시 controller에 있는 addattribute를 남기고 setattribute를 지워볼 것
      return realfrilist;
   }

   @Override
   public ArrayList<UserDTO> getfriendBlockList(HttpServletRequest req) {
      HttpSession session = req.getSession();
      String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();

      List<FriendDTO> friendBlockList = fdao.getfriendBlockList(userid);
      ArrayList<UserDTO> realblocklist = new ArrayList<UserDTO>();
      for (int i = 0; i < friendBlockList.size(); i++) {
      
         UserDTO udto = new UserDTO();
         if(friendBlockList.get(i).getUserid().equals(userid)) {

            udto.setUserid(friendBlockList.get(i).getFriendid());
            udto.setUsername(friendBlockList.get(i).getFriendname());
         }else {

            udto.setUserid(friendBlockList.get(i).getUserid());
            udto.setUsername(friendBlockList.get(i).getUsername());
         }
         System.out.println(udto);
          realblocklist.add(udto);
      }
//      for (int i = 0; i <  realblocklist.size(); i++) {
//         System.out.println( realblocklist.get(i));
//         
//      }
      
//      req.setAttribute("friendBlockList", realblocklist);
//      req.setAttribute("userid", userid);
   
      return realblocklist;
   }

   @Override
   public boolean add(HttpServletRequest req) {
      HttpSession session = req.getSession();
      
      FriendDTO friend = new FriendDTO();
      String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
      String friendid = req.getParameter("friendid"); 
            
      friend.setUserid(userid);
      friend.setFriendid(friendid);
      
      if(fdao.add(friend)) {
         return true;
      }
      else {
         return false;

      }
      
   }

   @Override
   public boolean friendblockok(String friendid, HttpServletResponse resp, HttpServletRequest req) throws IOException {
      friendid = req.getParameter("userid");
      HttpSession session = req.getSession();
      String userid =((UserDTO)session.getAttribute("loginUser")).getUserid();
      PrintWriter out = resp.getWriter();
      if(fdao.friendblock(friendid, userid)) {
         System.out.println("1");
         out.print("O");
      } else {
         System.out.println("2");
         out.print("X");
      }
      
      return true;
   }

   @Override
   public ArrayList<UserDTO> getFriendList(HttpServletRequest req) {
      HttpSession session = req.getSession();
      UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
      String userid = loginUser.getUserid();
      ArrayList<UserDTO> flist = fdao.getFriendList(userid);
      return flist;
   }

   @Override
   public HashMap<String, Object> getFriendsDto(String[] friendIdList, String[] friends, HttpServletRequest req) {
//      HttpSession session = req.getSession();
      String userid = ((UserDTO)req.getSession().getAttribute("loginUser")).getUserid();
         
//      체크안된 친구들
      HashMap<String, UserDTO> uncheckedfriends = fdao.getFriendMap(userid);
         
//      체크된 친구들 리스트
      ArrayList<UserDTO> checkedfriendList = new ArrayList<UserDTO>();
      
//      체크된 친구들 이름이 ,로 연결됨
         String result = "";
         if(friends!=null) {
            for (int i = 0; i < friends.length; i++) {
               if(friends.length == 1 || friends.length - 1 == i) {
                  result += uncheckedfriends.get(friends[i]).getUsername();
                  checkedfriendList.add(uncheckedfriends.get(friends[i]));
                  uncheckedfriends.remove(friends[i]);
                  
               }else {
                  result += uncheckedfriends.get(friends[i]).getUsername()+", ";
                  checkedfriendList.add(uncheckedfriends.get(friends[i]));
                  uncheckedfriends.remove(friends[i]);
               }
            }
         }
//      체크안된 친구들 리스트
         ArrayList<UserDTO> newfriendList = new ArrayList<UserDTO>();
         newfriendList.addAll(uncheckedfriends.values());
         
         
         HashMap<String, Object> friendsDto = new HashMap<String, Object>();
         friendsDto.put("result", result);
         friendsDto.put("checkedfriendList", checkedfriendList);
         friendsDto.put("nocheckFriendList", newfriendList);
         
      
      return friendsDto;
   }
   
//      @Override
//      public List<UserDTO> getFriendList(HttpServletRequest req) {
//         HttpSession session = req.getSession();
//         UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
//         String userid = loginUser.getUserid();
//         
//         List<UserDTO> flist = fdao.getFriendList(userid);
//         return flist;
//      }
      
      @Override
      public String getFriendNAutho(int scnum) {
         List<GroupDTO> friendNAutho =fdao.getFriendNAutho(scnum);
         String authority = "";
            if(friendNAutho.size()!=0) {
               if(friendNAutho.get(0).getAuthority().equals("r")) {
                  authority = "함께보기";
               } else if(friendNAutho.get(0).getAuthority().equals("w")) {
                  authority = "함께수정";
               }
            } else {
              authority = "혼자하기";
            }
            
            return authority;
      }
      
      @Override
      public String getFriendName(int scnum) {
         String friends = "";
         List<String> friendName = fdao.getFriendName(scnum);
         List<GroupDTO> friendNAutho =fdao.getFriendNAutho(scnum);
           if(friendNAutho.size()!=0) {
              for (int i = 0; i < friendName.size(); i++) {
                 friends += friendName.get(i)+", ";//전체친구리스트(모든친구들이름)
              }
              friends = friends.substring(0,friends.length()-2);
           }
           return friends;
      }
      
      @Override
      public HashMap<String, Object> getModifyFriends(int scnum, String userid, String writer) {
         HashMap<String, Object> totalFriend = new HashMap<String, Object>();
         List<GroupDTO> friendNAutho =fdao.getFriendNAutho(scnum);
         String[] checkfriendList = new String[friendNAutho.size()];//체크된친구리스트(체크된 친구들아이디)
            for (int i = 0; i < friendNAutho.size(); i++) {
               checkfriendList[i]=friendNAutho.get(i).getFriends();
            }
            
//            체크안된 친구들
            HashMap<String,UserDTO> friends = fdao.getFriendMap(userid);
//            체크된 친구들 리스트
            ArrayList<UserDTO> checkedfriendList = new ArrayList<UserDTO>();
            System.out.println(friends+"여기가 지워지나-5");
//            체크된 친구들 이름이 ,로 연결됨
            String result = "";
            if(!userid.equals(writer)) {
            	UserDTO writerdto = friends.get(writer);
            	System.out.println(writer+"w");
            	friends = fdao.getFriendMap(writer);
            	writer = writerdto.getUsername();
            	result = writer+", ";
            	System.out.println(result);
            }
            ArrayList<String> friId = new ArrayList<String>();
            for (String key : friends.keySet()) {
               friId.add(key);
	           }
	           String [] friendIdList = new String[friends.size()]; //(모든 친구들아이디)
	           for (int i = 0; i < friends.size(); i++) {
	              friendIdList[i]=friends.get(friId.get(i)).getUserid();
	           } 
	            if(friendNAutho.size()!=0) {
	            for (int i = 0; i < friId.size(); i++) {
	                  String k= friId.get(i);
	                  UserDTO ud=  friends.get(k);
	                  String usernames = ud.getUsername();
	                  for (int j = 0; j < checkfriendList.length; j++) {
	                	  if(k.equals(checkfriendList[j])) {
	                		  result += usernames +", " ;
	                		  checkedfriendList.add(friends.get(friId.get(i)));
	                		  friends.remove(k);
					}
	                  }
	               }
	               result=result.length()!=0?result.substring(0,result.length()-2):"";
	            }
            
            //체크안된 친구들 리스트
            ArrayList<UserDTO> newfriendList = new ArrayList<UserDTO>();
            newfriendList.addAll(friends.values());
            totalFriend.put("result",result);
            totalFriend.put("checkedfriendList",checkedfriendList);
            totalFriend.put("nocheckFriendList", newfriendList);
            totalFriend.put("friendIdList", friendIdList);
         
         return totalFriend;
      }

   

}