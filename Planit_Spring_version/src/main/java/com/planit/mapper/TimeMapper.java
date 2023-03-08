package com.planit.mapper;

import java.util.List;

import com.planit.domain.TimeDTO;

public interface TimeMapper {
	public List<TimeDTO> getDetail();
	public boolean addTime();
	public boolean deleteTime();
}
