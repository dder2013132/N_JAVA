package model;

import java.sql.Date;

/**
 * 댓글 정보를 담는 모델 클래스
 */
public class Comment {
    private int commentId;
    private int boardId;
    private String memberId;
    private String content;
    private Date regDate; // DB 테이블에는 없지만 확장성을 위해 추가
    private String userName; // 조인 시 사용할 필드
    
    // 기본 생성자
    public Comment() {}
    
    // 모든 필드를 포함한 생성자
    public Comment(int commentId, int boardId, String memberId, String content, Date regDate) {
        this.commentId = commentId;
        this.boardId = boardId;
        this.memberId = memberId;
        this.content = content;
        this.regDate = regDate;
    }
    
    // 댓글 등록을 위한 생성자
    public Comment(int boardId, String memberId, String content) {
        this.boardId = boardId;
        this.memberId = memberId;
        this.content = content;
    }
    
    // Getter와 Setter
    public int getCommentId() {
        return commentId;
    }
    
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    
    public int getBoardId() {
        return boardId;
    }
    
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getRegDate() {
        return regDate;
    }
    
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // 댓글 표시에 사용할 메소드
    public String getDisplayInfo() {
        return commentId + " | " + memberId + " | " + content;
    }
    
    @Override
    public String toString() {
        return commentId + " | " + memberId + " | " + content;
    }
}