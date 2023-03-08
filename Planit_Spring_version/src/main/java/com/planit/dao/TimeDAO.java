package com.planit.dao;

import java.util.List;

import com.planit.domain.TimeDTO;

public interface TimeDAO {

	List<TimeDTO> getDetail(String userid);

	boolean addTime(TimeDTO tdto);

	boolean deleteTime(int timenum);

}
