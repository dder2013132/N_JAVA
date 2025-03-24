package main.java.com.proj.util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
	public static Properties properties = new Properties();
		
		
		
	
	
	public static Connection getConnect() {
		Reader reader;
		try {
			reader = new FileReader("src/main/resources/db.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream("db.properties");
			properties.load(fis);
			Class.forName(properties.getProperty("dirver"));
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		String url = properties.getProperty("url");
		String userId = properties.getProperty("user");
		String userPwd = properties.getProperty("password");
		
		try {
			Connection conn = DriverManager.getConnection(url,userId,userPwd);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static void close(Connection conn, PreparedStatement psmt) {
		if(psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection conn, PreparedStatement psmt, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		close(conn,psmt);
	}
	
	public static void commit(Connection conn) {
		if(conn != null) {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public static void rollback(Connection conn) {
		if(conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
