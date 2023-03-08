package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetScBarDTO {
	private int scnum;
	private String title;
	private String color;
	private String startdate;
	private String enddate;
	
}
