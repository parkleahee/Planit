package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String userid;
    private String userpw;
    private String username;
    private String gender;
    private String userdob;
    private String useremail;
    private String userphone;
	private String zipcode;
    private String addr;
    private String addrdetail;
    private String addretc;
    }
