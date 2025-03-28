package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 상품 정보를 데이터베이스와 연동하는 DAO 클래스
 */
public class ProductDAO {
    
    // 상품 등록
    public boolean insertProduct(Product product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "INSERT INTO tbl_product (product_id, product_name, price, stock_quantity, description, category, seller_id, seller_name) " +
                         "VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setInt(3, product.getStockQuantity());
            pstmt.setString(4, product.getDescription());
            pstmt.setString(5, product.getCategory());
            pstmt.setString(6, product.getSellerId());
            pstmt.setString(7, product.getSellerName());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("상품 등록 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    // 상품 목록 조회
    public List<Product> getProductList() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            
            // 상품과 판매자 정보를 함께 조회
            String sql = "SELECT p.*, m.user_name, " +
                        "(SELECT COUNT(*) FROM tbl_order_detail od JOIN tbl_order o ON od.order_id = o.order_id " +
                        "WHERE od.product_id = p.product_id) as request_count " +
                        "FROM tbl_product p " +
                        "LEFT JOIN tbl_member m ON p.seller_id = m.member_id " +
                        "ORDER BY p.product_id DESC";
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getInt("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setRegDate(rs.getDate("reg_date"));
                product.setSellerId(rs.getString("seller_id"));
                product.setSellerName(rs.getString("seller_name"));
                product.setRequestCount(rs.getInt("request_count"));
                
                productList.add(product);
            }
            
        } catch (SQLException e) {
            System.out.println("상품 목록 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return productList;
    }
    
    // 판매 중인 상품 목록 조회
    public List<Product> getSellingProductList(String sellerId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT p.*, " +
                        "(SELECT COUNT(*) FROM tbl_order_detail od JOIN tbl_order o ON od.order_id = o.order_id " +
                        "WHERE od.product_id = p.product_id) as request_count " +
                        "FROM tbl_product p " +
                        "WHERE p.seller_id = ? " +
                        "ORDER BY p.product_id DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sellerId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getInt("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setRegDate(rs.getDate("reg_date"));
                product.setSellerId(rs.getString("seller_id"));
                product.setSellerName(rs.getString("seller_name"));
                product.setRequestCount(rs.getInt("request_count"));
                
                productList.add(product);
            }
            
        } catch (SQLException e) {
            System.out.println("판매 중인 상품 목록 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return productList;
    }
    
    // 상품 상세 조회
    public Product getProductById(int productId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Product product = null;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT p.*, m.user_name " +
                         "FROM tbl_product p " +
                         "LEFT JOIN tbl_member m ON p.seller_id = m.member_id " +
                         "WHERE p.product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setPrice(rs.getInt("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                product.setDescription(rs.getString("description"));
                product.setCategory(rs.getString("category"));
                product.setRegDate(rs.getDate("reg_date"));
                product.setSellerId(rs.getString("seller_id"));
                product.setSellerName(rs.getString("seller_name"));
            }
            
        } catch (SQLException e) {
            System.out.println("상품 상세 조회 오류: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return product;
    }
    
    // 상품 수정
    public boolean updateProduct(Product product) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "UPDATE tbl_product SET product_name = ?, price = ?, stock_quantity = ?, " +
                         "description = ?, category = ? WHERE product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setInt(3, product.getStockQuantity());
            pstmt.setString(4, product.getDescription());
            pstmt.setString(5, product.getCategory());
            pstmt.setInt(6, product.getProductId());
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("상품 수정 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    // 재고 감소
    public boolean decreaseStock(int productId, int quantity) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            // 현재 재고 확인
            String checkSql = "SELECT stock_quantity FROM tbl_product WHERE product_id = ?";
            pstmt = conn.prepareStatement(checkSql);
            pstmt.setInt(1, productId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int currentStock = rs.getInt("stock_quantity");
                if (currentStock < quantity) {
                    return false; // 재고 부족
                }
            } else {
                return false; // 상품이 존재하지 않음
            }
            
            // 재고 감소
            String sql = "UPDATE tbl_product SET stock_quantity = stock_quantity - ? WHERE product_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            
            int result = pstmt.executeUpdate();
            
            if (result > 0) {
                DBConnection.commit();
                success = true;
            } else {
                DBConnection.rollback();
            }
            
        } catch (SQLException e) {
            DBConnection.rollback();
            System.out.println("재고 감소 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    // 상품 삭제
    public boolean deleteProduct(int productId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean success = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "DELETE FROM tbl_product WHERE product_id = ?";
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
            System.out.println("상품 삭제 오류: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.out.println("리소스 닫기 오류: " + e.getMessage());
            }
        }
        
        return success;
    }
    
    public void createProductSequence() {
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
                stmt.executeUpdate("DROP SEQUENCE PRODUCT_SEQ");
            } catch (SQLException e) {
                // 시퀀스가 없으면 무시 (처음 실행 시)
            }
            
            // 현재 테이블의 최대 ID 값 조회
            int startValue = 1; // 기본 시작값
            String maxIdSql = "SELECT NVL(MAX(product_id), 0) + 1 FROM tbl_product";
            pstmt = conn.prepareStatement(maxIdSql);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                startValue = rs.getInt(1);
            }
            
            // 최대값+1에서 시작하는 새 시퀀스 생성
            String createSeqSql = "CREATE SEQUENCE PRODUCT_SEQ START WITH " + startValue + 
                                  " INCREMENT BY 1 NOCACHE NOCYCLE";
            stmt.executeUpdate(createSeqSql);
            
            DBConnection.commit();
            //System.out.println("PRODUCT_SEQ 시퀀스가 " + startValue + "에서 시작하도록 설정되었습니다.");
            
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
    
    // 사용자의 상품인지 확인
    public boolean isProductOwner(int productId, String memberId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isOwner = false;
        
        try {
            conn = DBConnection.getConnection();
            
            String sql = "SELECT product_id FROM tbl_product WHERE product_id = ? AND seller_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            pstmt.setString(2, memberId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                isOwner = true;
            }
            
        } catch (SQLException e) {
            System.out.println("상품 소유자 확인 오류: " + e.getMessage());
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