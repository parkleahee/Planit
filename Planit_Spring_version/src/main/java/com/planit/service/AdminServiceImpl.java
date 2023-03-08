package com.planit.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planit.dao.AdminDAO;
import com.planit.dao.AdminDAOImpl;
import com.planit.domain.ContactDTO;

import lombok.Setter;

@Service
public class AdminServiceImpl implements AdminService {
	@Setter(onMethod_ = @Autowired)
	AdminDAO adao = new AdminDAOImpl();
	
	
	@Override
	public HashMap<String, Object> contactList(HttpServletRequest req) {
		String temp = req.getParameter("page");
		String keyword = req.getParameter("q");
		
		int page = temp == null ? 1 : Integer.parseInt(temp);
		
		//한 페이지에서 보여줄 게시글의 개수
		int pageSize = 20;
		
		//전체 게시글의 개수
		int totalCnt = adao.getContactCnt(keyword);
		
		//아래쪽 페이징 처리 부분에 보여지는 첫번째 페이지 번호
		int startPage = ((page - 1)/5)*5 + 1;
		
		//아래쪽 페이징 처리 부분에 보여지는 마지막 페이지 번호(연산으로 구해진것)
		int endPage = startPage + 4;
		
		//전체 개수를 기반으로 한 가장 마지막 페이지 번호
		int totalPage = (totalCnt - 1)/pageSize + 1;
		
		//허구의 페이지가 있는 경우 totalPage로 바꿔주기
		endPage = endPage > totalPage ? totalPage : endPage;
		
		int startRow = (page - 1) * pageSize;
		
		List<ContactDTO> contactList = adao.getContactList(startRow,pageSize,keyword);
		
		HashMap <String, Object> contactViewList = new HashMap<String, Object>();
	    contactViewList.put("contactList", contactList);
	    contactViewList.put("totalPage", totalPage);
	    contactViewList.put("totalCnt", totalCnt);
	    contactViewList.put("page", page);
	    contactViewList.put("startPage", startPage);
	    contactViewList.put("endPage", endPage);
	    contactViewList.put("keyword", keyword);
	    
		return contactViewList;
	}


	@Override
	public ContactDTO contactDetail(int contactnum) {
		ContactDTO contact = adao.getDetail(contactnum);
		
		return contact;
	}

}
