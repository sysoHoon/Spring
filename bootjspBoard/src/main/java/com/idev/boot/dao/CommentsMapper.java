package com.idev.boot.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.idev.boot.dto.Comments;

@Repository		//dao 역할 bean
@Mapper
public interface CommentsMapper {
	List<Comments> getComments(int mref);
	int insert(Comments dto);
	int update(Map<String,Object> map);
	int delete(int idx);
	int updateCmtCount(int idx);   //freeboard 테이블 댓글갯수 증가 update
}
