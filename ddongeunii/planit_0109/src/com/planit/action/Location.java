package com.planit.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class Location {
	public String LocationPath(HttpServletRequest req, String pagepath, HashMap<String, String> datas) {
	      String path = "'"+req.getContextPath()+pagepath +"?";
	      int cnt = 1;
	      for (String key : datas.keySet()) {
	         if (cnt<datas.size()) {            
	            path += key+"="+datas.get(key)+"&";
	            cnt++;
	         }else {
	            path += key+"="+datas.get(key)+"'";            
	         }
	      }
	      return path;
	   }
	public String fowardPath(HttpServletRequest req, String pagepath) {
	      String path = ""+req.getContextPath()+pagepath;
	      return path;
	   }
}
