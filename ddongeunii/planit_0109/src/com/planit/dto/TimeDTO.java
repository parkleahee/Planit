package com.planit.dto;

public class TimeDTO {
	private String userid;
	private int timenum;
	private String timetitle;
	private String timestart;
	private String timeend;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getTimenum() {
		return timenum;
	}
	
	public void setTimenum(int timenum) {
		this.timenum = timenum;
	}
	
	public String getTimetitle() {
		return timetitle;
	}
	public void setTimetitle(String timetitle) {
		this.timetitle = timetitle;
	}
	public String getTimestart() {
		return timestart;
	}
	public void setTimestart(String timestart) {
		this.timestart = timestart;
	}
	public String getTimeend() {
		return timeend;
	}
	public void setTimeend(String timeend) {
		this.timeend = timeend;
	}

}
