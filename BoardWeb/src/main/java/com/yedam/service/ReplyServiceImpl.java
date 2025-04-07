package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class ReplyServiceImpl implements ReplyService {
    
    SqlSession sqlSession = DataSource.getInstance().openSession(true);
    ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);

    @Override
    public List<ReplyVO> replyList(int boardNo) {
        return mapper.selectList(boardNo);
    }

    @Override
    public boolean addReply(ReplyVO rvo) {
        return mapper.insertReply(rvo) > 0;
    }

    @Override
    public boolean removeReply(int replyNo) {
        return mapper.deleteReply(replyNo) > 0;
    }

    @Override
    public ReplyVO getReply(int replyNo) {
        return mapper.selectReply(replyNo);
    }
    
}
