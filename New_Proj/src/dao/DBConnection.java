package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 데이터베이스 연결을 관리하는 클래스
 */
public class DBConnection {
    // 데이터베이스 연결 정보
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";
    
    private static Connection conn = null;
    
    // 싱글톤 패턴 적용
    private DBConnection() {}
    
    /**
     * 데이터베이스 연결을 얻는 메소드
     * @return Connection 객체
     */
    public static Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                // Oracle JDBC 드라이버 로드
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                conn.setAutoCommit(false);
            }
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾을 수 없습니다: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 오류: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * 데이터베이스 연결을 닫는 메소드
     */
    public static void closeConnection() {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("데이터베이스 연결 종료 오류: " + e.getMessage());
            }
        }
    }
    
    /**
     * 트랜잭션 커밋 메소드
     */
    public static void commit() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.commit();
            }
        } catch (SQLException e) {
            System.out.println("커밋 오류: " + e.getMessage());
        }
    }
    
    /**
     * 트랜잭션 롤백 메소드
     */
    public static void rollback() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("롤백 오류: " + e.getMessage());
        }
    }
}