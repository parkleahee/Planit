package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
	private String fromUser;
	private String toUser;
	private String contents;
	private int chatnum;
	private int chatroomnum;
	private String chatdate;
	private String chatname;
}
