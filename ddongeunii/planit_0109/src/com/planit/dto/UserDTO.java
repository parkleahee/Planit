package com.planit.dto;

import java.util.Date;
import java.text.SimpleDateFormat;

public class UserDTO {
	private String userid;
    private String userpw;
    private String username;
    private String gender;
    private String userdob;
    private String useremail;
    private String userphone;
	private String zipcode;
    private String addr;
    private String addrdetail;
    private String addretc;
    public UserDTO() {
	}
	public UserDTO(String userid, String userpw, String username, String gender, String userdob, String userphone,
			String useremail, String zipcode, String addr, String addrdetail, String addretc) {
		super();
		this.userid = userid;
		this.userpw = userpw;
		this.username = username;
		this.gender = gender;
		this.userdob = userdob;
		this.userphone = userphone;
		this.useremail = useremail;
		this.zipcode = zipcode;
		this.addr = addr;
		this.addrdetail = addrdetail;
		this.addretc = addretc;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
//	public String getUserdob() throws Exception {
	public String getUserdob(){	
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		//월은 대문자 확인 !!!!!!!
//		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy년 MM월 dd일");
//		Date date = simpleDateFormat.parse(userdob);
//		String birth = simpleDateFormat2.format(date);
//		System.out.println(date);
		
		//return birth;
		return userdob;
	}
	public void setUserdob(String userdob) {
		this.userdob = userdob;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddrdetail() {
		return addrdetail;
	}
	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}
	public String getAddretc() {
		return addretc;
	}
	public void setAddretc(String addretc) {
		this.addretc = addretc;
	}
	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", gender=" + gender
				+ ", userdob=" + userdob + ", userphone=" + userphone + ", useremail=" + useremail + ", zipcode="
				+ zipcode + ", addr=" + addr + ", addrdetail=" + addrdetail + ", addretc=" + addretc + "]";
	}
    
    }
