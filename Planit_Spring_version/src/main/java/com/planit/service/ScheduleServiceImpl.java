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
			System.out.println("?????? ??????");
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
			System.out.println("?????????");
			((GoalDTO)session.getAttribute("goal1")).setGoalnum(goalnum);
			//?????? ?????????????????? ???????????? 
			System.out.println(gdao.getCheckGoal(goalnum));
			if(gdao.getCheckGoal(goalnum)==0) {
				if(gdao.getGoal((GoalDTO)session.getAttribute("goal1"))) {
					System.out.println("?????????2");
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
			System.out.println("2??? ?????????");
			((GoalDTO)session.getAttribute("goal2")).setGoalnum(goalnum);
		if(gdao.getGoal((GoalDTO)session.getAttribute("goal2"))) {
			System.out.println("2??? ?????????2");
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
	               
	               //dto??? ??????
	              ScheduleDTO schedule = new ScheduleDTO();
	              GroupDTO gdto = new GroupDTO();
	              ScheduleDateDTO sddto = new ScheduleDateDTO();
	              SetScBarDTO setBar = new SetScBarDTO();
	              
	              
//	                  ????????? ??????????????? ?????????
	              ArrayList<String> totalFriendsId = new ArrayList<String>();
	              if(modifycheck) {
	            	  	if(!req.getParameter("writer").equals(userid)) {
	            	  		realuser = userid;
	            	  		userid = req.getParameter("writer");
	            	  		
	            	  	}
	            	  
	                  int delscnum = Integer.parseInt(req.getParameter("scnum"));
	                  sdao.deleteSchedule(delscnum);
//	                 modifyview?????? ??????????????? ????????? ?????????
	                  if(req.getParameterValues("modify_friend_content")!=null) {
	//	                  String[] modifyCheckedFriendId = req.getParameterValues("modify_friend_content");
	//	                               ????????? ??????????????? ?????????
	//	                  for (int i = 0; i < modifyCheckedFriendId.length; i++) {
	//	                  totalFriendsId.add(modifyCheckedFriendId[i]);
	//	                  }
	                  }
	                 // totalFriendsId.add(realuser);
	                  totalFriendsId.remove(userid);
	                  modifycheck = false;
	               }
	           
//	                1. schedule ??????
	               String title = req.getParameter("title");
	               String color = req.getParameter("color");
	                 
	              schedule.setScheduletitle(title);
	              schedule.setSchedulecolor(color);
	              schedule.setUserid(userid);
	              
//	               ???????????? ????????? ?????? ????????? scnum ?????????
	              int scnum = sdao.insertScheduleDetail(schedule);
	              System.out.println("scnum: "+scnum);
	              schedule.setScnum(scnum);
	              String authority_content = req.getParameter("authority_content");
	//-----------------------------???????????? ---------------------------
	              //         ?????? ????????? ????????? ?????????
	              String [] friendsId= req.getParameterValues("friends");
	              System.out.println(req.getParameterValues("friends"));
	              
//	               setview?????? ??????????????? ????????? ?????????
	              String [] setViewCheckedFriendsId = req.getParameterValues("setview_friend_content");
//	              modifyview?????? ??????????????? ????????? ?????????(???????????? ??? ?????? ????????? ?????????)
	              String[] modifyCheckedFriendId = req.getParameterValues("modify_friend_content");
//	               ????????? ??????????????? ?????????
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
	                 System.out.println("??????????????? ?????????????????? : "+totalFriendsId.get(i));
	              }
//	               -------------------------------------------------------------------------------------------
	              gdto.setUserid(userid);
	              gdto.setScnum(scnum);
	              
	              if(authority_content != null) {
	                    if(authority_content.equals("????????????")) {
	                       //??????????????? authority??? set ????????? ???
	                       
	                    } else if(authority_content.equals("????????????")) {
	                       gdto.setAuthority("r");
	                     
	                    } else if(authority_content.equals("????????????")) {
	                       gdto.setAuthority("w");
	                    }
	              } 
	              
	              if(gdto.getAuthority()!= null) {
	                     if(totalFriendsId!=null) {
	                         for (int i = 0; i < totalFriendsId.size(); i++) {
	                              gdto.setFriends(totalFriendsId.get(i));
	                              if(sdao.insertAuthority(gdto)) {
	                                 System.out.println("t_share ????????? ??????!");
	                              }
	                          }
	                 //??????????????? t_share ???????????? ????????? ????????? ???
	                       }
	               }
	              System.out.println("????????? ?????? : "+gdto.getAuthority());
	              System.out.println("????????? ?????? : "+gdto.getFriends());
	                 
//	               3. scheduledate ??????
	              String start = req.getParameter("term_content1");//?????? ?????? 2022-01-20
	              String end = req.getParameter("term_content2");//2022-01-20
	              
	              DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
	              
	              LocalDate startDate = LocalDate.parse(start, formatter);
	              LocalDate endDate = LocalDate.parse(end, formatter);
	           
	              if(start != null && end != null) {
	                 for (int j = 0; j <sdao.getDatesBetweenTwoDates(startDate, endDate.plusDays(1)).size(); j++) {
	                    
	                    sddto.setScdate(sdao.getDatesBetweenTwoDates(startDate, endDate.plusDays(1)).get(j).toString());
	                    sddto.setScnum(scnum);
	                    
	                    if(scnum != 0 &&sdao.insertDate(sddto)) {
//	                           ????????????! ?????? ?????? ????????????
//	                        transfer.setRedirect(true);
//	                        transfer.setPath(req.getContextPath()+"/schedule/mainview.tc");
	                       System.out.println(sddto+" ????????? ??????");
	                    }else {
	                       System.out.println(" ????????? ??????");
	                       
	                    }
	                 }   
	               }
//	               4.  ????????? ?????? diary-1, culture-2 account-1,account-2
	              String[] cntValues = req.getParameterValues("cnt");
	              if(cntValues != null) {
	                 for (int i = 0; i < cntValues.length; i++) {
	                    String category = cntValues[i].split("-")[0];//diary , culture, culture
	                    System.out.println("????????????: "+category);
	                    String contnum = cntValues[i].split("-")[1];// 1, 2, 3...
	                    
	                    if(category.equals("culture")) {
	                       
	                       String culturetitle = req.getParameter("cultitle-" + contnum);
	                       String culturetype = req.getParameter("cultype-" + contnum);
	                       String culturegenre = req.getParameter("culgenre-" + contnum);
	                       String culturecontents = req.getParameter("culcontents-" + contnum);
	                       int culturestar = (Integer.parseInt(req.getParameter("culstar-" + contnum)));
	                       
	                       SCDTO cdto = new CultureDTO(scnum,3,i+1,culturetitle,culturetype,culturegenre,culturestar,culturecontents);
	                       
	                       //?????? ?????? ?????????????????? ??????
	                       if(sdao.insertCulture(cdto)) {
	                          System.out.println("??????");
	                          
	                       } else {
	                          System.out.println("??????...");
	                       }
	                       //?????? ?????? ?????????????????? ??????
	                    } else if(category.equals("memo")) {
	                       
	                       String memocontents = req.getParameter("memocontents-" + contnum);
	                       
	                       SCDTO mdto = new MemoDTO(scnum, 7, i+1, memocontents);
	                       
	                       if(sdao.insertMemo(mdto)) {
	                          System.out.println("??????");
	                       } else {
	                          System.out.println("??????...");
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
	                             System.out.println("??????..");
	                          }
//	                     
	                 
	                       }
	                       else if(category.equals("account")) {
//	                           row ????????? ?????? row??? ???????????? ??????????????? ???????????? ??? ????????? 3?????? ???????????? ?????????.. ????????? hidden??? ????????? ?????? cnt + row ????????? ???????????????
//	                           ??????????????? - ???????????? ?????? - ???????????? ?????? - ?????????date - enddate - memo - budget- ?????????(1,3,5,6,7)
//	                           ????????? ?????? - ???????????? ??????- ????????? - ?????? - ??????- ?????? -??????      
	                          String[] accRowCnts = req.getParameterValues("accRowCnt");
//	                           accRowCnts = {1-1,1-3,2-1}
//	                           ????????? ?????? contnum??? ?????? ????????? ???????????? ??? ex) {1-1,1-3}
	                          List<String> nums = new ArrayList<String>();
	                          for (int j = 0; j < accRowCnts.length; j++) {
	                             if(contnum.equals(accRowCnts[j].split("-")[0])) {
	                              nums.add(accRowCnts[j].split("-")[1]);
	                             }
	                          }
//	                            ?????? ?????? ?????? = num.size();
//	                           num.get(i)??? ??? ??????
//	                           1,3,7,9
	                          String startdate = req.getParameter("startdate-"+contnum);
	                          String enddate = req.getParameter("enddate-"+contnum);
	                          String budget = req.getParameter("budget-"+contnum);
	                          String accmemo = req.getParameter("accmemo-"+contnum);
	                          accmemo = accmemo.replace("\r\n","<br>");
	                         

	                          //?????????(1,3,5,6,7) ???????????? ??????????????????
	                          String [] accountRowNumAr= new String[nums.size()];
	                          String accountRowNums="";
	                          for (int k = 0; k < nums.size(); k++) {
	                             
	                             String history = req.getParameter("history-"+contnum+"-"+nums.get(k));
	                             String amount = req.getParameter("amount-"+contnum+"-"+nums.get(k));
	                             String acctype = req.getParameter("acctype-"+contnum+"-"+nums.get(k));
	                             String accdate = req.getParameter("accdate-"+contnum+"-"+nums.get(k));
	                             
//	                           ????????? ?????? - ???????????? ??????- ????????? - ?????? - ??????- ?????? -??????      
	                             SCDTO accountRow = new AccountRowDTO(nums.get(k), scnum, 1, i+1, history, amount, acctype, accdate);
	                             if(sdao.insertAccountRow(accountRow)) {
	                                System.out.println(accountRow);
	                                
	                             }else {}
	                             
	                             accountRowNumAr[k] =nums.get(k);
	                             accountRowNums =String.join(",",accountRowNumAr);

	                             
//	                           startaccterm, endaccterm DTO??? ????????? ?????????
//	                              AccountDTO account = new AccountDTO(2,7,i+1,startdate,enddate,budget,history,amount,acctype,accmemo,accdate);
//	                              System.out.println(account);
	                          }
//	                           ??????????????? - ???????????? ?????? - ???????????? ?????? - ?????????date - enddate - memo - budget- ?????????(1,3,5,6,7)
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
//	            ????????? account??? ?????? accountRow???
	            ArrayList<AccountRowDTO> ardto = new ArrayList<>();
	            //????????? accountrow??? ?????? ArrayList????????? ardto??? ???????????? ?????? ardto????????? accountrow??? ?????????
	            ardto.addAll(acrow);

	            List<AccountDTO> ac =sdao.getAccount(scnum);
//	            ?????? ???????????? 2?????? ???????????? ????????? ??????
	            int contnum [] = new int[ac.size()]; 
	            //contnum?????? ???????????? totalAccount??? ?????????????????? ????????? contnum[]??? ????????? ??????
	            //?????????????????? ???????????? ????????? ?????? ????????? ????????? ?????????
	            for (int i = 0; i < ac.size(); i++) {
	                  contnum[i]=ac.get(i).getContnum();
	            }
	            //account??????(2)
	            for (int i = 0; i < ac.size(); i++) {
	               TotalAccountDTO totalAccount = new TotalAccountDTO();
	               ArrayList<AccountRowDTO> realAccountRow = new ArrayList<AccountRowDTO>();
//	               accountrow??????(3)
	               for (int j = 0; j < ardto.size(); j++) {
	                  if(contnum[i]==ardto.get(j).getContnum()) {
//	                     totalAccountAr??? 0???????????? totalAccountDto??? setA,setB?????????
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
	            //???????????? ?????? ????????? ?????????????????? ?????? ????????? ??? 
	            MONGA [] monga = new MONGA[ar.size()];
//	            ?????? ?????? ????????????
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
	                  
//	                  -- ????????? ????????? ????????? ??????????????? (?????????????????? ?????? ?????? ??? ?????? ??????????????? monga??? ????????????)--
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
	                  //?????????
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
