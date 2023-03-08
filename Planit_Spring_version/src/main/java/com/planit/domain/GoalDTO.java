package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoalDTO {
	
	private String userid;
	private String term;
	private String goal;
	private int goalnum;
	private String goaldate;
	private String goalcheck;
	private int goalcnt;

}
