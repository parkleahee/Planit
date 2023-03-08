package com.planit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class AccountDTO extends SCDTO {
	private String startdate;
	private String enddate;
 	private String budget;
    private String accmemo; 
    private String accountRowNums;
	
    public AccountDTO() {
	}
    
    public AccountDTO(int scnum, int catnum, int contnum, String startdate, String enddate, String budget,
			String accmemo, String accountRowNums) {
		super(scnum, catnum, contnum);
		this.startdate = startdate;
		this.enddate = enddate;
		this.budget = budget;
		this.accmemo = accmemo;
		this.accountRowNums = accountRowNums;
	}
    
    
}
