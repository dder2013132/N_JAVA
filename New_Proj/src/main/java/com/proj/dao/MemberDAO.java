package main.java.com.proj.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.java.com.proj.util.DatabaseUtil;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
		
	public boolean register(String member_id, String password, String user_name, String email, String phone, String address) {
		conn = DatabaseUtil.getConnect();
		String query = "insert into tbl_member (member_id,password,user_name) "
				   + "values                 (?,?,?,?,?,?,SYSDATE) ";   
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, member_id);
			psmt.setString(2, password);
			psmt.setString(3, user_name);
			psmt.setString(4, email);
			psmt.setString(5, phone);
			psmt.setString(6, address);
			
			int r = psmt.executeUpdate();
            if (r > 0) return true; // 등록 성공
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean login(String id, String pw) {
		conn = DatabaseUtil.getConnect();
		String sql = "select * from tbl_member "
				+ "   where member_id = ? "
				+ "   and   password = ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery();
			updateLastLoginDate(conn, id);
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
		conn = DatabaseUtil.getConnect();
		String sql = "select * "
				+ "from tbl_member ";
		
		try {
				psmt = conn.prepareStatement(sql);
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
	
	public void updateLastLoginDate(Connection conn, String memberId) {
		psmt = null;
		String sql = "update tbl_member "
				+ "set last_login_date = SYSDATE "
				+ "where member_id = ? ";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, memberId);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null ) psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
