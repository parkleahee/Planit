package com.planit.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDTO {
   private int scnum;
   private String userid;
   private String authority;
   private String friends;
   private String friendname;
   
}