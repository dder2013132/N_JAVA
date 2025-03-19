package com.yedam.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 1. ojdbc 데이터베이스 연결, 쿼리, 실행결과.
 * 2. Connection 객체(db session)
 * 3. Statement(PreparedStatement) 쿼리실행.
 * 4. ResultSet(조회), int(입력,수정,삭제)
 */
public class DaoExe {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userId = "scott";
		String userPw = "tiger";
		String sql = "select empno, ename, job, mgr, hiredate, sal\r\n"//
				+ "from emp order by empno desc";
		try {
			Connection conn = DriverManager.getConnection(url, userId, userPw);
			System.out.println("연결성공");
			conn.setAutoCommit(false); // autoCommit을 사용여부.
			Statement stmt = conn.createStatement();
			
			int r = stmt.executeUpdate("delete from emp where empno = 9999");
			if(r>0) {
				System.out.println("삭제성공.");
				conn.commit(); // 커밋처리.
			}
			
			ResultSet rs = stmt.executeQuery(sql); // 쿼리작성. -> 실행.
			while (rs.next()) {
				System.out.println(rs.getInt("empno") + "," + rs.getString("ename") + "," + rs.getString("job"));
			}
			System.out.println("-- end of data --");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
