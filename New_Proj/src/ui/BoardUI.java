package ui;

import dao.BoardDAO;
import dao.CommentDAO;
import model.Board;
import model.Comment;
import model.Member;
import util.ConsoleUtil;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 게시판 UI 클래스
 */
public class BoardUI {
    private Member loginMember;
    private BoardDAO boardDAO;
    private CommentDAO commentDAO;
    
    public BoardUI(Member loginMember) {
        this.loginMember = loginMember;
        this.boardDAO = new BoardDAO();
        this.commentDAO = new CommentDAO();
    }
    
    /**
     * 게시판 목록을 표시하는 메소드
     */
    public void showBoardList() {
        while (true) {
            ConsoleUtil.clearScreen();
            List<Board> boardList = boardDAO.getBoardList();
            
            ConsoleUtil.printHeader("게시판");
            
            if (boardList.isEmpty()) {
                ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "게시글이 없습니다." + ConsoleUtil.RESET);
            } else {
                ConsoleUtil.printMessage(ConsoleUtil.CYAN + "번호   |  타입  |  제목                     |  작성자      |  날짜         |조회수" + ConsoleUtil.RESET);
                ConsoleUtil.printDivider();
                
                for (Board board : boardList) {
                    String type = getColoredBoardType(board.getBoardType());
                    String title = limitString(board.getTitle(), 30);
                    String dateStr = formatDate(board.getRegDate());
                    
                    ConsoleUtil.printMessage(String.format("%03d   |  %s  |  %-20s  |  %-8s  |  %s  | %d", 
                        board.getBoardId(), type, title, board.getUserName(), dateStr, board.getViewCount()));
                }
            }
            
            ConsoleUtil.printDivider();
            ConsoleUtil.printMessage("게시글 번호 입력: 해당 게시글 보기");
            ConsoleUtil.printMessage(ConsoleUtil.GREEN + "q" + ConsoleUtil.RESET + ": 글 등록   " + 
                                  ConsoleUtil.RED + "w" + ConsoleUtil.RESET + ": 돌아가기");
            ConsoleUtil.printDivider();
            
            String input = ConsoleUtil.readString("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
            
            if (input.equalsIgnoreCase("q")) {
                registerBoard();
            } else if (input.equalsIgnoreCase("w")) {
                return; // 메인 메뉴로 돌아가기
            } else {
                try {
                    int boardId = Integer.parseInt(input);
                    showBoardDetail(boardId);
                } catch (NumberFormatException e) {
                    ConsoleUtil.printError("올바른 입력이 아닙니다. 다시 선택해주세요.");
                    ConsoleUtil.pressEnterToContinue();
                }
            }
        }
    }
    
    private String getColoredBoardType(String boardType) {
        switch (boardType) {
            case "공지":
                return ConsoleUtil.RED + "공지" + ConsoleUtil.RESET;
            case "질문":
                return ConsoleUtil.BLUE + "질문" + ConsoleUtil.RESET;
            case "자유":
                return ConsoleUtil.GREEN + "자유" + ConsoleUtil.RESET;
            default:
                return boardType;
        }
    }
    
    private String limitString(String str, int maxLength) {
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    private String formatDate(Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    /**
     * 게시글 상세 화면을 표시하는 메소드
     * @param boardId 게시글 ID
     */
    private void showBoardDetail(int boardId) {
        ConsoleUtil.clearScreen();
        Board board = boardDAO.getBoardById(boardId);
        
        if (board == null) {
            ConsoleUtil.printError("존재하지 않는 게시글입니다.");
            ConsoleUtil.pressEnterToContinue();
            return;
        }
        
        ConsoleUtil.printHeader("게시글 상세");
        
        // 게시글 헤더 정보
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "제목: " + ConsoleUtil.RESET + board.getTitle());
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "작성자: " + ConsoleUtil.RESET + board.getUserName() + 
                              ConsoleUtil.BLUE + "  |  " + ConsoleUtil.YELLOW + "작성일: " + 
                              ConsoleUtil.RESET + formatDate(board.getRegDate()) + 
                              ConsoleUtil.BLUE + "  |  " + ConsoleUtil.YELLOW + "조회수: " + 
                              ConsoleUtil.RESET + board.getViewCount());
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "분류: " + ConsoleUtil.RESET + getColoredBoardType(board.getBoardType()));
        ConsoleUtil.printDivider();
        
