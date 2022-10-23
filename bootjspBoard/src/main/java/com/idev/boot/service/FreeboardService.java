package com.idev.boot.service;

import java.util.List;
import java.util.Map;

import com.idev.boot.dto.Board;
import com.idev.boot.dto.PageDto;



public interface FreeboardService {
	//서비스 인터페이스에서는 dao의 메소드들을 구현하고 session,request등을 제어하는 메소드 설계 작성
	int insert(Board dto);
	int getCount();
	List<Board> getPagelist(PageDto dto);
	int delete(int idx);
	Board getBoardOne(int idx);
	int update(Board dto);
	void updateReadCnt(int idx);
	int searchCount(PageDto dto);
	List<Board> searchList(PageDto dto);
	
	
	
	
	
	
}
