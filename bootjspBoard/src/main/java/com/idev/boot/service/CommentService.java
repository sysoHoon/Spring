package com.idev.boot.service;

import java.util.List;

import com.idev.boot.dto.Comments;



public interface CommentService {
	int insert(Comments dto); //댓글 입력
	List<Comments> commentList(int mref); //게시글 전체 댓글
	int update(int idx,String content); //댓글 수정
	int delete(int idx); //댓글 삭제
	int updateCommentCnt(int mref); //프리보드 테이블에 댓글개수 컬럼 업데이트
}
