package com.planit.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

import lombok.Setter;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO{
    @Setter(onMethod_ = @Autowired)
    private SqlSession sqlSession;
    
    private static String namespace = "com.planit.mapper.ScheduleMapper.";
   
    public List<ScheduleDTO> getScheduleList(String newStart, String userid) {
        List<ScheduleDTO> result = null;
        HashMap<String, String> datas = new HashMap<String, String>();
        datas.put("scdate", newStart);
        datas.put("userid", userid);
        result = sqlSession.selectList(namespace+"getScheduleList", datas);
        return result;

  }

@Override
public boolean deleteSchedule(int scnum) {
   return sqlSession.delete(namespace+"deleteSchedule", scnum) == 1;
}

@Override
public int insertScheduleDetail(ScheduleDTO schedule) {
  sqlSession.insert(namespace+"insertScheduleDetail", schedule);
   return schedule.getScnum();
}

@Override
public boolean insertAuthority(GroupDTO gdto) {
  return sqlSession.insert(namespace+"insertAuthority", gdto) == 1;
}

@Override
public List<LocalDate> getDatesBetweenTwoDates(LocalDate startDate, LocalDate plusDays) {
   return startDate.datesUntil(plusDays)
             .collect(Collectors.toList());
}

@Override
public boolean insertDate(ScheduleDateDTO sddto) {
   return sqlSession.insert(namespace+"insertScheduleDate", sddto) == 1;
  
}
@Override
public boolean insertCulture(SCDTO cdto) {
  return sqlSession.insert(namespace+"insertCulture", cdto) == 1;
}
@Override
public boolean insertMemo(SCDTO mdto) {
  return sqlSession.insert(namespace+"insertMemo", mdto) == 1;
}
@Override
public boolean insertDiary(SCDTO diary) {
  return sqlSession.insert(namespace+"insertDiary",diary)==1;
}
@Override
public boolean insertAccountRow(SCDTO accountRow) {
  return sqlSession.insert(namespace+"insertAccountRow",accountRow)==1;
}
@Override
public boolean insertAccount(SCDTO account) {
  return sqlSession.insert(namespace+"insertAccount",account)==1;
}
@Override
public boolean insertMap(SCDTO map) {
  return sqlSession.insert(namespace+"insertMap",map)==1;
}
@Override
public boolean insertFood(SCDTO food) {
 return sqlSession.insert(namespace+"insertFood",food)==1;
}

@Override
public List<SetScBarDTO> getScBarList(String userid) {
 return sqlSession.selectList(namespace+"getScBarList",userid);
}

@Override
public SetScBarDTO getScBar(int scnum) {
   return sqlSession.selectOne(namespace + "getScBar",scnum);
}

@Override
public List<MapDTO> getMap(int scnum) {
  return sqlSession.selectList(namespace + "getMap",scnum);
}

@Override
public List<MemoDTO> getMemo(int scnum) {
  return sqlSession.selectList(namespace + "getMemo",scnum);
}

@Override
public List<DiaryDTO> getDiary(int scnum) {
  return sqlSession.selectList(namespace + "getDiary",scnum);
}

@Override
public List<CultureDTO> getCulture(int scnum) {
  return sqlSession.selectList(namespace + "getCulture",scnum);
}

@Override
public List<FoodDTO> getFood(int scnum) {
  return sqlSession.selectList(namespace + "getFood",scnum);
}

@Override
public List<AccountRowDTO> getAccountRow(int scnum) {
  return sqlSession.selectList(namespace + "getAccountRow",scnum);
}

@Override
public List<AccountDTO> getAccount(int scnum) {
  return sqlSession.selectList(namespace + "getAccount",scnum);
}

    
   @Override
   public List<ScheduleDTO> gettogetherList(String userid) {
        List<ScheduleDTO> result = null;      
         result = sqlSession.selectList(namespace + "gettogetherList",userid);            
         return result;
   }

@Override
public String getScId(int scnum) {
	return sqlSession.selectOne(namespace+"getScId",scnum);
}

}


