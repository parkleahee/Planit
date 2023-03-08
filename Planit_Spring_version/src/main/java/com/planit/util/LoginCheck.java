package com.planit.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheck {

		public static boolean logincheck(HttpServletRequest req) {
			HttpSession session = req.getSession();
			return session.getAttribute("loginUser")==null?true:false;
		}
}
