package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO {
	private String userid;
	private int timenum;
	private String timetitle;
	private String timestart;
	private String timeend;
	private String timecolor;
}
