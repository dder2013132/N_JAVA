package com.yedam.mapper;

import java.util.List;

import com.yedam.vo.BoardVO;

/*
 * 인터페이스(메소드정의) - 구현은 구현클래스를 통해
 */
public interface BoardMapper {
	//목록
	List<BoardVO> selectBoard();
	//등록
	int insertBoard(BoardVO board);
	//수정
	int updateBoard(BoardVO board);
	//삭제
	int deleteBoard(int boardNo);
}
