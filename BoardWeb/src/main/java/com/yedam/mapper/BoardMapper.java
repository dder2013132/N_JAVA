package com.yedam.mapper;

import java.util.List;

import com.yedam.common.SearchDTO;
import com.yedam.vo.BoardVO;

public interface BoardMapper {
    List<BoardVO> selectBoard(SearchDTO searchDTO);
    
    int insertBoard(BoardVO board);

    int updateBoard(BoardVO board);
    
    int deleteBoard(int boardNo);
    
    // 상세조회
    BoardVO selectOne(int boardNo);
    
    int selectTotal(SearchDTO searchDTO); // 전체 건수 조회
}
