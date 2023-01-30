package com.planit.dto;

public class ContactDTO {
	private String userid;
	private int contactnum;
	private String contacttitle;
    private String contactcontents;
    private String contactdate;
    
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getContactnum() {
		return contactnum;
	}
	public void setContactnum(int contactnum) {
		this.contactnum = contactnum;
	}
	public String getContacttitle() {
		return contacttitle;
	}
	public void setContacttitle(String contacttitle) {
		this.contacttitle = contacttitle;
	}
	public String getContactcontents() {
		return contactcontents;
	}
	public void setContactcontents(String contactcontents) {
		this.contactcontents = contactcontents;
	}
	public String getContactdate() {
		return contactdate;
	}
	public void setContactdate(String contactdate) {
		this.contactdate = contactdate;
	}
	

}
