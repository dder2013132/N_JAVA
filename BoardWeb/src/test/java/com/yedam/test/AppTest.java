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

		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);

			List<BoardVO> list = mapper.selectBoard(1);
			for (BoardVO brd : list) {
				System.out.println(brd.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
