package com.planit.mapper;

import java.util.List;

import com.planit.domain.ContactDTO;

public interface AdminMapper {
	int getContactCnt();
	List<ContactDTO> getContactList();
	ContactDTO getDetail();
}
