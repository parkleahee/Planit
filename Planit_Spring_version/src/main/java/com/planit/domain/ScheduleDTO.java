package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
	private int scnum;
	private String scheduletitle;
	private String schedulecolor;
    private String userid;
    private String scheduledate;
    private boolean tempsave;
     
}
