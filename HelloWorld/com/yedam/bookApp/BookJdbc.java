package com.yedam.bookApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookJdbc {

    // Connection 생성
    Connection getConnection() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String userId = "scott";
        String userPw = "tiger";
        
        try {
            Connection conn = DriverManager.getConnection(url, userId, userPw);
            return conn;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // 추가
    public boolean insert(Book book) {
        String query = "INSERT INTO tbl_book (book_code, book_title, author, company, price) "
                     + "VALUES               (book_seq.nextval, ?, ?, ?, ?)";
        
        Connection conn = getConnection();
        try {
//            Statement stmt = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setInt(4, book.getPrice());
            
            int r = stmt.executeUpdate();
            if (r > 0) return true; // 등록 성공
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; // 등록 실패
    }
    
    // 수정
    public boolean update(Book book) {
        String query = "UPDATE tbl_book "
                     + "SET    book_title = NVL(?, book_title), "
                     + "       price = ?, "
                     + "       author = NVL(?, author) "
                     + "WHERE  book_code = ?";
        
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getPrice());
            stmt.setString(3, book.getAuthor());
            stmt.setString(4, book.getBookCode());
            
            int r = stmt.executeUpdate();
            if (r > 0) return true; // 삭제 성공
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    // 삭제
    public boolean delete(String bookCode) {
        String query = "DELETE FROM tbl_book "
                     + "WHERE       book_code = '" + bookCode + "'";
        
        Connection conn = getConnection();
        try {
            Statement stmt = conn.createStatement();
            int r = stmt.executeUpdate(query);
            if (r > 0) return true; // 삭제 성공
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; // 삭제 실패
    }
    
    // 조회
    public List<Book> list(String company) {
        List<Book> list = new ArrayList<Book>(); 
        Connection conn = getConnection();
        
        String query = "SELECT   book_code, book_title, author, company, price "
                     + "FROM     tbl_book "
                     + "WHERE    company = NVL(?, company) "
                     + "ORDER BY company, book_code";
        
        try {
            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setString(1, company);
            
            ResultSet rs = psmt.executeQuery();
            
            while (rs.next()) {
                Book book = new Book();
                book.setAuthor(rs.getString("author"));
                book.setBookCode(rs.getString("book_code"));
                book.setPrice(rs.getInt("price"));
                book.setPublisher(rs.getString("company"));
                book.setTitle(rs.getString("book_title"));
                list.add(book);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    // 조회(도서코드)
    public Book select(String bookCode) {
        Connection conn = getConnection();
        
        String query = "SELECT   book_code, book_title, author, company, price "
                     + "FROM     tbl_book "
                     + "WHERE    book_code = ? ";
        
        try {
            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setString(1, bookCode);
            
            ResultSet rs = psmt.executeQuery();
            
            if (rs.next()) {
                Book book = new Book();
                book.setAuthor(rs.getString("author"));
                book.setBookCode(rs.getString("book_code"));
                book.setPrice(rs.getInt("price"));
                book.setPublisher(rs.getString("company"));
                book.setTitle(rs.getString("book_title"));
                return book;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
