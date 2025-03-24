package main.java.com.proj.model;

import java.util.Date;

public class Member {
	private String member_id;
	private String password;
	private String user_name;
	private String email;
	private String phone;
	private String adress;
	private Date join_date;
	private Date last_login_date;
	
	public Member() {}
	
	public Member(String member_id,String password,String user_name,String email,
			String phone, String adress,Date join_date,Date last_login_date) {
		this.member_id = member_id;
		this.password = password;
		this.user_name = user_name;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
		this.join_date = join_date;
		this.last_login_date = last_login_date;
	}
	
	public String getMemberId() {
		return member_id;
	}
	
	public void setMemberId(String member_id) { 
		this.member_id = member_id;
	}
	
	public String getpassword() {
		return member_id;
	}
	
	public void setPassword(String password) { 
		this.password = password;
	}
	
	public String getUsername() {
		return user_name;
	}
	
	public void setUsername(String user_name) { 
		this.user_name = user_name;
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
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) { 
		this.adress = adress;
	}
	
	public Date getJoindate() {
		return join_date;
	}
	
	public void setJoindate(Date join_date) { 
		this.join_date = join_date;
	}
	
	public Date getLastlogindate() {
		return last_login_date;
	}
	
	public void setLastlogindate(Date last_login_date) { 
		this.last_login_date = last_login_date;
	}
	
	@Override
	public String toString() {
		return "LOG: [member : " + member_id + ", username : " + user_name + ", email : " + email + ", phone : " + phone + ", joindate : " + join_date + ", lastlogindate : " + last_login_date + "]";   
	}
}
