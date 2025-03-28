package util;

import dao.BoardDAO;
import dao.ProductDAO;
import dao.OrderDAO;

/**
 * 시스템 초기화 관련 유틸리티 클래스
 */
public class SystemInitializer {
    
    /**
     * 시퀀스 초기화 메소드
     */
    public static void initializeSequences() {
        try {
            // 게시판 시퀀스 생성
            BoardDAO boardDAO = new BoardDAO();
            boardDAO.createBoardSequence();
            
            // 상품 시퀀스 생성
            ProductDAO productDAO = new ProductDAO();
            productDAO.createProductSequence();
            
            // 주문 시퀀스 생성
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.createOrderSequences();
            
        } catch (Exception e) {
            System.out.println("시퀀스 초기화 오류: " + e.getMessage());
        }
    }
}