        // 게시글 내용
        String[] contentLines = board.getContent().split("\n");
        for (String line : contentLines) {
            ConsoleUtil.printMessage(line);
        }
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage(ConsoleUtil.PURPLE + "▶ 댓글" + ConsoleUtil.RESET);
        
        // 댓글 목록 표시
        List<Comment> commentList = commentDAO.getCommentsByBoardId(boardId);
        if (commentList.isEmpty()) {
            ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "댓글이 없습니다." + ConsoleUtil.RESET);
        } else {
            for (Comment comment : commentList) {
                ConsoleUtil.printMessage(ConsoleUtil.CYAN + comment.getCommentId() + ". " + 
                                      ConsoleUtil.GREEN + comment.getUserName() + ConsoleUtil.RESET + 
                                      ": " + comment.getContent());
            }
        }
        
        ConsoleUtil.printDivider();
        
        // 본인 게시물인 경우 수정, 삭제 옵션 표시
        boolean isOwner = board.getMemberId().equals(loginMember.getMemberId());
        
        if (isOwner) {
            ConsoleUtil.printMessage("1. " + ConsoleUtil.BLUE + "글 수정" + ConsoleUtil.RESET + "   " + 
                                  "2. " + ConsoleUtil.RED + "글 삭제" + ConsoleUtil.RESET + "   " + 
                                  "3. " + ConsoleUtil.GREEN + "댓글 작성" + ConsoleUtil.RESET + "   " + 
                                  "4. " + ConsoleUtil.YELLOW + "댓글 삭제" + ConsoleUtil.RESET + "   " + 
                                  "5. " + ConsoleUtil.PURPLE + "돌아가기" + ConsoleUtil.RESET);
        } else {
            ConsoleUtil.printMessage("1. " + ConsoleUtil.GREEN + "댓글 작성" + ConsoleUtil.RESET + "   " + 
                                  "2. " + ConsoleUtil.YELLOW + "댓글 삭제" + ConsoleUtil.RESET + "   " + 
                                  "3. " + ConsoleUtil.PURPLE + "돌아가기" + ConsoleUtil.RESET);
        }
        
        int choice = ConsoleUtil.readInt("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
        
        if (isOwner) {
            switch (choice) {
                case 1:
                    updateBoard(board);
                    showBoardDetail(boardId); // 새로고침
                    break;
                case 2:
                    if (deleteBoard(board.getBoardId())) {
                        return; // 삭제 성공 시 목록으로
                    }
                    showBoardDetail(boardId); // 삭제 실패 시 새로고침
                    break;
                case 3:
                    addComment(board.getBoardId());
                    showBoardDetail(boardId); // 새로고침
                    break;
                case 4:
                    deleteComment(boardId);
                    showBoardDetail(boardId); // 새로고침
                    break;
                case 5:
                    return; // 게시판 목록으로 돌아가기
                default:
                    ConsoleUtil.printError("잘못된 선택입니다.");
                    ConsoleUtil.pressEnterToContinue();
                    showBoardDetail(boardId); // 다시 보기
            }
        } else {
            switch (choice) {
                case 1:
                    addComment(board.getBoardId());
                    showBoardDetail(boardId); // 새로고침
                    break;
                case 2:
                    deleteComment(boardId);
                    showBoardDetail(boardId); // 새로고침
                    break;
                case 3:
                    return; // 게시판 목록으로 돌아가기
                default:
                    ConsoleUtil.printError("잘못된 선택입니다.");
                    ConsoleUtil.pressEnterToContinue();
                    showBoardDetail(boardId); // 다시 보기
            }
        }
    }
    
    /**
     * 게시글 등록 메소드
     */
    private void registerBoard() {
        ConsoleUtil.printDivider();
        
        String title = ConsoleUtil.readString("제목 >> ");
        if (title.trim().isEmpty()) {
            ConsoleUtil.printError("제목을 입력해주세요.");
            return;
        }
        
        String content = ConsoleUtil.readString("내용 >> ");
        if (content.trim().isEmpty()) {
            ConsoleUtil.printError("내용을 입력해주세요.");
            return;
        }
        
        ConsoleUtil.printMessage("게시판 타입: 1. 공지 2. 질문 3. 자유");
        int typeChoice = ConsoleUtil.readInt("선택 >> ");
        
        String boardType;
        switch (typeChoice) {
            case 1:
                boardType = "공지";
                break;
            case 2:
                boardType = "질문";
                break;
            case 3:
                boardType = "자유";
                break;
            default:
                boardType = "자유"; // 기본값
        }
        
        Board board = new Board(title, content, loginMember.getMemberId(), boardType);
        
        boolean success = boardDAO.insertBoard(board);
        
        if (success) {
            ConsoleUtil.printSuccess("게시글이 등록되었습니다.");
        } else {
            ConsoleUtil.printError("게시글 등록에 실패했습니다.");
        }
    }
    
    /**
     * 게시글 수정 메소드
     * @param board 수정할 게시글
     */
    private void updateBoard(Board board) {
        ConsoleUtil.printDivider();
        
        String title = ConsoleUtil.readString("제목 (" + board.getTitle() + ") >> ");
        if (!title.trim().isEmpty()) {
            board.setTitle(title);
        }
        
        String content = ConsoleUtil.readString("내용 >> ");
        if (!content.trim().isEmpty()) {
            board.setContent(content);
        }
        
        ConsoleUtil.printMessage("게시판 타입: 1. 공지 2. 질문 3. 자유 (현재: " + board.getBoardType() + ")");
        String typeChoice = ConsoleUtil.readString("선택 (엔터시 유지) >> ");
        
        if (!typeChoice.trim().isEmpty()) {
            try {
                int choice = Integer.parseInt(typeChoice);
                switch (choice) {
                    case 1:
                        board.setBoardType("공지");
                        break;
                    case 2:
                        board.setBoardType("질문");
                        break;
                    case 3:
                        board.setBoardType("자유");
                        break;
                }
            } catch (NumberFormatException e) {
                // 숫자가 아닌 경우 무시
            }
        }
        
        boolean success = boardDAO.updateBoard(board);
        
        if (success) {
            ConsoleUtil.printSuccess("게시글이 수정되었습니다.");
        } else {
            ConsoleUtil.printError("게시글 수정에 실패했습니다.");
        }
    }
    
    /**
     * 게시글 삭제 메소드
     * @param boardId 삭제할 게시글 ID
     */
    private boolean deleteBoard(int boardId) {
        ConsoleUtil.printDivider();
        
        boolean confirm = ConsoleUtil.confirm("정말 삭제하시겠습니까?");
        
        if (confirm) {
            boolean success = boardDAO.deleteBoard(boardId);
            
            if (success) {
                ConsoleUtil.printSuccess("게시글이 삭제되었습니다.");
                return success;
            } else {
                ConsoleUtil.printError("게시글 삭제에 실패했습니다.");
            }
        }
        return false;
    }
    
    /**
     * 댓글 추가 메소드
     * @param boardId 댓글을 추가할 게시글 ID
     */
    private void addComment(int boardId) {
        ConsoleUtil.printDivider();
        
        String content = ConsoleUtil.readString("댓글 내용 >> ");
        if (content.trim().isEmpty()) {
            ConsoleUtil.printError("내용을 입력해주세요.");
            return;
        }
        
        Comment comment = new Comment(boardId, loginMember.getMemberId(), content);
        
        boolean success = commentDAO.insertComment(comment);
        
        if (success) {
            ConsoleUtil.printSuccess("댓글이 등록되었습니다.");
        } else {
            ConsoleUtil.printError("댓글 등록에 실패했습니다.");
        }
    }
    
    /**
     * 댓글 삭제 메소드
     * @param boardId 댓글이 속한 게시글 ID
     */
    private void deleteComment(int boardId) {
        List<Comment> commentList = commentDAO.getCommentsByBoardId(boardId);
        
        if (commentList.isEmpty()) {
            ConsoleUtil.printError("삭제할 댓글이 없습니다.");
            return;
        }
        
        ConsoleUtil.printDivider();
        for (Comment comment : commentList) {
            ConsoleUtil.printMessage(comment.getDisplayInfo());
        }
        
        int commentId = ConsoleUtil.readInt("삭제할 댓글 번호 >> ");
        
        // 본인 댓글인지 확인
        boolean isOwner = commentDAO.isCommentOwner(boardId, commentId, loginMember.getMemberId());
        
        if (!isOwner) {
            ConsoleUtil.printError("본인이 작성한 댓글만 삭제할 수 있습니다.");
            return;
        }
        
        boolean confirm = ConsoleUtil.confirm("정말 삭제하시겠습니까?");
        
        if (confirm) {
            boolean success = commentDAO.deleteComment(boardId, commentId, loginMember.getMemberId());
            
            if (success) {
                ConsoleUtil.printSuccess("댓글이 삭제되었습니다.");
            } else {
                ConsoleUtil.printError("댓글 삭제에 실패했습니다.");
            }
        }
    }
}