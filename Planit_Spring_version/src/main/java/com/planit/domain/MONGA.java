package com.planit.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MONGA {
    private AccountDTO account;
    private ArrayList<AccountRowDTO> accountRow;
    private String accBalance;
    private DiaryDTO diary;
    private MemoDTO memo;
    private CultureDTO culture;
    private MapDTO map;
    private FoodDTO food;
    
   

   public MONGA() {
      // TODO Auto-generated constructor stub
   }
   
}