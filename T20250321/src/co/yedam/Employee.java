package co.yedam;

import java.sql.Date;

public class Employee {
	private String empno;
	private String empname;
	private String phone;
	private Date hiredate;
	private int salary;
	
	public Employee() {};
	
	public Employee(String empno, String empname, String phone, Date hiredate, int salary) {
		this.empno = empno;
		this.empname = empname;
		this.phone = phone;
		this.hiredate = hiredate;
		this.salary = salary;
	}
	
	public String getEmpno() {
		return empno;
	}
	
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	
	public String getEmpname() {
		return empname;
	}
	
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public Date getHiredate() {
		return hiredate;
	}
	
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String showList() {
	    return empno + "   " + empname + "   " + phone;
	}
	
	public String showList2() {
	    return empno + "   " + empname + "   " + hiredate;
	}
}