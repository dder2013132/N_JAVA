package co.yedam;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class EmpApp {
	private static Scanner scn = new Scanner(System.in);
	private static EmpDAO dao = new EmpDAO(); 
	
	public static void main(String[] args) {
		
		// 앱을 실행하는 클래스.
		boolean run = true;
		while(run) {
			System.out.println("1.등록 2.목록 3.수정(급여) 4.삭제 5.조회(조건:입사일자) 6.종료");
			String m = getInput();
			switch(m) {
			case "1" : addEmp(); break;
			case "2" : showList(); break;
			case "3" : updateEmp(); break;
			case "4" : deleteEmp(); break;
			case "5" : showList2(); break;
			case "6" : run = false;
			}
		}
	}
	
	private static String getInput() {
        String input = scn.nextLine().trim();
        return input;
    } // getInput 종료
	
	private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scn.nextLine().trim());
            } catch (Exception e) {
                System.out.println("\n" + "정수를 입력하세요" + "\n");
            }
        }
    } // getIntInput 종료
	
	private static Date getDateInput() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String input = scn.nextLine();
        try {
			java.util.Date strToDate = df.parse(input);
			String fd = df.format(strToDate);
			Date date = null;
	        date = java.sql.Date.valueOf(fd);
			return date;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    } // getInput 종료
	
	private static void addEmp() {
		System.out.println("사번입력>> ");
        String empno = getInput();
        System.out.println("이름입력>> ");
        String empname = getInput();
        System.out.println("전화번호입력>> ");
        String phone = getInput();
        System.out.println("입사일입력>> ");
        Date hiredate = getDateInput();
        System.out.println("급여입력>> ");
        int salary = getIntInput();
        
        if (dao.insert(new Employee(empno, empname, phone, hiredate, salary))) {
            System.out.println("등록이 완료되었습니다.");
        }
        else {
            System.out.println("등록과정 중 예외가 발생되었습니다.");
        }
	}
	
	private static void showList() {
		List<Employee> list = dao.list();
        System.out.println("사번     이름    전화번호");
        for (Employee employee : list) {
            System.out.println(employee.showList());
        }
	}
	
	private static void updateEmp() {
        Employee employee = new Employee();
        System.out.println("사번 급여 >>");
        String empno = getInput();
        employee.setEmpno(empno);
        
        int salary = getIntInput();
        employee.setSalary(salary);
        
        if (dao.update(employee)) {        
            System.out.println("수정이 완료되었습니다.");
        }
        else {
            System.out.println("수정과정 중 예외가 발생되었습니다.");            
        }
    } // updateEmp 종료
	
	private static void deleteEmp() {
        System.out.println("사번>>");
        String empno = getInput();
        
        if (dao.delete(empno)) {
            System.out.println("삭제가 완료되었습니다.");
        }
        else {
            System.out.println("삭제과정 중 예외가 발생되었습니다.");            
        }
        
    } // deleteEmp 종료
	
	private static void showList2() {
		System.out.println("입사일자>>");
		Date date = getDateInput();
		List<Employee> list = dao.list2(date);
        System.out.println("사번     이름    입사일");
        for (Employee employee : list) {
            System.out.println(employee.showList2());
        }
	}
}
