package com.planit.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountRowDTO extends SCDTO{
    private String history; 
    private String amount;	
    private String acctype;  
    private String accdate; 
    private String accountRowNum;
	
    public AccountRowDTO() {
	}
    
    public AccountRowDTO(String accountRowNum,int scnum,int catnum, int contnum,String history,String amount, String acctype,
			String accdate) {
		super(scnum, catnum, contnum);
		this.history = history;
		this.amount = amount;
		this.acctype = acctype;
		this.accdate = accdate;
		this.accountRowNum = accountRowNum;
	}
    
    
}
