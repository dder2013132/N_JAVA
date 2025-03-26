package model;

import java.sql.Date;

/**
 * 게시판 글 정보를 담는 모델 클래스
 */
public class Board {
    private int boardId;
    private String title;
    private String content;
    private String memberId;
    private int viewCount;
    private String boardType; // 공지, 질문, 자유
    private Date regDate;
    private String userName; // 조인 시 사용할 필드
    
    // 기본 생성자
    public Board() {}
    
    // 모든 필드를 포함한 생성자
    public Board(int boardId, String title, String content, String memberId, 
                int viewCount, String boardType, Date regDate) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.viewCount = viewCount;
        this.boardType = boardType;
        this.regDate = regDate;
    }
    
    // 글 등록을 위한 생성자
    public Board(String title, String content, String memberId, String boardType) {
        this.title = title;
        this.content = content;
        this.memberId = memberId;
        this.boardType = boardType;
    }
    
    // Getter와 Setter
    public int getBoardId() {
        return boardId;
    }
    
    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public int getViewCount() {
        return viewCount;
    }
    
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    
    public String getBoardType() {
        return boardType;
    }
    
    public void setBoardType(String boardType) {
        this.boardType = boardType;
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
    
    // 게시판 목록에 사용할 메소드
    public String getDisplayInfo() {
        // 게시판 타입, 게시물 번호, 제목, 유저명, 날짜, 조회수 형식
        return String.format("%-6s%03d. %-40s%-10s%-12s%d", 
                boardType, boardId, title, memberId, regDate, viewCount);
    }
    
    @Override
    public String toString() {
        return "제목: " + title + "\n작성자: " + memberId + "\n내용: " + content;
    }
}