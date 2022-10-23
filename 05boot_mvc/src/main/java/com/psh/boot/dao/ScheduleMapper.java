package com.psh.boot.dao;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;

import com.psh.boot.dto.Schedule;

@Mapper
public interface ScheduleMapper {
	
	int insert(Schedule sch);
	List<Schedule> getSchedules(int mno);
	void delete(int idx);
	int checkMno(int idx);
}
