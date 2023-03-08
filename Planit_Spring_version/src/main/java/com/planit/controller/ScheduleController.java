package com.planit.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planit.domain.GoalDTO;
import com.planit.domain.TimeDTO;
import com.planit.domain.TodoDTO;
import com.planit.domain.UserDTO;
import com.planit.service.FriendService;
import com.planit.service.ScheduleService;
import com.planit.util.LoginCheck;

import lombok.Setter;

@RequestMapping("/schedule/*")
@Controller
public class ScheduleController {
	@Setter(onMethod_ = @Autowired)
	private ScheduleService service;

	@Setter(onMethod_ = @Autowired)
	   private FriendService service2;
	   
	   @GetMapping("/mainview")
	   public String goMain(HttpServletRequest req, Model model) {
		  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	      HashMap<String, Object> datas = service.mainOk(req);
	      model.addAttribute("scBarList",datas.get("scBarList"));
	      return "/schedule/mainview";
	   }
	   
	   
	   
	      @GetMapping("/writesetview")
	      public void writeset(@RequestParam(value = "start")String start, @RequestParam(value = "end")String end, HttpServletRequest req, Model model) {
	         try {
	            model.addAttribute("startt", start);
	            model.addAttribute("endd", end);
	            model.addAttribute("flist", service2.getFriendList(req));
	            model.addAttribute("titlelist", service.getScheduleList(start,req));
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	      
	   @PostMapping("/writeview")
	   public void writeview(@RequestParam(required = false) String[] friendIdList,@RequestParam(required = false) String[] friends, HttpServletRequest req, Model model ) {
		   if(LoginCheck.logincheck(req)) {
				  return;
			  }
		   
		  HashMap<String, Object> friendsDtos = service2.getFriendsDto(friendIdList,friends,req);
	      // 체크된 친구들 이름
	      model.addAttribute("result", friendsDtos.get("result"));
	      // 체크된 친구들 dto
	      model.addAttribute("checkedfriendList", friendsDtos.get("checkedfriendList"));
	      // 체크안된 친구들 dto
	      model.addAttribute("nocheckFriendList", friendsDtos.get("nocheckFriendList"));
//	      // 모든 친구들 아이디
	   }
	   
	      @PostMapping("/categorySave")
	      public String categorySave(HttpServletRequest req, RedirectAttributes ra) {
    	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
    	  boolean modifycheck = false;
	      if(service.insertCate(req, modifycheck)) {
	         ra.addFlashAttribute("t","스케줄 등록 성공!");
	      }else {
	         ra.addFlashAttribute("f","스케줄 등록 실패!");
	      }
	      
	      return "redirect:/schedule/mainview";
	      
	   }
	      @GetMapping("/getwriteview")
	      public void getwrite(@RequestParam(value = "scnum")int scnum, HttpServletRequest req, Model model) {
    	  if(LoginCheck.logincheck(req)) {
			  return;
		  }
	    	 model.addAttribute("scnum",scnum);
	         model.addAttribute("scbar",service.getScBar(scnum));
	         model.addAttribute("authority",service2.getFriendNAutho(scnum));
	         model.addAttribute("friends",service2.getFriendName(scnum));
	         model.addAttribute("monga",service.getMonga(scnum));
	         model.addAttribute("writer",service.getScId(scnum));
	      }
	      
	      @GetMapping("/schedulemodify")
	      public String modifywrite(@RequestParam(value = "scnum")int scnum,@RequestParam(value = "writer") String writer, HttpServletRequest req, Model model) {
	    	  if(LoginCheck.logincheck(req)) {
				  return "redirect:/";
			  }
	    	 HttpSession session = req.getSession();
	         UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
	         String userid = loginUser.getUserid();
	         model.addAttribute("scnum",scnum);
	         model.addAttribute("scbar",service.getScBar(scnum));
	         model.addAttribute("authority",service2.getFriendNAutho(scnum));
	         model.addAttribute("result", service2.getModifyFriends(scnum,userid,writer).get("result"));
	         System.out.println(service2.getModifyFriends(scnum,userid,writer).get("result"));
	         model.addAttribute("checkedfriendList", service2.getModifyFriends(scnum,userid,writer).get("checkedfriendList"));
	         model.addAttribute("nocheckFriendList", service2.getModifyFriends(scnum,userid,writer).get("nocheckFriendList"));
	         model.addAttribute("friendIdList", service2.getModifyFriends(scnum,userid,writer).get("friendIdList"));
	         model.addAttribute("monga",service.getMonga(scnum));
	         model.addAttribute("writer",writer);
	         
	         return "/schedule/modifywriteview";
	      }
	      
	      @GetMapping("/scheduledelete")
	      public String scheduleremove(@RequestParam int scnum, HttpServletRequest req,RedirectAttributes ra) {
    	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	    	 if(service.scheduleremove(scnum)) {
	            ra.addFlashAttribute("t","삭제 완료!");
	         }else {
	            ra.addFlashAttribute("f","삭제 실패!");
	         }
	         return "redirect:/schedule/mainview";
	      }
	      
	      
	      
	      @PostMapping("/schedulemodifyok")
	      public String schedulemodifyok(@RequestParam(value = "scnum")int scnum, HttpServletRequest req, RedirectAttributes ra) {
    	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
	         boolean modifycheck = true;
	         
//	         req.setAttribute("modifycheck", modifycheck);
	         if(service.insertCate(req, modifycheck)) {
	            ra.addFlashAttribute("t","스케줄 수정 완료!");
	         }else {
	            ra.addFlashAttribute("f","스케줄 수정 실패!");
	         }
	      
	         
	         return "redirect:/schedule/mainview";
	      }


//	dto로 주입할때 ,, 말도안되는 오류 뜨면 @noarg 어노테이션 생성되었는지 확인할것 ,, 
	@RequestMapping(value = "/timeadd", method = { RequestMethod.POST })
	public String timeAdd(TimeDTO tdto, HttpServletRequest req) {
  	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		if (service.timeAdd(tdto, req)) {
			System.out.println("성공");
		}
		return "redirect:/schedule/timelist";
	}

	@GetMapping("/timelist")
	public String timetable(HttpServletRequest req, Model model) {
  	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		model.addAttribute("timeList", service.timeList(req));
		return "/schedule/timetableview";
	}

	@GetMapping(value = "/{timenum}")
	public ResponseEntity<String> timeDelete(@PathVariable("timenum") int timenum) {
		return service.deleteTime(timenum) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/regist", consumes = "application/json")
	public ResponseEntity<String> regist(@RequestBody TodoDTO todo) {

		boolean check = service.regist(todo);
		int todonum = service.getMaxTodoNum(todo.getUserid());
		return check ? new ResponseEntity<String>(todonum +"", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/todolist/{userid}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })

	public ResponseEntity<List<TodoDTO>> getList(@PathVariable("userid") String userid) {

		return new ResponseEntity<List<TodoDTO>>(service.getList(userid), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{todonum}", produces = MediaType.TEXT_PLAIN_VALUE)

	public ResponseEntity<String> remove(@PathVariable("todonum") int todonum) {

		return service.deleteTodo(todonum) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = { RequestMethod.PUT }, value = "/{todonum}", consumes = "application/json")
	public ResponseEntity<String> importTodo(@PathVariable("todonum") int todonum) {

		return service.importTodo(todonum) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(method = { RequestMethod.PATCH }, value = "/{todonum}", consumes = "application/json")
	public ResponseEntity<String> checkTodo(@PathVariable("todonum") int todonum) {

		return service.checkTodo(todonum) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/{goal}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> addGoal(@PathVariable("goal") String goal, HttpServletRequest req,
			HttpServletResponse resp) {
		return service.addgoal(goal, req, resp) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/cntgoalview/{goal}/{goalnum}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> GoalCnt(@PathVariable("goal") String goal, @PathVariable("goalnum") int goalnum,
			HttpServletRequest req) {
		return service.cntGoal(goal, goalnum, req) ? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	  @GetMapping("/togetherlist")
	   public String gettogetherList(HttpServletRequest req, Model model) {
    	  if(LoginCheck.logincheck(req)) {
			  return "redirect:/";
		  }
		  model.addAttribute("togetherList", service.gettogetherList(req));      
//	        model.addAttribute("userid", userid);
	         return "/schedule/together";
	   }
}
	
