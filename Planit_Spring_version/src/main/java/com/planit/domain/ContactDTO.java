package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
	
	private String userid;
	private int contactnum;
	private String contacttitle;
    private String contactcontents;
    private String contactdate;
}
