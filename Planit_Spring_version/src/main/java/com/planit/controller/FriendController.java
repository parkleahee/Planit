package com.planit.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.planit.domain.FriendDTO;
import com.planit.domain.UserDTO;
import com.planit.service.FriendService;
import com.planit.service.ScheduleService;

import lombok.Setter;

@RestController
@RequestMapping("/friend/*")
public class FriendController {
   @Setter(onMethod_ = @Autowired)
   private FriendService service;
   
   
   @GetMapping("/friendconfirmviewok")
   public void friendOk(String userid, HttpServletRequest req ,HttpServletResponse resp) throws IOException{
      service.friendconfirmviewok(userid, resp, req);
      
   }
   
//   @GetMapping(value="/friendconfirmviewok")
//   public ResponseEntity<String> regist(@RequestBody String userid){
//      boolean check = service.friendconfirmviewok(userid);
//      
//      return check ? new ResponseEntity<String>("O",HttpStatus.OK) : 
//         new ResponseEntity<String>("X",HttpStatus.OK);
//   }
   
   /*@GetMapping(value="/friendconfirmviewok")
   public ResponseEntity<String> regist(@RequestBody String userid, HttpServletRequest req){
      boolean check = service.friendconfirmviewok(userid);
      
      return check ? new ResponseEntity<String>("O",HttpStatus.OK) : 
         new ResponseEntity<String>("X",HttpStatus.OK);
      //HttpStatus.OK를 작성하지 않을 시 ajax에서 success로 들어가지 못하고 에러가 발생한다.
   }*/
   
//   @DeleteMapping(value="/friendconfirmviewno")
//   public ResponseEntity<String> friendNo(@RequestBody String userid){
//      boolean check = service.friendconfirmviewno(userid);
//      
//      return check ? new ResponseEntity<String>("O",HttpStatus.OK) : 
//         new ResponseEntity<String>("X",HttpStatus.OK);
//   }
   
   @GetMapping("/friendconfirmviewno")
   public void friendNo(String userid, HttpServletRequest req ,HttpServletResponse resp) throws IOException{
      service.friendconfirmviewno(userid, resp, req);
      
   }
   
   /*@DeleteMapping(value="/friendconfirmviewno")
   public ResponseEntity<String> friendNo(@RequestBody String userid, HttpServletRequest req){
      boolean check = service.friendconfirmviewno(userid);
      
      return check ? new ResponseEntity<String>("O",HttpStatus.OK) : 
         new ResponseEntity<String>("X",HttpStatus.OK);
   }*/
   
   @GetMapping("/friendblockok")
   public void friendblock(String friendid, HttpServletRequest req ,HttpServletResponse resp) throws IOException {
      
      service.friendblockok(friendid, resp, req);
         
      
   }   
//   @GetMapping(value="/friendblockok")
//   public ResponseEntity<String> friendblock(@RequestBody String friendid, @RequestBody String userid){
      
//      boolean check = service.friendblockok(friendid, userid);
//      
//      return check ? new ResponseEntity<String>("O",HttpStatus.OK) : 
//         new ResponseEntity<String>("X",HttpStatus.OK);
//   }
   
   /*@GetMapping(value="/friendblockok")
   public ResponseEntity<String> friendblock(@RequestBody String friendid, @RequestBody String userid, HttpServletRequest req){
      
      boolean check = service.friendblockok(friendid, userid);
      
      return check ? new ResponseEntity<String>("O",HttpStatus.OK) : 
         new ResponseEntity<String>("X",HttpStatus.OK);
   }*/
   // 추가 : POST, 차단 : PUT, 검색 : GET, 삭제 : DELETE
   
   @GetMapping(value="/friendblockend")
   public void friendblockend(String userid, HttpServletRequest req ,HttpServletResponse resp) throws IOException {
      
      service.friendblockend(userid, req, resp);
      
   }
   
//   @GetMapping(value="/friendblockend")
//   public ResponseEntity<String> friendblockend(@RequestBody String friendid, @RequestBody String userid){
//      
//      boolean check = service.friendblockend(userid);
//      
//      return check ? new ResponseEntity<String>("O",HttpStatus.OK) : 
//         new ResponseEntity<String>("X",HttpStatus.OK);
//   }
   
   /*@GetMapping(value="/friendblockend")
   public ResponseEntity<String> friendblockend(@RequestBody String friendid, @RequestBody String userid, HttpServletRequest req){
      
      boolean check = service.friendblockend(userid);
      
      return check ? new ResponseEntity<String>("O",HttpStatus.OK) : 
         new ResponseEntity<String>("X",HttpStatus.OK);
   }*/
   
}