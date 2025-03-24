package com.yedam.classes;

import java.util.List;

public class MethodMain {
    static java.util.Scanner scanner = new java.util.Scanner(System.in);
    
    // 메소드 오버로드
    void method1() {
        MethodExe1 m1 = new MethodExe1();
        // System.out.println(m1);
        int n = 5;
        m1.printString(n, "*");
        
        int sum = m1.sum(n, 10);
        System.out.println(sum);
        
        double result = m1.sum(n, 10.5);
        System.out.println(result);
        
        sum = m1.sum(new int[] { 10, 20, 30 });
        System.out.println(sum);
        
        result = m1.sum(new double[] { 10.4, 20.3, 30.8 });
        System.out.println(result);
    } // end of method1 methods
    
    // Product 관리
    void method2() {
     // 상품코드: M001, 상품명: 만년필, 가격: 10000
        MethodExe2 m2 = new MethodExe2();
        if(m2.add(new Product("M001", "만년필", 10000))) {
            System.out.println("등록성공");
        }
        
        Product search = new Product();
        search.setProductName("지우개");
        
        List<Product> list = m2.productList(search);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.println(list.get(i).showList());
            }
        }
        
        if(m2.remove("A001")) {
            System.out.println("삭제성공");
        }
        
        search.setProductName("ALL");
        
        list = m2.productList(search);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.println(list.get(i).showList());
            }
        }
        
        if (m2.modify(new Product("B001", "형광펜", 0))) {
            System.out.println("수정성공");
        }
        
        search.setProductName("지우개");
        search.setPrice(700);
        
        list = m2.productList(search);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                System.out.println(list.get(i).showList());
            }
        }
    } // end of method2 methods
    
    // 별찍기, 랜덤 수(중복x)
    void method3() {
        MethodExe3 m3 = new MethodExe3();
        System.out.println(m3.gugudan(7));
        System.out.println(m3.gugudan(3, 5));
        
        System.out.println();
        
        m3.printStar(5, "*");
        System.out.println();
        
        m3.printReverseStar(5, "*");
        System.out.println();
        
        m3.printPyramidStar(5, "*");
        System.out.println();
        
        m3.printDiamondStar(5, "*");
        System.out.println();
        
        m3.printCard();
        System.out.println();
    } // end of method3 methods
    
    void method4() {
        MethodExe4 m4 = new MethodExe4();
        m4.main();
        
        System.out.println("end of program");
    } // end of method4 methods
    
    
    // 초기 메뉴
    private static int getInputMenu() {
        
        boolean check = true;
        
        int menu = 0;
        while (check) {
            try {            
                System.out.println("┏━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━┓");
                System.out.println("┃ 1. 목록 ┃ 2. 추가 ┃ 3. 수정 ┃ 4. 삭제 ┃ 9. 종료 ┃");
                System.out.println("┗━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━┛");
                
                System.out.print("메뉴 선택 >> ");
                menu = Integer.parseInt(scanner.nextLine());
                check = false;
            }
            catch (Exception e) {
                System.out.println("메뉴를 다시 입력하세요.");
                check = true;
            }
        }
        
        return menu;
    } // end of getInputMenu methods
    
    // 문자열 입력 받고, 빈 입력은 허용하지 않기
    private static String getInput(String msg, String errorMsg) {
        boolean check = true;
        
        String buff = "";
        while (check) {        
            System.out.print(msg);
            buff = scanner.nextLine();
            
            // errorMsg가 공백이면 빈 입력 허용하겠다는 뜻
            if (buff.isBlank() && !errorMsg.isBlank()) {
                System.out.println(errorMsg);
            }
            else {
                check = false;
            }
        }
        
        return buff;
    } // end of getInput methods
    
    // 정수를 안전(?)하게 입력 받기
    private static int getInputInt(String msg, String errorMsg) {
        boolean check = true;
        
        int buff = 0;
        while (check) {
            try {            
                System.out.print(msg);
                buff = Integer.parseInt(scanner.nextLine());
                check = false;
            }
            catch (Exception e) {
                System.out.println(errorMsg);
                check = true;
            }
        }
        
        return buff;
    } // end of getInputInt methods
    
    // 상품 목록 출력
    private static void showList(MethodExe2 m2) {
        String search = getInput("검색할 상품이름을 입력하세요.\nALL을 입력하면 전체 상품을 조회합니다. >> ", "\n상품이름을 꼭 입력해야합니다.\n");
        List<Product> list = m2.productList(new Product("", search, 0));
        
        System.out.println("상품코드 ┃ 상품명 ┃ 가격");
        for (Product p : list) {
            if (p != null)
                System.out.println(p.getProductCode() + " ┃ " + p.getProductName() + " ┃ " + p.getPrice());
        }
    } // end of showList methods
    
    // 상품 추가
    private static void addProduct(MethodExe2 m2) {
        String productCode = getInput("상품코드를 입력하세요. >> ", "\n상품코드를 꼭 입력해야합니다.\n");
        String productName = getInput("상품이름을 입력하세요. >> ", "\n상품이름을 꼭 입력해야합니다.\n");
        int price = getInputInt("상품가격을 입력하세요. >> ", "\n상품가격을 다시 입력해주세요.\n");
        
        Product product = new Product(productCode, productName, price);
        
        if (m2.add(product)) {
            System.out.println("상품 추가에 성공했습니다.");
        }
        else {
            System.out.println("상품 추가에 실패했습니다.");
        }
    } // end of addProduct methods

    // 상품 수정
    private static void modifyProduct(MethodExe2 m2) {
        String productCode = getInput("상품코드를 입력하세요. >> ", "\n상품코드를 꼭 입력해야합니다.\n");
        String productName = getInput("상품이름을 입력하세요.\n 변경을 원하지 않으면 엔터키를 입력하세요. >> ", "");
        int price = getInputInt("상품가격을 입력하세요. \n 변경을 원하지 않으면 0을 입력하세요. >> ", "\n상품가격을 다시 입력해주세요.\n");
        
        Product modify;
        if (productName.isBlank())
            modify = new Product(productCode, null, price);
        else
            modify = new Product(productCode, productName, price);
        
        if (m2.modify(modify)) {
            System.out.println("상품 수정에 성공했습니다.");
        }
        else {
            System.out.println("상품 수정에 실패했습니다.\n등록된 상품이 없거나 처리 과정 중 오류가 발생했습니다.");
        }
    } // end of modifyProduct methods
    
    // 상품 삭제
    private static void removeProduct(MethodExe2 m2) {
        String productCode = getInput("상품코드를 입력하세요. >> ", "상품코드를 꼭 입력해야합니다.");

        if (m2.remove(productCode)) {
            System.out.println("상품 삭제에 성공했습니다.");
        }
        else {
            System.out.println("상품 삭제에 실패했습니다.\n없는 상품이거나 처리 과정 중 오류가 발생했습니다.");
        }
    } // end of removeProduct methods
    
    static void officeApp() {
        MethodExe2 m2 = new MethodExe2();
        
        // 사용자 입력을 받아 1. 목록 2. 추가 3. 수정 4. 삭제 9. 종료 구현
        // 입력메시지
        boolean run = true;

        while (run) {            
            int menu = getInputMenu();
            
            switch (menu) {
            case 1: // 목록
                showList(m2);
                break;
            case 2: //추가
                addProduct(m2);
                break;
            case 3: // 수정
                modifyProduct(m2);
                break;
            case 4: // 삭제
                removeProduct(m2);
                break;
            case 9: // 종료
                m2.save();
                System.out.println("종료");
                run = false;
                break;
            default:
                System.out.println("메뉴를 다시 입력하세요.");
            }
        }
        
        System.out.println("Exit officeApp");
    }
    
    public static void main(String[] args) {
        officeApp();
    } // end of main methods
}
