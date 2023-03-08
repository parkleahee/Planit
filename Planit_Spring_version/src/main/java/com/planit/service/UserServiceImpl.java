package com.planit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planit.dao.GoalDAO;
import com.planit.dao.GoalDAOImpl;
import com.planit.dao.UserDAO;
import com.planit.dao.UserDAOImpl;
import com.planit.domain.ContactDTO;
import com.planit.domain.GoalDTO;
import com.planit.domain.UserDTO;

import lombok.Setter;

@Service
public class UserServiceImpl implements UserService {

	@Setter(onMethod_ = @Autowired)
	UserDAO udao = new UserDAOImpl();

	@Setter(onMethod_ = @Autowired)
	GoalDAO gdao = new GoalDAOImpl();

	@Override
	public UserDTO userLogin(String userid, String userpw, HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
//		userid = req.getParameter("userid");
//		userpw = req.getParameter("userpw");
		UserDTO loginUser = udao.userLogin(userid, userpw);

		if (loginUser != null) {
			session.setAttribute("loginUser", loginUser);
//         ChatServer.getNowloginUser().add(loginUser.getUserid());
			session.setAttribute("goal1", null);
			session.setAttribute("goal2", null);
			gdao.goalNow(userid).forEach((value) -> {
				int goalnum = value.getGoalnum();
				int goalcnt = gdao.goalCnt(goalnum);
				value.setGoalcnt(goalcnt);
				// db에 오늘 날짜가 있는지 없는지 확인
				if (gdao.getCheckGoal(goalnum) == 1) {
					value.setGoalcheck("f");
				} else if (gdao.getCheckGoal(goalnum) == 0) {
					value.setGoalcheck("t");
				}
				if (session.getAttribute("goal1") == null) {
					session.setAttribute("goal1", value);
				} else if(session.getAttribute("goal2") == null) {
					session.setAttribute("goal2", value);
				}
			});
			return loginUser;
		} else {
			return null;
		}
	}

	@Override
	public void logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (req != null) {
			session.invalidate();
		}
	}

	@Override
	public List<GoalDTO> goalList(HttpServletRequest req) {

		HttpSession session = req.getSession();
		String userid = ((UserDTO) session.getAttribute("loginUser")).getUserid();

		List<GoalDTO> goalList = gdao.getGoalList(userid);

		return goalList;
	}

	@Override
	public boolean withdrawOk(String userid, String userpw, HttpServletRequest req) {

		HttpSession session = req.getSession();
		if (udao.deleteInfo(userid, userpw)) {
			session.invalidate();
		} else {
		}
		return true;
	}

	@Override
	public boolean deleteGoal(int goalnum) {
		if (gdao.deleteGoal(goalnum)) {
		} else {
		}
		return true;
	}

	@Override
	public boolean modifyInfo(UserDTO loginUser, HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		
		String userid = ((UserDTO) session.getAttribute("loginUser")).getUserid();
		String username = ((UserDTO) session.getAttribute("loginUser")).getUsername();
		String userdob = ((UserDTO) session.getAttribute("loginUser")).getUserdob();
		String useremail = req.getParameter("useremail");
	    String userpw = req.getParameter("password");
	    String userphone = req.getParameter("userphone");;
		String zipcode= req.getParameter("zipcode");
	    String addr = req.getParameter("addr");
	    String addrdetail = req.getParameter("addrdetail");
	    String addretc = req.getParameter("addretc");
	    System.out.println(userphone);
	    
	    loginUser.setUserid(userid);
	    loginUser.setUserpw(userpw);
	    loginUser.setUsername(username);
	    loginUser.setUserdob(userdob);
	    loginUser.setUseremail(useremail);
	    loginUser.setUserphone(userphone);
	    loginUser.setZipcode(zipcode);
	    loginUser.setAddr(addr);
	    loginUser.setAddrdetail(addrdetail);
	    loginUser.setAddretc(addretc);
	    
	    if(udao.modifyInfo(loginUser)) {
	    	session.setAttribute("loginUser",loginUser);
	    	return true;
	    }else {
	    	return false;
	    }
	}

	@Override
	public boolean addcontact(String contacttitle, String contactcontents, HttpServletRequest req) {
		ContactDTO cdto = new ContactDTO();
		
		HttpSession session = req.getSession();
		String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
		
		cdto.setUserid(userid);
		cdto.setContacttitle(contacttitle);
		cdto.setContactcontents(contactcontents);
		System.out.println(contacttitle);
		System.out.println(contactcontents);

		if (udao.addcontact(cdto)) {
			return true;
		} else {
			return false;
		}
	}

	 @Override
		public boolean checkid(String userid) {
			return udao.checkid(userid);
		}
	   @Override
		public boolean joinok(UserDTO udto) {
			return udao.joinok(udto);
		}
	   
	   @Override
		public String findid(String phone) {
			String userid = udao.findid(phone);
			return userid==null||userid.equals("")?"":userid;
		}
	   
	   @Override
		public UserDTO findpw(String userid) {
		   UserDTO userpw = udao.findpw(userid);
		   UserDTO notfound = new UserDTO();
		   notfound.setUseremail("해당하는 아이디를 찾을 수 없습니다");
			return userpw==null||userpw.equals("")?notfound:userpw;
		}
	   
	   @Override
	   public List<UserDTO> getFriendList(String keyword, HttpServletRequest req) {
	      HttpSession session = req.getSession();
	      String userid = ((UserDTO)session.getAttribute("loginUser")).getUserid();
	      keyword = req.getParameter("q");
	      String loginUser = ((UserDTO)session.getAttribute("loginUser")).getUserid();
	      
	      List<UserDTO> friendList = udao.getFriendList(keyword,loginUser);
	      
//	      req.setAttribute("friendList", friendList);
//	      req.setAttribute("keyword", keyword);
	      
	      return friendList;
	   }
}