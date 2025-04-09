package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import com.yedam.common.SearchDTO;
import com.yedam.vo.ReplyVO;

// 댓글목록, 등록, 삭제, 단건조회.
public interface ReplyMapper {
	List<ReplyVO> selectList(SearchDTO search); //댓글목록.
	int insertReply(ReplyVO rvo); //댓글등록.
	int deleteReply(int replyNo); //댓글삭제.
	ReplyVO selectReply(int replyNo); //단건조회.
	int selectReplyCnt(int boardNo);//댓글의 건수.
	// Datatable용도.
	List<Map<String, Object>> selectListForDT(int boardNo);
}
