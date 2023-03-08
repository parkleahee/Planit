package com.planit.dao;

import java.util.List;

import com.planit.domain.ContactDTO;

public interface AdminDAO {

	int getContactCnt(String keyword);

	List<ContactDTO> getContactList(int startRow, int pageSize, String keyword);

	ContactDTO getDetail(int contactnum);

}
