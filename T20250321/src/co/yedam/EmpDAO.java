package co.yedam;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	//기능 구현
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
	//add
	public boolean insert(Employee employee) {
        String query = "INSERT INTO tbl_emp (emp_no, emp_name, phone, hire_date, salary) "
                     + "VALUES               (?, ?, ?, ?, ?)";
        
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, employee.getEmpno());
            stmt.setString(2, employee.getEmpname());
            stmt.setString(3, employee.getPhone());
            stmt.setDate(4, employee.getHiredate());
            stmt.setInt(5, employee.getSalary());
            
            int r = stmt.executeUpdate();
            if (r > 0) return true; // 등록 성공
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false; // 등록 실패
    }
	
	// 수정
    public boolean update(Employee employee) {
        String query = "UPDATE tbl_emp "
                     + "SET    salary = ? "
                     + "WHERE  emp_no = ?";
        
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, employee.getSalary());
            stmt.setString(2, employee.getEmpno());
            
            int r = stmt.executeUpdate();
            if (r > 0) return true; // 삭제 성공
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    // 삭제
    public boolean delete(String empno) {
        String query = "DELETE FROM tbl_emp "
                     + "WHERE       emp_no = '" + empno + "'";
        
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
    public List<Employee> list() {
        List<Employee> list = new ArrayList<Employee>(); 
        Connection conn = getConnection();
        
        String query = "SELECT   emp_no, emp_name, phone "
                     + "FROM     tbl_emp "
                     + "ORDER BY emp_no ";
        
        try {
            PreparedStatement psmt = conn.prepareStatement(query);
            
            ResultSet rs = psmt.executeQuery();
            
            while (rs.next()) {
            	Employee employee = new Employee();
                employee.setEmpno(rs.getString("emp_no"));
                employee.setEmpname(rs.getString("emp_name"));
                employee.setPhone(rs.getString("phone"));
                list.add(employee);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Employee> list2(Date hiredate) {
        List<Employee> list = new ArrayList<Employee>(); 
        Connection conn = getConnection();
        
        String query = "SELECT   emp_no, emp_name, hire_date "
                     + "FROM     tbl_emp "
                     + "WHERE    hire_date = ? "
                     + "ORDER BY hire_date ";
        
        try {
            PreparedStatement psmt = conn.prepareStatement(query);
            System.out.println(hiredate);
            psmt.setDate(1, hiredate);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
            	Employee employee = new Employee();
                employee.setEmpno(rs.getString("emp_no"));
                employee.setEmpname(rs.getString("emp_name"));
                employee.setHiredate(rs.getDate("hire_date"));
                list.add(employee);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // 조회(입사일자)
    public Employee select(Date hiredate) {
        Connection conn = getConnection();
        
        String query = "SELECT   emp_no, emp_name, hire_date "
                     + "FROM     tbl_emp "
                     + "WHERE    hire_date = ? "
                     + "ORDER BY hire_date ";
        
        try {
            PreparedStatement psmt = conn.prepareStatement(query);
            psmt.setDate(1, (java.sql.Date) hiredate);
            
            ResultSet rs = psmt.executeQuery();
            
            if (rs.next()) {
                Employee employee = new Employee();
                employee.setEmpno(rs.getString("emp_no"));
                employee.setEmpname(rs.getString("emp_name"));
                employee.setHiredate(rs.getDate("hire_date"));
                return employee;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
}
