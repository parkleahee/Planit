package com.planit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.planit.domain.FriendDTO;
import com.planit.domain.UserDTO;
import com.planit.service.FriendService;
import com.planit.util.LoginCheck;

import lombok.Setter;

@Controller
@RequestMapping("/friend/*")
public class FriendController_etc {
   @Setter(onMethod_ = @Autowired)
   private FriendService service;
   
   @GetMapping("/friendconfirmlist")
   public String getConfirmList(String userid, HttpServletRequest req, Model model) {
 	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	  List<FriendDTO> confirmList = service.getConfirmList(userid, req);
      model.addAttribute("confirmList", confirmList); 
      model.addAttribute("userid", userid);
         return "/friend/friendconfirmview";      
   }
   
   @GetMapping("/friendlslist")
   public String getfriendlsList(HttpServletRequest req, Model model) {

 	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
      model.addAttribute("friendlsList", service.getfriendlsList(req));
//      model.addAttribute("userid", userid);
      return "/friend/friendlistview";
   }
   
   @GetMapping("/friendblocklist")
   public String getfriendBlockList(HttpServletRequest req, Model model) {
 	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
      model.addAttribute("friendBlockList", service.getfriendBlockList(req));
//      model.addAttribute("userid", userid);
      return "/friend/friendblocklist";
   }
   
   @PostMapping("/addfriendok")
   public String add(HttpServletRequest req) {      
 	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	   if(service.add(req)){
         return "/user/friendsearchview";
      }else {
         return "/user/friendsearchview";         
      }
      
   }
}