package com.planit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.planit.domain.ChatDTO;
import com.planit.domain.FriendDTO;
import com.planit.domain.UserDTO;
import com.planit.service.ChatService;
import com.planit.service.FriendService;
import com.planit.util.LoginCheck;

import lombok.Setter;

@Controller
@RequestMapping("/chat/*")
public class ChatController {
   @Setter(onMethod_ = @Autowired)
   private ChatService service;
   
   @PostMapping("/makeChatRoomOk")
   public String makeChatRoomOk(@RequestParam String chatRoomName, @RequestParam String[] chatMember,HttpServletRequest req) {
 	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	  int chatroomnum = service.makeChatRoomOk(chatRoomName, chatMember, req);
      if(chatroomnum !=-1) {
      return "redirect:/chat/chatroomentranceok?chatroomnum="+chatroomnum;}
      else {
         return "redirect:/friend/friendlslist";
      }
   }
   
   @RequestMapping("/makeChatRoom")
   public String getchatroomList(String userid, HttpServletRequest req, Model model) {
//      List<ChatDTO> chatroomList = service.getchatroomList(userid, req);
 	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	   model.addAttribute("chatroomList", service.getchatroomList(userid, req));
      return "/chat/makechat";
   }
   
//   @RequestMapping("/makeChatRoom")
//   public String getchatroomList(String userid, HttpServletRequest req, Model model) {
////      List<ChatDTO> chatroomList = service.getchatroomList(userid, req);
//      model.addAttribute("chatroomList", service.getchatroomList(userid, req));
//      return "/chat/makechat";
//   }
   
   @GetMapping("/chatroomentranceok")
   public String chatroomEntranceOk(HttpServletRequest req, Model model) {
 	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	   int chatroomnum = 0;
	   if(req.getParameter("chatroomnum")==null) {
		   chatroomnum =  Integer.parseInt((String)(req.getSession().getAttribute("sessionchat")));
	   }else {
		   chatroomnum = Integer.parseInt(req.getParameter("chatroomnum"));
		   req.getSession().setAttribute("sessionchat",chatroomnum+"");
	   }
      HashMap<String, Object> data = service.chatroomEntranceOk(req, chatroomnum);
         model.addAttribute("chatroomList", (List<ChatDTO>)data.get("chatroomList"));
         model.addAttribute("chatroomnum", chatroomnum);
         model.addAttribute("chatname", (String)data.get("chatroomname"));
         model.addAttribute("chatcontent", (List<ChatDTO>)data.get("chatcontent"));
      return "/chat/chatting";
   }
   
   @PostMapping("/makeNewChatRoom")
   public String makeChatRoom(@RequestParam String chatRoomName, @RequestParam String[] chatMember, HttpServletRequest req) {
 	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	   int chatroomnum = service.makeChatRoom(chatRoomName, chatMember, req);
      if(chatroomnum !=-1) {
         return "redirect:/chat/chatroomentranceok?chatroomnum="+chatroomnum;}
         else {
            return "redirect:/friend/friendlslist";
         }
   }
   
}