package com.planit.dto;

public class ChatDTO {
	private String fromUser;
	private String toUser;
	private String contents;
	private int chatnum;
	private int chatroomnum;
	private int chatdate;
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getChatnum() {
		return chatnum;
	}
	public void setChatnum(int chatnum) {
		this.chatnum = chatnum;
	}
	public int getChatroomnum() {
		return chatroomnum;
	}
	public void setChatroomnum(int chatroomnum) {
		this.chatroomnum = chatroomnum;
	}
	public int getChatdate() {
		return chatdate;
	}
	public void setChatdate(int chatdate) {
		this.chatdate = chatdate;
	}
	
	// 채팅번호 채팅 내용 시간 채팅방번호
	//; 채팅방 번호 채팅번호 프롬 투
	// 채팅방 
	
	
	
}
