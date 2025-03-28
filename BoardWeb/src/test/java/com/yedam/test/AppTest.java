package com.yedam.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class AppTest {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = DataSource.getInstance();
		BoardVO board = new BoardVO();
		board.setTitle("매퍼테스트2");
		board.setContent("매퍼를 활용한 입력테스트");
		board.setWriter("newbie");
//		board.setBoardNo(5);
		
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			
			int r = mapper.insertBoard(board);
			if(r==1) {
				System.out.println("등록 성공");
				sqlSession.commit();
			} else {
				System.out.println("등록 실패");
			}
			List<BoardVO> list = mapper.selectBoard();
			for (BoardVO brd : list) {
				System.out.println(brd.toString());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
//		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
//			int r = sqlSession.update("com.yedam.mapper.BoardMapper.updateBoard", board);
//			if(r==1) {
//				System.out.println("수정 성공");
//				sqlSession.commit();
//			} else {
//				System.out.println("수정 실패");
//			}
//			List<BoardVO> list = sqlSession.selectList("com.yedam.mapper.BoardMapper.selectBoard");
//			for (BoardVO brd : list) {
//				System.out.println(brd.toString());
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
//		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
//			int r = sqlSession.delete("com.yedam.mapper.BoardMapper.deleteBoard", board);
//			if(r==1) {
//				System.out.println("삭제 성공");
//				sqlSession.commit();
//			} else {
//				System.out.println("삭제 실패");
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
}
