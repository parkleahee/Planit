package com.planit.mapper;

import java.time.LocalDate;
import java.util.List;

import com.planit.domain.AccountDTO;
import com.planit.domain.AccountRowDTO;
import com.planit.domain.CultureDTO;
import com.planit.domain.DiaryDTO;
import com.planit.domain.FoodDTO;
import com.planit.domain.GroupDTO;
import com.planit.domain.MapDTO;
import com.planit.domain.MemoDTO;
import com.planit.domain.SCDTO;
import com.planit.domain.ScheduleDTO;
import com.planit.domain.ScheduleDateDTO;
import com.planit.domain.SetScBarDTO;

public interface ScheduleMapper {
   
   public List<ScheduleDTO> getScheduleList();

   public boolean deleteSchedule();

   public int insertScheduleDetail();

   public boolean insertAuthority();

   public List<LocalDate> getDatesBetweenTwoDates();

   public boolean insertDate();

   public boolean insertCulture();
   public boolean insertMemo();
   public boolean insertDiary();
   public boolean insertAccountRow();
   public boolean insertAccount();
   public boolean insertMap();
   public boolean insertFood();

   public List<SetScBarDTO> getScBarList();
   
   public SetScBarDTO getScBar();

   public List<MapDTO> getMap();

   public List<MemoDTO> getMemo();
   
   public List<DiaryDTO> getDiary();
   
   public List<CultureDTO> getCulture();
   
   public List<FoodDTO> getFood();
   
   public List<AccountRowDTO> getAccountRow();
   
   public List<AccountDTO> getAccount();
   
   public String gettogetherList(); 
   
   public String getScId();
}