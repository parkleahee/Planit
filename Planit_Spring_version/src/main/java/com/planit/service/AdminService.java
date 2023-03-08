package com.planit.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.planit.domain.ContactDTO;

public interface AdminService {

	HashMap<String, Object> contactList(HttpServletRequest req);

	ContactDTO contactDetail(int contactnum);

}
