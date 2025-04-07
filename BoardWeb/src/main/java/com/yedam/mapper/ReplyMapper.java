package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.ReplyVO;

public interface ReplyMapper {
    List<ReplyVO> selectList(int boardNo);
    int insertReply(ReplyVO rvo);
    int deleteReply(int replyNo);
    ReplyVO selectReply(int replyNo);
}
