package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
	private String userid;
	private int todonum;
	private String todocheck;
	private String todoimport;
	private String todocontents;
}
