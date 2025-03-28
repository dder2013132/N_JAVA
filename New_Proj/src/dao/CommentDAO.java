package dao;

import model.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 댓글 정보를 데이터베이스와 연동하는 DAO 클래스
 */
public class CommentDAO {
    
    // 댓글 등록
	public boolean insertComment(Comment comment) {
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    boolean success = false;
	    
	    try {
	        conn = DBConnection.getConnection();
	        
	        // 현재 게시글의 최대 댓글 번호 조회
	        String maxSeqSql = "SELECT NVL(MAX(comment_id), 0) + 1 FROM tbl_comment WHERE board_id = ?";
	        pstmt = conn.prepareStatement(maxSeqSql);
	        pstmt.setInt(1, comment.getBoardId());
	        rs = pstmt.executeQuery();
	        
	        int nextCommentId = 1; // 기본값
	        if (rs.next()) {
	            nextCommentId = rs.getInt(1);
	        }
	        
	        // 댓글 등록
	        if (pstmt != null) pstmt.close();
	        String sql = "INSERT INTO tbl_comment (comment_id, board_id, member_id, content) " +
	                     "VALUES (?, ?, ?, ?)";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, nextCommentId);
	        pstmt.setInt(2, comment.getBoardId());
	        pstmt.setString(3, comment.getMemberId());
	        pstmt.setString(4, comment.getContent());
	        
	        int result = pstmt.executeUpdate();
	        
	        if (result > 0) {
	            DBConnection.commit();
	            success = true;
	        } else {
	            DBConnection.rollback();
	        }
	        
	    } catch (SQLException e) {
	        DBConnection.rollback();
	        System.out.println("댓글 등록 오류: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	        } catch (SQLException e) {
	            System.out.println("리소스 닫기 오류: " + e.getMessage());
	        }
	    }
	    
	    return success;
	}
    
    // 댓글 목록 조회
    public List<Comment> getCommentsByBoardId(int boardId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Comment> commentList = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            
            // 댓글과 작성자 정보를 함께 조회
            String sql = "SELECT c.*, m.user_name " +
                         "FROM tbl_comment c " +
                         "JOIN tbl_member m ON c.member_id = m.member_id " +
                         "WHERE c.board_id = ? " +
                         "ORDER BY c.comment_id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setBoardId(rs.getInt("board_id"));
                comment.setMemberId(rs.getString("member_id"));
                comment.setContent(rs.getString("content"));
                comment.setUserName(rs.getString("user_name"));
                
                commentList.add(comment);
            }
            
        } catch (SQLException e) {
            System.out.println("댓글 목록 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return commentList;
    }
    
    // 댓글 삭제
    public boolean deleteComment(int boardId, int commentId, String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            // 댓글 작성자 확인 후 삭제
            String sql = "DELETE FROM tbl_comment WHERE board_id = ? AND comment_id = ? AND member_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            pstmt.setInt(2, commentId);
            pstmt.setString(3, memberId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("댓글 삭제 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    // 사용자의 댓글인지 확인
    public boolean isCommentOwner(int boardId, int commentId, String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isOwner = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT comment_id FROM tbl_comment WHERE board_id = ? AND comment_id = ? AND member_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, boardId);
            pstmt.setInt(2, commentId);
            pstmt.setString(3, memberId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                isOwner = true;
            }
            
        } catch (SQLException e) {
            System.out.println("댓글 소유자 확인 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return isOwner;
    }
}