package com.planit.totalfrontcontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planit.action.ActionTo;

public interface FrontController{
	public ActionTo flow(HttpServletRequest req, HttpServletResponse resp, String command);
}