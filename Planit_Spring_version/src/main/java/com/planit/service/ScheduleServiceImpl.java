package com.planit.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planit.dao.GoalDAO;
import com.planit.dao.GoalDAOImpl;
import com.planit.dao.ScheduleDAO;
import com.planit.dao.ScheduleDAOImpl;
import com.planit.dao.TimeDAO;
import com.planit.dao.TimeDAOImpl;
import com.planit.domain.AccountDTO;
import com.planit.domain.AccountRowDTO;
import com.planit.domain.CultureDTO;
import com.planit.domain.DiaryDTO;
import com.planit.domain.FoodDTO;
import com.planit.domain.GoalDTO;
import com.planit.domain.GroupDTO;
import com.planit.domain.MONGA;
import com.planit.domain.MapDTO;
import com.planit.domain.MemoDTO;
import com.planit.domain.SCDTO;
import com.planit.domain.ScheduleDTO;
import com.planit.domain.ScheduleDateDTO;
import com.planit.domain.SetScBarDTO;
import com.planit.domain.TimeDTO;
import com.planit.domain.TodoDTO;
import com.planit.domain.TotalAccountDTO;
import com.planit.domain.UserDTO;

import lombok.Setter;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	   @Setter(onMethod_ = @Autowired)
	   ScheduleDAO sdao = new ScheduleDAOImpl();
	
	@Setter(onMethod_ = @Autowired)
	GoalDAO gdao = new GoalDAOImpl();
	
	@Setter(onMethod_ = @Autowired)
	TimeDAO tdao = new TimeDAOImpl();
	@Override
	public boolean regist(TodoDTO tdto) {

		if (gdao.addTodo(tdto)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getMaxTodoNum(String userid) {
		int todonum = 0;

		todonum = gdao.getMaxTodoNum(userid);

		return todonum;
	}

	@Override
	public List<TodoDTO> getList(String userid) {
		List<TodoDTO> todoList = gdao.getTodoList(userid);
		return todoList;
	}

	@Override
	public boolean deleteTodo(int todonum) {
		if (gdao.deleteTodo(todonum)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean importTodo(int todonum) {
		if (gdao.importTodo(todonum)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean checkTodo(int todonum) {
		if (gdao.checkTodo(todonum)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addgoal(String goal, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		
		GoalDTO gdto = new GoalDTO();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		gdto.setUserid(userid);
		gdto.setGoal(goal);
		
		GoalDTO gdto1 = (GoalDTO) session.getAttribute("goal1");
		GoalDTO gdto2 = (GoalDTO) session.getAttribute("goal2");
		
		int goalnum = 0;
		
//		GoalDTO goal = gdao.addGoal(gdto);
		
		if (gdao.addGoal(gdto)) {
			if (gdto1 == null) {
				goalnum = gdao.getGoalnum(userid,goal);
				gdto1 = new GoalDTO();
				gdto1.setUserid(userid);
				gdto1.setGoal(goal);
				gdto1.setGoalnum(goalnum);
				gdto1.setGoalcheck("t");
				session.setAttribute("goal1", gdto1);
			} else if(gdto2 == null){
				goalnum = gdao.getGoalnum(userid,goal);
				gdto2 = new GoalDTO();
				gdto2.setUserid(userid);
				gdto2.setGoal(goal);
				gdto2.setGoalnum(goalnum);
				gdto2.setGoalcheck("t");
				session.setAttribute("goal2", gdto2);
			}
		}
		return true;
	}

	@Override
	public List<TimeDTO> timeList(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		List<TimeDTO> timeList = tdao.getDetail(userid);
		
		return timeList;
	}

	@Override
	public boolean timeAdd(TimeDTO tdto,HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		tdto.setUserid(userid);
		
		if(tdao.addTime(tdto)) {
			System.out.println("추가 성공");
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean deleteTime(int timenum) {
		if (tdao.deleteTime(timenum)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean cntGoal(String goal, int goalnum, HttpServletRequest req) {
		
		HttpSession session = req.getSession();

		if (goal.equals("goal1")) {
			System.out.println("들어옴");
			((GoalDTO)session.getAttribute("goal1")).setGoalnum(goalnum);
			//날짜 들어가있는지 확인해서 
			System.out.println(gdao.getCheckGoal(goalnum));
			if(gdao.getCheckGoal(goalnum)==0) {
				if(gdao.getGoal((GoalDTO)session.getAttribute("goal1"))) {
					System.out.println("들어옴2");
					int goalcnt = gdao.goalCnt(goalnum);
					System.out.println(goalcnt);
					GoalDTO gdto1 = (GoalDTO)session.getAttribute("goal1");	
					gdto1.setGoalcheck("f");
					gdto1.setGoalcnt(goalcnt);
					session.setAttribute("goal1", gdto1);
					}
			}else if(gdao.getCheckGoal(goalnum)==1) {
			}
		
		}else if(goal.equals("goal2")){
			System.out.println("2번 들어옴");
			((GoalDTO)session.getAttribute("goal2")).setGoalnum(goalnum);
		if(gdao.getGoal((GoalDTO)session.getAttribute("goal2"))) {
			System.out.println("2번 들어옴2");
			int goalcnt = gdao.goalCnt(goalnum);
			System.out.println(goalcnt);
			GoalDTO gdto2 = (GoalDTO)session.getAttribute("goal2");	
			gdto2.setGoalcheck("f");
			gdto2.setGoalcnt(goalcnt);
			session.setAttribute("goal2", gdto2);
			}
		}
		else {
			}

		return true;
	}
	
	   @Override
	   public List<ScheduleDTO> gettogetherList(HttpServletRequest req) {
	         HttpSession session = req.getSession();
	         String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
	         
	         List<ScheduleDTO> togetherList = sdao.gettogetherList(userid);
	         /*
	          * togetherList.forEach((v)->{ System.out.println(v.getScnum());
	          * System.out.println(v.getTitle()); System.out.println(v.getUserid()); });
	          */
//	         req.setAttribute("togetherList", togetherList);      
	                  
	         return togetherList;
	   }
	   
	   	  @Override
	      public List<ScheduleDTO> getScheduleList(String newStart, HttpServletRequest req) {
	         HttpSession session = req.getSession();
	         UserDTO loginUser = (UserDTO)session.getAttribute("loginUser");
	         String userid = loginUser.getUserid();
	         System.out.println(newStart);
	         List<ScheduleDTO> titleList = sdao.getScheduleList(newStart, userid);
	         return titleList;
	      }

	      @Override
	      public boolean insertCate(HttpServletRequest req, boolean modifycheck) {
	          
	          try {
		         HttpSession session = req.getSession();
		         String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		         String realuser ="";
	               
	               //dto등 선언
	              ScheduleDTO schedule = new ScheduleDTO();
	              GroupDTO gdto = new GroupDTO();
	              ScheduleDateDTO sddto = new ScheduleDateDTO();
	              SetScBarDTO setBar = new SetScBarDTO();
	              
	              
//	                  체크된 전체친구들 아이디
	              ArrayList<String> totalFriendsId = new ArrayList<String>();
	              if(modifycheck) {
	            	  	if(!req.getParameter("writer").equals(userid)) {
	            	  		realuser = userid;
	            	  		userid = req.getParameter("writer");
	            	  		
	            	  	}
	            	  
	                  int delscnum = Integer.parseInt(req.getParameter("scnum"));
	                  sdao.deleteSchedule(delscnum);
//	                 modifyview에서 체크되었던 친구의 아이디
	                  if(req.getParameterValues("modify_friend_content")!=null) {
	//	                  String[] modifyCheckedFriendId = req.getParameterValues("modify_friend_content");
	//	                               체크된 전체친구들 아이디
	//	                  for (int i = 0; i < modifyCheckedFriendId.length; i++) {
	//	                  totalFriendsId.add(modifyCheckedFriendId[i]);
	//	                  }
	                  }
	                 // totalFriendsId.add(realuser);
	                  totalFriendsId.remove(userid);
	                  modifycheck = false;
	               }
	           
//	                1. schedule 삽입
	               String title = req.getParameter("title");
	               String color = req.getParameter("color");
	                 
	              schedule.setScheduletitle(title);
	              schedule.setSchedulecolor(color);
	              schedule.setUserid(userid);
	              
//	               스케줄을 인서트 하는 동시에 scnum 가져옴
	              int scnum = sdao.insertScheduleDetail(schedule);
	              System.out.println("scnum: "+scnum);
	              schedule.setScnum(scnum);
	              String authority_content = req.getParameter("authority_content");
	//-----------------------------친구세팅 ---------------------------
	              //         새로 체크된 친구의 아이디
	              String [] friendsId= req.getParameterValues("friends");
	              System.out.println(req.getParameterValues("friends"));
	              
//	               setview에서 체크되었던 친구의 아이디
	              String [] setViewCheckedFriendsId = req.getParameterValues("setview_friend_content");
//	              modifyview에서 체크되었던 친구의 아이디(수정하기 전 기존 친구들 아이디)
	              String[] modifyCheckedFriendId = req.getParameterValues("modify_friend_content");
//	               체크된 전체친구들 아이디
//	              ArrayList<String> totalFriendsId = new ArrayList<String>();
	              
	              
	              if (friendsId != null) {
	                 for (int i = 0; i < friendsId.length; i++) {
//	                  System.out.println(friendsId[i]);
	                    totalFriendsId.add(friendsId[i]);
	                 }
	              }
	              if(setViewCheckedFriendsId!=null) {
	              for (int i = 0; i < setViewCheckedFriendsId.length; i++) {
//	               System.out.println(setViewCheckedFriendsId[i]);
	                 totalFriendsId.add(setViewCheckedFriendsId[i]);
	              }
	              }
	              for (int i = 0; i < totalFriendsId.size(); i++) {
	                 System.out.println("토탈프렌드 아이디리스트 : "+totalFriendsId.get(i));
	              }
//	               -------------------------------------------------------------------------------------------
	              gdto.setUserid(userid);
	              gdto.setScnum(scnum);
	              
	              if(authority_content != null) {
	                    if(authority_content.equals("혼자하기")) {
	                       //혼자하기면 authority에 set 안해도 됨
	                       
	                    } else if(authority_content.equals("함께보기")) {
	                       gdto.setAuthority("r");
	                     
	                    } else if(authority_content.equals("함께수정")) {
	                       gdto.setAuthority("w");
	                    }
	              } 
	              
	              if(gdto.getAuthority()!= null) {
	                     if(totalFriendsId!=null) {
	                         for (int i = 0; i < totalFriendsId.size(); i++) {
	                              gdto.setFriends(totalFriendsId.get(i));
	                              if(sdao.insertAuthority(gdto)) {
	                                 System.out.println("t_share 인서트 성공!");
	                              }
	                          }
	                 //혼자하기면 t_share 테이블에 인서트 안해도 됨
	                       }
	               }
	              System.out.println("저장시 권한 : "+gdto.getAuthority());
	              System.out.println("저장시 친구 : "+gdto.getFriends());
	                 
//	               3. scheduledate 삽입
	              String start = req.getParameter("term_content1");//시작 날짜 2022-01-20
	              String end = req.getParameter("term_content2");//2022-01-20
	              
	              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
	              
	              LocalDate startDate = LocalDate.parse(start, formatter);
	              LocalDate endDate = LocalDate.parse(end, formatter);
	           
	              if(start != null && end != null) {
	                 for (int j = 0; j <sdao.getDatesBetweenTwoDates(startDate, endDate.plusDays(1)).size(); j++) {
	                    
	                    sddto.setScdate(sdao.getDatesBetweenTwoDates(startDate, endDate.plusDays(1)).get(j).toString());
	                    sddto.setScnum(scnum);
	                    
	                    if(scnum != 0 &&sdao.insertDate(sddto)) {
//	                           등록성공! 메인 뷰로 돌아가기
//	                        transfer.setRedirect(true);
//	                        transfer.setPath(req.getContextPath()+"/schedule/mainview.tc");
	                       System.out.println(sddto+" 인서트 성공");
	                    }else {
	                       System.out.println(" 인서트 실패");
	                       
	                    }
	                 }   
	               }
//	               4.  스케줄 삽입 diary-1, culture-2 account-1,account-2
	              String[] cntValues = req.getParameterValues("cnt");
	              if(cntValues != null) {
	                 for (int i = 0; i < cntValues.length; i++) {
	                    String category = cntValues[i].split("-")[0];//diary , culture, culture
	                    System.out.println("카테고리: "+category);
	                    String contnum = cntValues[i].split("-")[1];// 1, 2, 3...
	                    
	                    if(category.equals("culture")) {
	                       
	                       String culturetitle = req.getParameter("cultitle-" + contnum);
	                       String culturetype = req.getParameter("cultype-" + contnum);
	                       String culturegenre = req.getParameter("culgenre-" + contnum);
	                       String culturecontents = req.getParameter("culcontents-" + contnum);
	                       int culturestar = (Integer.parseInt(req.getParameter("culstar-" + contnum)));
	                       
	                       SCDTO cdto = new CultureDTO(scnum,3,i+1,culturetitle,culturetype,culturegenre,culturestar,culturecontents);
	                       
	                       //문화 부분 데이터베이스 저장
	                       if(sdao.insertCulture(cdto)) {
	                          System.out.println("성공");
	                          
	                       } else {
	                          System.out.println("실패...");
	                       }
	                       //메모 부분 데이터베이스 저장
	                    } else if(category.equals("memo")) {
	                       
	                       String memocontents = req.getParameter("memocontents-" + contnum);
	                       
	                       SCDTO mdto = new MemoDTO(scnum, 7, i+1, memocontents);
	                       
	                       if(sdao.insertMemo(mdto)) {
	                          System.out.println("성공");
	                       } else {
	                          System.out.println("실패...");
	                       }
	                    } else if (category.equals("diary")){
	                          String diarydate = req.getParameter("diadate-"+contnum);
	                          String diaryweather = req.getParameter("diaweather-"+contnum);
	                          String diarytitle = req.getParameter("diatitle-"+contnum);
	                          String diarycontents = req.getParameter("diacont-"+contnum);
//	                           diarycontents=diarycontents.replace("\r\n","<br>");
	                          
	                          SCDTO diary = new DiaryDTO(scnum,4,i+1,diaryweather,diarytitle,diarycontents,diarydate);
	                                      
//	                  
	                          if(sdao.insertDiary(diary)){
	                             System.out.println(diary);               
	                          }
	                          else{
	                             System.out.println("실패..");
	                          }
//	                     
	                 
	                       }
	                       else if(category.equals("account")) {
//	                           row 가지고 와서 row의 갯수대로 돌려가면서 넣어야함 오 테이블 3개로 나누는게 좋겠네.. 그리고 hidden에 숫자는 지금 cnt + row 이렇게 들어가있음
//	                           스케줄넘버 - 카테고리 넘버 - 컨테이너 넘버 - 스타트date - enddate - memo - budget- 행넘버(1,3,5,6,7)
//	                           스케줄 넘버 - 컨테이너 넘버- 행넘버 - 날짜 - 내역- 금액 -유형      
	                          String[] accRowCnts = req.getParameterValues("accRowCnt");
//	                           accRowCnts = {1-1,1-3,2-1}
//	                           그중에 해당 contnum과 같은 것들만 뽑아내야 함 ex) {1-1,1-3}
	                          List<String> nums = new ArrayList<String>();
	                          for (int j = 0; j < accRowCnts.length; j++) {
	                             if(contnum.equals(accRowCnts[j].split("-")[0])) {
	                              nums.add(accRowCnts[j].split("-")[1]);
	                             }
	                          }
//	                            해당 행의 갯수 = num.size();
//	                           num.get(i)가 행 넘버
//	                           1,3,7,9
	                          String startdate = req.getParameter("startdate-"+contnum);
	                          String enddate = req.getParameter("enddate-"+contnum);
	                          String budget = req.getParameter("budget-"+contnum);
	                          String accmemo = req.getParameter("accmemo-"+contnum);
	                          accmemo = accmemo.replace("\r\n","<br>");
	                         

	                          //행넘버(1,3,5,6,7) 만들어서 저장하기위함
	                          String [] accountRowNumAr= new String[nums.size()];
	                          String accountRowNums="";
	                          for (int k = 0; k < nums.size(); k++) {
	                             
	                             String history = req.getParameter("history-"+contnum+"-"+nums.get(k));
	                             String amount = req.getParameter("amount-"+contnum+"-"+nums.get(k));
	                             String acctype = req.getParameter("acctype-"+contnum+"-"+nums.get(k));
	                             String accdate = req.getParameter("accdate-"+contnum+"-"+nums.get(k));
	                             
//	                           스케줄 넘버 - 컨테이너 넘버- 행넘버 - 날짜 - 내역- 금액 -유형      
	                             SCDTO accountRow = new AccountRowDTO(nums.get(k), scnum, 1, i+1, history, amount, acctype, accdate);
	                             if(sdao.insertAccountRow(accountRow)) {
	                                System.out.println(accountRow);
	                                
	                             }else {}
	                             
	                             accountRowNumAr[k] =nums.get(k);
	                             accountRowNums =String.join(",",accountRowNumAr);

	                             
//	                           startaccterm, endaccterm DTO및 테이블 만들기
//	                              AccountDTO account = new AccountDTO(2,7,i+1,startdate,enddate,budget,history,amount,acctype,accmemo,accdate);
//	                              System.out.println(account);
	                          }
//	                           스케줄넘버 - 카테고리 넘버 - 컨테이너 넘버 - 스타트date - enddate - memo - budget- 행넘버(1,3,5,6,7)
	                          SCDTO account = new AccountDTO(scnum, 1, i+1, startdate, enddate, budget, accmemo, accountRowNums);
	                          if(sdao.insertAccount(account)) {
	                             System.out.println(account);
	                             
	                          }else {
	                             
	                          }
	                       }
	                       else if(category.equals("map")) {
	                          String latNlng = req.getParameter("latNlng-"+contnum);
	                          if (latNlng.length()==0) {
	                             latNlng = "37.49998231489462/127.03541966047779";
	                          }
	                          String latitude = latNlng.split("/")[0];
	                          String longitude = latNlng.split("/")[1];
	                          
	                          SCDTO map = new MapDTO(scnum, 5, i+1, latitude, longitude);
	                          
	                          if(sdao.insertMap(map)) {
	                             System.out.println(map);                     
	                          }else {}
	                          
	                       }else if(category.equals("food")) {
//	                           
	                          String foodTime = req.getParameter("fd_time-"+contnum);
	                          int foodScore = Integer.parseInt(req.getParameter("fdstar-"+contnum));
	                          String foodContents = req.getParameter("fd_text-"+contnum);
	                          SCDTO food = new FoodDTO(scnum,2,i+1,foodTime,foodScore,foodContents);
	                          if(sdao.insertFood(food)) {
	                             System.out.println(food);
	                          }
	                       }
	                 }
	              }
	      } catch (NumberFormatException e) {
	         return false;
	      } 
	         return true;
	      }


	   @Override
	   public List<SetScBarDTO> getScBarList(HttpServletRequest req) {
	      HttpSession session = req.getSession();
	      String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
	      List<SetScBarDTO> scbarList =  sdao.getScBarList(userid);
	      return scbarList;
	   }

	      @Override
	      public HashMap<String, Object> getScBar(int scnum) {
	         SetScBarDTO preMenu= sdao.getScBar(scnum);
	         HashMap<String, Object> topMenu = new HashMap<>();
	         topMenu.put("startdate", preMenu.getStartdate());
	         topMenu.put("enddate", preMenu.getEnddate());
	         topMenu.put("title", preMenu.getTitle());
	         topMenu.put("color", preMenu.getColor());
	         return topMenu;
	      }
	      
	    
	      @Override
	      public HashMap<String, Object> getMonga(int scnum) {
	         HashMap<String, Object> mogcat = new HashMap<String, Object>();
	         ArrayList<Object> ar = new ArrayList<>();

	            ar.addAll(sdao.getMap(scnum));
	            ar.addAll(sdao.getMemo(scnum));
	            ar.addAll(sdao.getDiary(scnum));
	            ar.addAll(sdao.getCulture(scnum));
	            ar.addAll(sdao.getFood(scnum));
	            
	            List<AccountRowDTO> acrow= sdao.getAccountRow(scnum);
//	            하나의 account에 딸린 accountRow들
	            ArrayList<AccountRowDTO> ardto = new ArrayList<>();
	            //가져온 accountrow를 전부 ArrayList타입의 ardto로 때려넣음 이제 ardto에모든 accountrow가 들어감
	            ardto.addAll(acrow);

	            List<AccountDTO> ac =sdao.getAccount(scnum);
//	            만약 가계부가 2개면 컨트넘이 두개일 것임
	            int contnum [] = new int[ac.size()]; 
	            //contnum으로 구분해서 totalAccount에 넣어주어야함 그래서 contnum[]에 하나씩 넣음
	            //어카운트들을 리스트로 가지고 와서 하나씩 배열에 넣어줌
	            for (int i = 0; i < ac.size(); i++) {
	                  contnum[i]=ac.get(i).getContnum();
	            }
	            //account갯수(2)
	            for (int i = 0; i < ac.size(); i++) {
	               TotalAccountDTO totalAccount = new TotalAccountDTO();
	               ArrayList<AccountRowDTO> realAccountRow = new ArrayList<AccountRowDTO>();
//	               accountrow개수(3)
	               for (int j = 0; j < ardto.size(); j++) {
	                  if(contnum[i]==ardto.get(j).getContnum()) {
//	                     totalAccountAr의 0번째방의 totalAccountDto에 setA,setB해야함
	                     realAccountRow.add(ardto.get(j));
	                  }
	               }
	               totalAccount.setA(realAccountRow);
	               totalAccount.setB(ac.get(i));
	               totalAccount.setContnum(contnum[i]);
	               totalAccount.setCatnum(1);
	               ar.add(totalAccount);
	            }
	            
//	            SCDTO [] result = new SCDTO[ar.size()];
	            //카테고리 번호 배열과 리졸트배열을 같이 넘겨야 함 
	            MONGA [] monga = new MONGA[ar.size()];
//	            캣넘 배열 보내야함
	            int []catnum = new int[ar.size()];
	            
	            for (int i = 0; i < ar.size(); i++) {
	               MONGA mong = new MONGA();
	               Object obj=ar.get(i);
	               int indexnum = ((SCDTO)obj).getContnum();
	               if(((SCDTO)obj).getCatnum()==1) {
	                  catnum[indexnum-1]=1;
//	                  TotalAccountDTO total =(TotalAccountDTO)((SCDTO)obj);
	                  ArrayList<AccountRowDTO> accountRow =((TotalAccountDTO)(((SCDTO)obj))).getA();
	                  AccountDTO account =((TotalAccountDTO)(((SCDTO)obj))).getB();
	                  
//	                  -- 잔액을 몽가에 담아서 보내줘야함 (콤마제거해서 잔액 계산 후 다시 콤마찍어서 monga에 담아주기)--
	                  int accBalance =0;
	                  for (int j = 0; j < accountRow.size(); j++) {
	                  accBalance+=Integer.parseInt(accountRow.get(j).getAmount().replaceAll(",", ""));
	                  }
	                  try {
	                  accBalance=Integer.parseInt(account.getBudget().replaceAll(",", ""))-accBalance;
	               } catch (NumberFormatException e) {
	                  accBalance = -accBalance;
	               }
//	                  ---------------------------------------------------------------
	                  mong.setAccountRow(accountRow);
	                  mong.setAccount(account);
	                  mong.setAccBalance((accBalance+"").replaceAll("\\B(?=(\\d{3})+(?!\\d))", ","));
	                  
	               }else if(((SCDTO)obj).getCatnum()==2) {
	                  catnum[indexnum-1]=2;
	                  FoodDTO food = (FoodDTO)((SCDTO)obj);
	                  mong.setFood(food);
	                  
	               }else if(((SCDTO)obj).getCatnum()==3) {
	                  catnum[indexnum-1]=3;
	                  CultureDTO culture=(CultureDTO)((SCDTO)obj);
	                  mong.setCulture(culture);
	                  
	               }else if(((SCDTO)obj).getCatnum()==4) {
	                  catnum[indexnum-1]=4;
	                  DiaryDTO diary = (DiaryDTO)((SCDTO)obj);
	                  mong.setDiary(diary);
	               }else if(((SCDTO)obj).getCatnum()==5) {
	                  catnum[indexnum-1]=5;
	                  MapDTO map = (MapDTO)((SCDTO)obj);
	                  mong.setMap(map);
	               }else if (((SCDTO)obj).getCatnum()==6){
	                  //이미지
	               }else if(((SCDTO)obj).getCatnum()==7){
	                  catnum[indexnum-1]=7;
	                  MemoDTO memo = (MemoDTO)((SCDTO)obj);
	                  mong.setMemo(memo);
	               }
	               monga[indexnum-1]=mong;
	            }
	            
	            mogcat.put("monga", monga);
	            mogcat.put("catnum", catnum);
	            return mogcat;
	      }
	      
	      @Override
	      public boolean scheduleremove(int scnum) {
	         return sdao.deleteSchedule(scnum);
	      }
	      


	   @Override
	   public HashMap<String, Object> mainOk(HttpServletRequest req) {
	      HttpSession session = req.getSession();
	      String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
	      List<SetScBarDTO> scBarList =sdao.getScBarList(userid);
	      
	      HashMap<String, Object> datas = new HashMap<>();
	      datas.put("scBarList", scBarList);
	      
	      return datas;
	      
	   }

	@Override
	public String getScId(int scnum) {
		// TODO Auto-generated method stub
		return sdao.getScId(scnum);
	}

	   

}
