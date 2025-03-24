package com.yedam.bookApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memberjdbc {
		Connection getConnect() {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String userId = "scott";
			String userPw = "tiger";
			
			try {
				Connection conn = DriverManager.getConnection(url, userId, userPw);
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	
	public boolean login(String id, String pw) {
		Connection conn = getConnect();
		String sql = "select * from tbl_member"
				+ "   where user_id = ?"
				+ "   and   password = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
		
	
	public List<Map<String, String>> memberList(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = getConnect();
		String sql = "select * from tbl_member";
		
		try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("userId", rs.getString("user_id"));
					map.put("password", rs.getString("password"));
					map.put("userName", rs.getString("user_name"));
					list.add(map);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return list;
	}
}
