package com.planit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planit.domain.ContactDTO;
import com.planit.domain.UserDTO;
import com.planit.service.UserService;
import com.planit.util.LoginCheck;

import lombok.Setter;

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Setter(onMethod_ = @Autowired)
	private UserService service;
	
	@GetMapping("/joinuser")
	public String join() {
		return "/user/joinview";
	}
	
	@GetMapping("/loginuser")
	public String login() {
		return "/user/loginview";
	}
	
	@PostMapping("/loginok")
	public String userLogin(String userid, String userpw, HttpServletRequest req, RedirectAttributes ra) throws Exception {
		
		if(service.userLogin(userid,userpw,req) != null) {
			return "redirect:/schedule/mainview";			
		}
		else {
			ra.addFlashAttribute("f","f");
		//	System.out.println("로그인 실패");
			return "redirect:/user/loginuser";
		}
	}
	
	@GetMapping("checkidok")
	public ResponseEntity<String> checkid(String userid){
		return service.checkid(userid) ? new ResponseEntity<String>("O",HttpStatus.OK):
			new ResponseEntity<String>("X",HttpStatus.OK);
	}
	
	@PostMapping("joinok")
	public String joinok(UserDTO udto) {
		return service.joinok(udto) ? "/user/loginview" : "/user/joinview";
		
	}
	
	@GetMapping("findid")
	public void findid() {}
	@GetMapping("findpw")
	public void findpw() {}
	
	@GetMapping("finduserid")
	public ResponseEntity<String> finduserid(String phone){
		String userid=service.findid(phone);
		return new ResponseEntity<String>(userid,HttpStatus.OK);
	}
	@GetMapping(value ="finduserpw", 
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<UserDTO> finduserpw(String userid){
		UserDTO userpw=service.findpw(userid);
		return new ResponseEntity<UserDTO>(userpw,HttpStatus.OK);
	}
	
	@GetMapping("/userlogout")
	public String logout(HttpServletRequest req) {
  	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		service.logout(req);
		return "home";
	}
	
	@GetMapping("/usergoal")
	public String goalList(HttpServletRequest req, Model model) throws Exception {
  	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		model.addAttribute("goalList",service.goalList(req));
		return "/user/goalview";				
	}
	
	@DeleteMapping("/deletegoal")
	public String deleteGoal(int goalnum, HttpServletRequest req) {
  	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		if(service.deleteGoal(goalnum)) {
		}
		return "redirect:/user/usergoal";			
	}
	
//	@GetMapping("/usermyinfo")
//	public String modifyInfo(HttpServletRequest req, Model model) throws Exception {
//		
//		model.addAttribute("goalList",service.goalList(req));
//		return "/user/goalview";				
//	}
	
	@GetMapping("/usercontact")
	public String usercontact() {
		return "/user/contactview";
	}
	
	@PostMapping("/usercontactokk")
	public String usercontactok(String contacttitle, String contactcontents,HttpServletRequest req ) {
  	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		if(service.addcontact(contacttitle,contactcontents,req)) {			 
		 }else {	 
		 }
		 return "/user/contactview";
	}
	
	@GetMapping("/userwithdraw")
	public String withdraw() {
		return "/user/withdrawview";
	}
	
	@GetMapping("/usermyinfo")
	public String myinfo() {
		return "/user/myinfoview";
	}
	
	@PostMapping("usermyinfook")
	public String myinfoOk(UserDTO loginUser,HttpServletRequest req) {
  	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		if(service.modifyInfo(loginUser,req)) {
			
		}else {
			
		}
		return "/user/myinfoview";
	}
	
	@PostMapping("/userwithdrawok")
	public String withdrawok(@RequestParam(value = "userid")String userid, @RequestParam(value = "userpw")String userpw,HttpServletRequest req) {
  	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		if(service.withdrawOk(userid,userpw,req)) {			 
		 }else {
		 }
		return "home";
	}
	
	   @GetMapping("/friendsearchlist")
	   public String getFriendList(String keyword, HttpServletRequest req, Model model) {
	    	  if(LoginCheck.logincheck(req)) {
				  return "redirect:/";
			  }	      
	      model.addAttribute("friendList", service.getFriendList(keyword, req));
//	      model.addAttribute("keyword", keyword);
	      
	      return "/user/friendsearchview";
	   }
	
	
}
