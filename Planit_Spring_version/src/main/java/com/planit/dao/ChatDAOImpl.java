package com.planit.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.planit.domain.ChatDTO;

import lombok.Getter;
import lombok.Setter;

@Repository
public class ChatDAOImpl implements ChatDAO{

   @Inject
   private SqlSession sqlSession;
         public ChatDAOImpl() {
      }
         
         public ChatDAOImpl(SqlSession sqlSession) {
            if(this.sqlSession == null) {
               this.sqlSession = sqlSession;
            }
      }
    
    private static String namespace = "com.planit.mapper.ChatMapper.";

    public void Testsql() {
      System.out.println(sqlSession);
   }
    
   @Override
   public List<ChatDTO> getchatroomList(String userid) {
       List<ChatDTO> result = null;      
         result = sqlSession.selectList(namespace + "getChatroomList",userid);            
         return result;
   }

   @Override
   public boolean makeChatRoom(String chatroomname, ArrayList<String> memberList) {
      try {
            sqlSession.insert(namespace+"makeChatRoom",chatroomname);
            int chatroomnum = getchatroomlastnum();
            HashMap<String, String> datas = new HashMap<String, String>();
            datas.put("chatroomnum", chatroomnum+"");
            for (int i = 0; i < memberList.size(); i++) {
               datas.put("userid",memberList.get(i));
               sqlSession.insert(namespace+"insertChatMember",datas);
            }
            return true;
         } catch (Exception e) {
            return false;
         }
   }

   @Override
   public int getchatroomlastnum() {
      // TODO Auto-generated method stub
      return (Integer)sqlSession.selectOne(namespace+"getchatroomlastnum");
   }

   
   @Override
   public List<ChatDTO> getchatcont(int chatroomnum) {
      List<ChatDTO> result = null;
       result = sqlSession.selectList(namespace+"getchatcont", chatroomnum);
       return result;
   }
   
   @Override
   public String getchatname(int chatroomnum) {
      return sqlSession.selectOne(namespace+"getchatname",chatroomnum);
   }

   @Override
   public ArrayList<String> getchatmembers(int chatroomnum) {
         System.out.println(sqlSession+"sqlsp");
         System.out.println();
        List<String> memberslist = sqlSession.selectList(namespace+"getchatmembers",chatroomnum);
         ArrayList<String> members = new ArrayList<String>();
         members.addAll(memberslist);
         return members;
   }

   @Override
   public boolean sendChat(String message, int chatroomnum, ArrayList<String> arrayList, String loginUser) {
      sqlSession.insert(namespace+"insertChat",message);
         int chatnum = sqlSession.selectOne(namespace+"getLastChat");
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
               int result = sqlSession.insert(namespace+"sendChat",cdto);
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