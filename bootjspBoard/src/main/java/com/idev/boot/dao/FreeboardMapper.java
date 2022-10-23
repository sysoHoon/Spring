package com.idev.boot.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.idev.boot.dto.Board;
import com.idev.boot.dto.PageDto;

@Repository
@Mapper
public interface FreeboardMapper { 
	//mapper로 dao 동작을 하도록 설정 -> spring mybatis 에서 구현체를 생성하여 bean 등록
	//sql 실행에 필요한 메소드 시그니쳐 정의
	List<Board> getPagelist(PageDto dto);	//한페이지 게시글
	int getCount();								//전체 게시글 카운트
	Board detail(int idx);				//한개 검색
	int insert(Board dto);					//insert
	int readcountUp(int idx);
	int update(Board dto);					//update
	int delete(int idx);						//delete
	int searchCount(PageDto dto);	//검색 카운트
	List<Board> searchList(PageDto dto);		//검색 게시글
	
}









