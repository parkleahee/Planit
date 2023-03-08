package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendDTO {
	private String userid; // apple
	private String friendid; // banana
	private String username;
	private String friendname;
	private boolean reqchk;
	private int blockchk;
	
}
