package com.yedam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.BoardVO;

public class BoardDAO extends DAO {

	public List<BoardVO> boardList() {
		List<BoardVO> list = new ArrayList<>();
		Connection conn = getConnect();
		try {
			PreparedStatement psmt //
					= conn.prepareStatement("select * from tbl_board");
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setContent(rs.getString("content"));
				board.setTitle(rs.getString("title"));
				board.setWriteDate(rs.getDate("write_date"));
				board.setWriter(rs.getString("writer"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
