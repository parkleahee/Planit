package com.planit.dao;

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

public interface ScheduleDAO {
   
   public List<ScheduleDTO> gettogetherList(String userid);
   
   public List<ScheduleDTO> getScheduleList(String newStart, String userid);

   public boolean deleteSchedule(int delscnum);

   public int insertScheduleDetail(ScheduleDTO schedule);

   public boolean insertAuthority(GroupDTO gdto);

   public List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate plusDays);

   public boolean insertDate(ScheduleDateDTO sddto);

   public boolean insertCulture(SCDTO cdto);
   public boolean insertMemo(SCDTO mdto);
   public boolean insertDiary(SCDTO diary);
   public boolean insertAccountRow(SCDTO accountRow);
   public boolean insertAccount(SCDTO account);
   public boolean insertMap(SCDTO map);
   public boolean insertFood(SCDTO food);

   public List<SetScBarDTO> getScBarList(String userid);
   
    public SetScBarDTO getScBar(int scnum);

   public List<MapDTO> getMap(int scnum);

   public List<MemoDTO> getMemo(int scnum);

   public List<DiaryDTO> getDiary(int scnum);

   public List<CultureDTO> getCulture(int scnum);

   public List<FoodDTO> getFood(int scnum);

   public List<AccountRowDTO> getAccountRow(int scnum);

   public List<AccountDTO> getAccount(int scnum);

   public String getScId(int scnum);
}