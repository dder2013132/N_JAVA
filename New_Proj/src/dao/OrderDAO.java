package dao;

import model.Order;
import model.OrderDetail;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 정보를 데이터베이스와 연동하는 DAO 클래스
 */
public class OrderDAO {
    
    // 주문 등록
    public int insertOrder(Order order) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int orderId = -1;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "INSERT INTO tbl_order (order_id, member_id, total_amount, payment_method, delivery_status) " +
                    "VALUES (ORDER_SEQ.NEXTVAL, ?, ?, ?, 'PENDING')";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, order.getMemberId());
	        pstmt.setInt(2, order.getTotalAmount());
	        pstmt.setString(3, order.getPaymentMethod());
	
 	        int result = pstmt.executeUpdate();
	
	        if (result > 0) {
	            // 방금 생성된 order_id 조회
	            Statement stmt = conn.createStatement();
	            rs = stmt.executeQuery("SELECT ORDER_SEQ.CURRVAL FROM dual");
	            if (rs.next()) {
	                orderId = rs.getInt(1);
	            }
	            rs.close();
	            stmt.close();
	           
	            DBConnection.commit();
	        } else {
	            DBConnection.rollback();
	        }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("주문 등록 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return orderId;
    }
    
    // 주문 상세 등록
    public boolean insertOrderDetail(OrderDetail orderDetail) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "INSERT INTO tbl_order_detail (order_detail_id, order_id, product_id, quantity, price) " +
                         "VALUES (ORDER_DETAIL_SEQ.NEXTVAL, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderDetail.getOrderId());
            pstmt.setInt(2, orderDetail.getProductId());
            pstmt.setInt(3, orderDetail.getQuantity());
            pstmt.setInt(4, orderDetail.getPrice());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                // 요청 수 증가
                if (increaseRequestCount(orderDetail.getProductId())) {
                    DBConnection.commit();
                    success = true;
                } else {
                    DBConnection.rollback();
                }
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("주문 상세 등록 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    // 사용자의 모든 주문 목록 조회
    public List<Order> getOrdersByMemberId(String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT o.*, m.user_name " +
                         "FROM tbl_order o " +
                         "JOIN tbl_member m ON o.member_id = m.member_id " +
                         "WHERE o.member_id = ? " +
                         "ORDER BY o.order_date DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setMemberId(rs.getString("member_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setDeliveryStatus(rs.getString("delivery_status"));
                order.setUserName(rs.getString("user_name"));
                
                orderList.add(order);
            }
            
        } catch (SQLException e) {
            System.out.println("주문 목록 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return orderList;
    }
    
    // 주문 상세 목록 조회
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT od.*, p.product_name, p.category " +
                         "FROM tbl_order_detail od " +
                         "JOIN tbl_product p ON od.product_id = p.product_id " +
                         "WHERE od.order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderDetailId(rs.getInt("order_detail_id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setProductId(rs.getInt("product_id"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setPrice(rs.getInt("price"));
                orderDetail.setProductName(rs.getString("product_name"));
                orderDetail.setCategory(rs.getString("category"));
                
                orderDetailList.add(orderDetail);
            }
            
        } catch (SQLException e) {
            System.out.println("주문 상세 목록 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return orderDetailList;
    }
    
    public List<Order> getOrdersByProductId(int productId) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	List<Order> orderList = new ArrayList<>();
    	
    	try {
    		conn = DBConnection.getConnection();
    		
    		String sql = "SELECT DISTINCT o.*, m.user_name " +
                    "FROM tbl_order o " +
                    "JOIN tbl_order_detail od ON o.order_id = od.order_id " +
                    "JOIN tbl_member m ON o.member_id = m.member_id " +
                    "WHERE od.product_id = ? " +
                    "ORDER BY o.order_date DESC ";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, productId);
    		
    		rs = pstmt.executeQuery();
    		
    		while(rs.next()) {
    			Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setMemberId(rs.getString("member_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setDeliveryStatus(rs.getString("delivery_status"));
                order.setUserName(rs.getString("user_name"));
    			
    			orderList.add(order);
    		}
    	} catch (SQLException e) {
    		System.out.println("상품별 주문 목록 조회 오류: " + e.getMessage());
    	} finally {
    		try {
    			if (rs != null) rs.close();
    			if (pstmt != null) pstmt.close();
    		} catch (SQLException e) {
    			System.out.println("리소스 닫기 오류: " + e.getMessage());
    		}
    	}
    	
    	return orderList;
    }
    
    // 배송 상태 변경
    public boolean updateDeliveryStatus(int orderId, String status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "UPDATE tbl_order SET delivery_status = ? WHERE order_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, orderId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("배송 상태 변경 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    // 주문 취소 (배송 상태가 '보류'인 경우만 가능)
    public boolean cancelOrder(int orderId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            // 주문 상태 확인
            String checkSql = "SELECT delivery_status FROM tbl_order WHERE order_id = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setInt(1, orderId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String status = rs.getString("delivery_status");
                if (!status.equals("PENDING")) {
                    return false; // 이미 배송 중이거나 완료된 주문
                }
            } else {
                return false; // 주문이 존재하지 않음
            }
            if (conn == null || conn.isClosed()) conn = DBConnection.getConnection();
            // 주문에 포함된 상품의 재고 복원 및 요청 수 감소
            String getOrderDetailsSql = "SELECT product_id, quantity FROM tbl_order_detail WHERE order_id = ?";
            pstmt = conn.prepareStatement(getOrderDetailsSql);
            pstmt.setInt(1, orderId);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                
                // 재고 증가
                String updateStockSql = "UPDATE tbl_product SET stock_quantity = stock_quantity + ? WHERE product_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateStockSql);
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, productId);
                updateStmt.executeUpdate();
                updateStmt.close();
                
                // 요청 수 감소
                decreaseRequestCount(productId);
            }
            
            // 주문 상세 삭제
            String deleteOrderDetailsSql = "DELETE FROM tbl_order_detail WHERE order_id = ?";
            pstmt = conn.prepareStatement(deleteOrderDetailsSql);
            pstmt.setInt(1, orderId);
            pstmt.executeUpdate();
            
            // 주문 삭제
            String deleteOrderSql = "DELETE FROM tbl_order WHERE order_id = ?";
            pstmt = conn.prepareStatement(deleteOrderSql);
            pstmt.setInt(1, orderId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("주문 취소 오류: " + e.getMessage());
            e.printStackTrace(); // 디버깅을 위한 상세 오류 출력
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    public void createOrderSequences() {
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
                stmt.executeUpdate("DROP SEQUENCE ORDER_SEQ");
                stmt.executeUpdate("DROP SEQUENCE ORDER_DETAIL_SEQ");
            } catch (SQLException e) {
                // 시퀀스가 없으면 무시 (처음 실행 시)
            }
            
            // 현재 테이블의 최대 ID 값 조회
            int startValue = 1; // 기본 시작값
            String maxIdSql = "SELECT NVL(MAX(order_id), 0) + 1 FROM tbl_order";
            pstmt = conn.prepareStatement(maxIdSql);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                startValue = rs.getInt(1);
            }
            
            // 최대값+1에서 시작하는 새 시퀀스 생성
            String createSeqSql = "CREATE SEQUENCE ORDER_SEQ START WITH " + startValue + 
                                  " INCREMENT BY 1 NOCACHE NOCYCLE";
            stmt.executeUpdate(createSeqSql);
            
            int startValue2 = 1;
            String maxIdSql2 = "SELECT NVL(MAX(order_detail_id), 0) + 1 FROM tbl_order_detail";
            pstmt = conn.prepareStatement(maxIdSql2);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	startValue2 = rs.getInt(1);
            }
            
            String createSeqSql2 = "CREATE SEQUENCE ORDER_DETAIL_SEQ START WITH " + startValue2 + 
                    " INCREMENT BY 1 NOCACHE NOCYCLE";
            stmt.executeUpdate(createSeqSql2);
            
            DBConnection.commit();
           // System.out.println("ORDER_SEQ 시퀀스가 " + startValue + "에서 시작하도록 설정되었습니다.");
            //System.out.println("PRODUCT_DETAIL_SEQ 시퀀스가 " + startValue2 + "에서 시작하도록 설정되었습니다.");
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("상품 시퀀스 생성 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
    }
    
    /**
     * 상품의 요청 수 증가
     * @param productId 상품 ID
     * @return 성공 여부
     */
    public boolean increaseRequestCount(int productId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "UPDATE tbl_product SET request_count = request_count + 1 WHERE product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("요청 수 증가 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }

    /**
     * 상품의 요청 수 감소
     * @param productId 상품 ID
     * @return 성공 여부
     */
    public boolean decreaseRequestCount(int productId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            // 현재 요청 수 확인 (0 미만으로 감소하지 않도록)
            String checkSql = "SELECT request_count FROM tbl_product WHERE product_id = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setInt(1, productId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int currentCount = rs.getInt("request_count");
                if (currentCount <= 0) {
                    return true; // 이미 0이면 감소 필요 없음
                }
            } else {
                return false; // 상품이 존재하지 않음
            }
            
            // 요청 수 감소
            String sql = "UPDATE tbl_product SET request_count = request_count - 1 WHERE product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("요청 수 감소 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    // 주문 상태별 목록 조회
    public List<Order> getOrdersByStatus(String status) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT o.*, m.user_name " +
                         "FROM tbl_order o " +
                         "JOIN tbl_member m ON o.member_id = m.member_id " +
                         "WHERE o.delivery_status = ? " +
                         "ORDER BY o.order_date DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setMemberId(rs.getString("member_id"));
                order.setOrderDate(rs.getDate("order_date"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setPaymentMethod(rs.getString("payment_method"));
                order.setDeliveryStatus(rs.getString("delivery_status"));
                order.setUserName(rs.getString("user_name"));
                
                orderList.add(order);
            }
            
        } catch (SQLException e) {
            System.out.println("주문 상태별 목록 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return orderList;
    }
}