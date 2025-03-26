package dao;

import model.Member;
import java.sql.*;

/**
 * 회원 정보를 데이터베이스와 연동하는 DAO 클래스
 */
public class MemberDAO {
    
    // 로그인
    public Member login(String memberId, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member member = null;
        
        try {
            conn = DBConnection.getConnection();
            
            // 로그인 쿼리
            String sql = "SELECT * FROM tbl_member WHERE member_id = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setString(2, password);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setPassword(rs.getString("password"));
                member.setUserName(rs.getString("user_name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoinDate(rs.getDate("join_date"));
                member.setLastLoginDate(rs.getDate("last_login_date"));
                
                // 로그인 시간 업데이트
                updateLastLoginDate(memberId);
            }
            
        } catch (SQLException e) {
            System.out.println("로그인 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return member;
    }
    
    // 회원가입
    public boolean register(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            // 회원가입 쿼리
            String sql = "INSERT INTO tbl_member (member_id, password, user_name, email, phone, address) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getUserName());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getPhone());
            pstmt.setString(6, member.getAddress());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("회원가입 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    // 아이디 중복 확인
    public boolean isIdDuplicated(String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isDuplicated = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT member_id FROM tbl_member WHERE member_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                isDuplicated = true;
            }
            
        } catch (SQLException e) {
            System.out.println("아이디 중복 확인 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return isDuplicated;
    }
    
    // 이메일 중복 확인
    public boolean isEmailDuplicated(String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isDuplicated = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT email FROM tbl_member WHERE email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                isDuplicated = true;
            }
            
        } catch (SQLException e) {
            System.out.println("이메일 중복 확인 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return isDuplicated;
    }
    
    // 사용자 정보 조회
    public Member getMemberById(String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Member member = null;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT * FROM tbl_member WHERE member_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setPassword(rs.getString("password"));
                member.setUserName(rs.getString("user_name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoinDate(rs.getDate("join_date"));
                member.setLastLoginDate(rs.getDate("last_login_date"));
            }
            
        } catch (SQLException e) {
            System.out.println("회원 정보 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return member;
    }
    
    // 마지막 로그인 시간 업데이트
    private void updateLastLoginDate(String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "UPDATE tbl_member SET last_login_date = SYSDATE WHERE member_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            
            pstmt.executeUpdate();
            DBConnection.commit();
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("로그인 시간 업데이트 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
    }
}