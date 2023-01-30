package com.planit.dto;

public class TodoDTO {
	private String userid;
	private int todonum;
	private String todocheck;
	private String todoimport;
	private String todocontents;
	
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public int getTodonum() {
		return todonum;
	}
	
	public void setTodonum(int todonum) {
		this.todonum = todonum;
	}
	
	public String getTodocheck() {
		return todocheck;
	}
	
	public void setTodocheck(String todocheck) {
		this.todocheck = todocheck;
	}
	
	public String getTodoimport() {
		return todoimport;
	}

	public void setTodoimport(String todoimport) {
		this.todoimport = todoimport;
	}

	public String getTodocontents() {
		return todocontents;
	}
	
	public void setTodocontents(String todocontents) {
		this.todocontents = todocontents;
	}
	
	
}
