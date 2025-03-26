package dao;

import model.Board;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 게시판 정보를 데이터베이스와 연동하는 DAO 클래스
 */
public class BoardDAO {
    
    // 게시글 등록
    public boolean insertBoard(Board board) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "INSERT INTO tbl_board (board_id, title, content, member_id, board_type) " +
                         "VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getMemberId());
            pstmt.setString(4, board.getBoardType());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("게시글 등록 오류: " + e.getMessage());
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
    
    // 게시글 목록 조회
    public List<Board> getBoardList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Board> boardList = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            
            // 게시글과 작성자 정보를 함께 조회
            String sql = "SELECT b.*, m.user_name " +
                         "FROM tbl_board b " +
                         "JOIN tbl_member m ON b.member_id = m.member_id " +
                         "ORDER BY CASE WHEN b.board_type = '공지' THEN 0 ELSE 1 END, b.board_id DESC";
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Board board = new Board();
                board.setBoardId(rs.getInt("board_id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setMemberId(rs.getString("member_id"));
                board.setViewCount(rs.getInt("view_count"));
                board.setBoardType(rs.getString("board_type"));
                board.setRegDate(rs.getDate("reg_date"));
                board.setUserName(rs.getString("user_name"));
                
                boardList.add(board);
            }
            
        } catch (SQLException e) {
            System.out.println("게시글 목록 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return boardList;
    }
    
    // 게시글 상세 조회
    public Board getBoardById(int boardId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Board board = null;
        
        try {
            conn = DBConnection.getConnection();
            
            // 조회수 증가
            updateViewCount(boardId);
            
            // 게시글과 작성자 정보를 함께 조회
            String sql = "SELECT b.*, m.user_name " +
                         "FROM tbl_board b " +
                         "JOIN tbl_member m ON b.member_id = m.member_id " +
                         "WHERE b.board_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                board = new Board();
                board.setBoardId(rs.getInt("board_id"));
                board.setTitle(rs.getString("title"));
                board.setContent(rs.getString("content"));
                board.setMemberId(rs.getString("member_id"));
                board.setViewCount(rs.getInt("view_count"));
                board.setBoardType(rs.getString("board_type"));
                board.setRegDate(rs.getDate("reg_date"));
                board.setUserName(rs.getString("user_name"));
            }
            
        } catch (SQLException e) {
            System.out.println("게시글 상세 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return board;
    }
    
    // 게시글 수정
    public boolean updateBoard(Board board) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "UPDATE tbl_board SET title = ?, content = ?, board_type = ? WHERE board_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContent());
            pstmt.setString(3, board.getBoardType());
            pstmt.setInt(4, board.getBoardId());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("게시글 수정 오류: " + e.getMessage());
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
    
    // 게시글 삭제
    public boolean deleteBoard(int boardId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            // 먼저 해당 게시글의 댓글을 모두 삭제
            String deleteCommentsSql = "DELETE FROM tbl_comment WHERE board_id = ?";
            pstmt = conn.prepareStatement(deleteCommentsSql);
            pstmt.setInt(1, boardId);
            pstmt.executeUpdate();
            
            // 그 다음 게시글 삭제
            String deleteBoardSql = "DELETE FROM tbl_board WHERE board_id = ?";
            pstmt = conn.prepareStatement(deleteBoardSql);
            pstmt.setInt(1, boardId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("게시글 삭제 오류: " + e.getMessage());
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
    
    // 조회수 증가
    private void updateViewCount(int boardId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "UPDATE tbl_board SET view_count = view_count + 1 WHERE board_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            
            pstmt.executeUpdate();
            DBConnection.commit();
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("조회수 증가 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
    }
    
    public void createBoardSequence() {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBConnection.getConnection();
            stmt = conn.createStatement();
            
            // 시퀀스가 이미 존재하는지 확인
            try {
                // 기존 시퀀스가 있다면 삭제
                stmt.executeUpdate("DROP SEQUENCE BOARD_SEQ");
            } catch (SQLException e) {
                // 시퀀스가 없으면 무시 (처음 실행 시)
            }
            
            // 현재 테이블의 최대 ID 값 조회
            int startValue = 1; // 기본 시작값
            String maxIdSql = "SELECT NVL(MAX(board_id), 0) + 1 FROM tbl_board";
            pstmt = conn.prepareStatement(maxIdSql);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                startValue = rs.getInt(1);
            }
            
            // 최대값+1에서 시작하는 새 시퀀스 생성
            String createSeqSql = "CREATE SEQUENCE BOARD_SEQ START WITH " + startValue + 
                                  " INCREMENT BY 1 NOCACHE NOCYCLE";
            stmt.executeUpdate(createSeqSql);
            
            DBConnection.commit();
            System.out.println("BOARD_SEQ 시퀀스가 " + startValue + "에서 시작하도록 설정되었습니다.");
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("게시판 시퀀스 생성 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (stmt != null) stmt.close();
                DBConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
    }
}