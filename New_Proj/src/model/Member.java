package model;

import java.sql.Date;

/**
 * 회원 정보를 담는 모델 클래스
 */
public class Member {
    private String memberId;
    private String password;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private Date joinDate;
    private Date lastLoginDate;
    
    // 기본 생성자
    public Member() {}
    
    // 모든 필드를 포함한 생성자
    public Member(String memberId, String password, String userName, String email, 
                 String phone, String address, Date joinDate, Date lastLoginDate) {
        this.memberId = memberId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.joinDate = joinDate;
        this.lastLoginDate = lastLoginDate;
    }
    
    // 회원가입을 위한 생성자
    public Member(String memberId, String password, String userName, String email, 
                 String phone, String address) {
        this.memberId = memberId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
    
    // Getter와 Setter
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public Date getJoinDate() {
        return joinDate;
    }
    
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    
    public Date getLastLoginDate() {
        return lastLoginDate;
    }
    
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    
    @Override
    public String toString() {
        return "ID: " + memberId + ", 이름: " + userName + ", 이메일: " + email;
    }
}