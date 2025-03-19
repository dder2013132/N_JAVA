package com.yedam.bookApp;

import java.util.List;
import java.util.Scanner;

public class BookMain {
    // TODO field area

    //    private static final int MAX_BOOK_STORE_SIZE = 100; 배열 사용했을 때 코드
    
    private static final int MAX_USER_SIZE = 3;
    
    // singleton
    private static BookMain bookMain = new BookMain();
    
    private User[] users = new User[MAX_USER_SIZE];
    private Scanner scanner = new Scanner(System.in);
    
    private BookJdbc dao = new BookJdbc(); 
    
    /*
     * 배열 사용했을 때 코드
    private Book[] bookStore = new Book[MAX_BOOK_STORE_SIZE];
    private int lastIdx;
    private int bookStoreSize;
    private int bookNo;           // a last book's order number
    */
    
    // private constructor
    private BookMain() { init(); }
    
    // TODO method area
    
    // get singleton
    public static BookMain getInstance() {
        return bookMain;
    }
    
    /*
     * 배열 사용했을 때 코드
    private boolean isDuplicateTitle(String title) {
        for (int i = 0; i < bookStoreSize; i++) {
            if (bookStore[i].getTitle().equals(title))
                return true;
        }
        
        return false;
    }

    private int findBookIdxByTitle(String title) {
        for (int i = 0; i < bookStoreSize; i++) {
            if (bookStore[i].getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    } // end of findBookIdxByTitle

    private int[] findBookIndexesByPublisher(String publisher) {
        int[] idxList = new int[bookStoreSize];

        for (int i = 0; i < bookStoreSize; i++) {
            idxList[i] = -1;
        }

        int idx = 0;
        for (int i = 0; i < bookStoreSize; i++) {
            if (bookStore[i].getPublisher().equals(publisher)) {
                idxList[idx] = i;
                idx++;
            }
        }
        return idxList;
    } // end of findBookIndexesByPublisher
    */

    private void init() {
        /*
         * 배열 사용했을 때 코드
        bookStore[0] = new Book("이것이자바다", "신용권", "한빛", 20000, 1);
        bookStore[1] = new Book("자바스크립트", "박기초", "우리", 26000, 2);
        bookStore[2] = new Book("HTML/CSS", "김하늘", "가람", 25000, 3);
        bookStore[3] = new Book("이것이자바다2", "신용권", "한빛", 20000, 4);
        bookStore[4] = new Book("자바스크립트2", "박기초", "우리", 26000, 5);
        bookStore[5] = new Book("HTML/CSS2", "김하늘", "가람", 25000, 6);
        */

        users[0] = new User("abc", "User", "1231");
        users[1] = new User("qwer", "ty", "1232");
        users[2] = new User("green", "star", "1233");
        
        /*
         * 배열 사용했을 때 코드
        lastIdx = 5;
        bookStoreSize = 6;
        bookNo = 6;
        */
    } // end of initialize
    
    private int getMenuSelection() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    } // end of getMenuSelection

    public void registerBook() {
        /*
         * 배열 사용했을 때 코드
        if (bookStoreSize == MAX_BOOK_STORE_SIZE) {
            System.out.println("도서가 가득 찼습니다.");
            return;
        }
        
        String title = getInput("책 제목 입력 >> ", "책 제목은 반드시 입력해야 합니다.");
        
        if (isDuplicateTitle(title)) {
            System.out.println("이미 등록된 책입니다.");
            return;
        }
        
        String author = getInput("저자 입력 >> ", "저자를 입력해주세요.");
        String publisher = getInput("출판사 입력 >> ", "출판사를 입력해주세요.");
        int price = getIntInput("가격 입력 >> ", "잘못된 입력 감지. 다시 입력해주세요.");
        
        bookStore[++lastIdx] = new Book(title, author, publisher, price, ++bookNo);
        bookStoreSize++;
        
        System.out.println("등록이 완료되었습니다.");        
        */
        
        String title = getInput("책 제목 입력 >> ", "책 제목은 반드시 입력해야 합니다.");
        String author = getInput("저자 입력 >> ", "저자를 입력해주세요.");
        String publisher = getInput("출판사 입력 >> ", "출판사를 입력해주세요.");
        int price = getIntInput("가격 입력 >> ", "잘못된 입력 감지. 다시 입력해주세요.");
        
        if (dao.insert(new Book(title, author, publisher, price))) {
            System.out.println("등록이 완료되었습니다.");
        }
        else {
            System.out.println("등록과정 중 예외가 발생되었습니다.");
        }
        
    } // end of registerBook

    private void updateBook() {
        /*
         * 배열 사용했을 때 코드
        if (bookStoreSize == 0) {
            System.out.println("등록된 책이 없습니다.");
            return;
        }
        
        String title = getInput("수정할 책 제목 입력 >> ", "책 제목은 반드시 입력해야 합니다.");
        int findIdx = findBookIdxByTitle(title);
        
        if (findIdx < 0) {
            System.out.println("해당 도서가 없습니다.");
            return;
        }
        
        String author = getInput("저자 입력 >> ", "저자명을 입력해주세요.");
        bookStore[findIdx].setAuthor(author);
        
        String publisher = getInput("출판사 입력 >> ", "출판사를 입력해주세요.");
        bookStore[findIdx].setPublisher(publisher);
        
        int price = getIntInput("가격 입력 >> ", "잘못된 입력 감지. 다시 입력해주세요.");
        bookStore[findIdx].setPrice(price);
        
        System.out.println("수정이 완료되었습니다.");
        */
        
        Book book = new Book();
        String bookCode = getInput("수정할 책의 도서코드 입력 >> ", "책 코드를 반드시 입력해야 합니다.");
        book.setBookCode(bookCode);
        
        String title = getInput("제목 입력 >> ");
        book.setTitle(title);
        
        String author = getInput("저자 입력 >> ");
        book.setAuthor(author);
        
        int price = getIntInput("가격 입력 >> ", "잘못된 입력 감지. 다시 입력해주세요.");
        book.setPrice(price);
        
        if (dao.update(book)) {        
            System.out.println("수정이 완료되었습니다.");
        }
        else {
            System.out.println("수정과정 중 예외가 발생되었습니다.");            
        }
    } // end of updateBook

    private void deleteBook() {
        /*
         * 배열 사용했을 때 코드
        if (bookStoreSize == 0) {
            System.out.println("등록된 책이 없습니다.");
            return;
        }
        
        String title = getInput("삭제할 책 제목 입력 >> ", "책 제목은 반드시 입력해야 합니다.");
        int findIdx = findBookIdxByTitle(title);
        
        if (findIdx < 0) {
            System.out.println("해당 도서가 없습니다.");
            return;
        }
        
        for (int i = findIdx; i < bookStoreSize - 1; i++) {
            bookStore[i] = bookStore[i + 1];
            bookStore[i].setOrderNo(i + 1);
        }
        
        bookStore[--bookStoreSize] = null;
        lastIdx--;
        bookNo--;
        */
        
        String bookCode = getInput("삭제할 책의 도서코드 입력 >> ", "도서코드는 반드시 입력해야 합니다.");
        
        if (dao.delete(bookCode)) {
            System.out.println("삭제가 완료되었습니다.");
        }
        else {
            System.out.println("삭제과정 중 예외가 발생되었습니다.");            
        }
        
    } // end of deleteBook

    // 전체 목록 조회
    public void listBooks() {
        /*
         * 배열 사용했을 때 코드
        if (bookStoreSize == 0) {
            System.out.println("등록된 책이 없습니다.");
            return;
        }
        
        System.out.println("┏━━━━━┳━━━━━━━━━━━━┳━━━━━━━┳━━━━━━━━┓");
        System.out.println("┃ No. ┃ 제목       ┃ 저자  ┃ 가격   ┃");
        System.out.println("┣━━━━━┻━━━━━━━━━━━━┻━━━━━━━┻━━━━━━━━┫");
        for (int i = 0; i < bookStoreSize; i++) {
            System.out.println(bookStore[i].showList());
        }
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        */
        
        List<Book> list = dao.list("");
        
        System.out.println("┏━━━━━┳━━━━━━━━━━━━┳━━━━━━━┳━━━━━━━━┓");
        System.out.println("┃ No. ┃ 제목       ┃ 저자  ┃ 가격   ┃");
        System.out.println("┣━━━━━┻━━━━━━━━━━━━┻━━━━━━━┻━━━━━━━━┫");
        for (Book book : list) {
            System.out.println(book.showList());
        }
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
    } // end of listBooks
    
    // 책의 상세 정보 조회
    private void showDetailBooks() {
        /*
         * 배열 사용했을 때 코드
        if (bookStoreSize == 0) {
            System.out.println("등록된 책이 없습니다.");
            return;
        }
        
        String title = getInput("조회할 책 제목 입력 >> ", "책 제목은 반드시 입력해야 합니다.");
        int findIdx = findBookIdxByTitle(title);
        
        if (findIdx < 0) {
            System.out.println("해당 도서가 없습니다.");
            return;
        }
        
        System.out.println("\n" + bookStore[findIdx].showBookInfo() + "\n");
        */
        
        String bookCode = getInput("조회할 책의 도서코드 입력 >> ", "도서코드는 반드시 입력해야 합니다.");
        Book book = dao.select(bookCode);
        
        if (book == null) {
            System.out.println("해당 도서가 없습니다.");
        }

        System.out.println("\n" + book.showBookInfo() + "\n");
        
    } // end of showDetailBooks
    
    // 입력한 출판사의 모든 책 조회, 출판사를 입력하지 않으면 모든 출판사별 책 조회
    private void publisherListBooks() {
        /*
         * 배열 사용했을 때 코드
        String publisher = getInput("출판사 이름을 입력 >> ");
        
        Book search = new Book();
        search.setPublisher(publisher);
        int[] findIdx = findBookIndexesByPublisher(search.getPublisher());

        if (findIdx[0] < 0) {
            System.out.println("해당 출판사의 책이 없습니다.");
        }

        for (int i = 0; i < findIdx.length && findIdx[i] >= 0; i++) {
            System.out.println(bookStore[findIdx[i]].showBookInfo());
        }
         */
        
        String publisher = getInput("출판사 이름을 입력 >> ");
        List<Book> list = dao.list(publisher);
        
        if (list.size() == 0) {
            System.out.println("해당 출판사의 책이 없습니다.");
        }
        
        for (Book book : list) {
            System.out.println(book.showBookInfo());
        }


    } // end of publisherListBooks
    
    // 공백값을 허용하는 입력
    private String getInput(String prompt) {
        System.out.print("\n" + prompt);
        String input = scanner.nextLine().trim();
        return input;
    }
    
    /*
     *  공백값을 허용하지 않는 입력
     *  공백값이 들어온다면 오류 메시지와 함께 계속 입력을 받음.
     */
    private String getInput(String prompt, String errorMessage) {
        while (true) {
            System.out.print("\n" + prompt);
            String input = scanner.nextLine().trim();
            if (!input.isBlank()) {
                return input;
            }
            System.out.println("\n" + errorMessage + "\n");
        }
    } // end of getInput

    /*
     *  안전(?)한 정수타입 입력.
     *  정수타입이 들어올 때 까지 계속 입력을 받음.
     */
    private int getIntInput(String prompt, String errorMessage) {
        while (true) {
            try {
                System.out.print("\n" + prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("\n" + errorMessage + "\n");
            }
        }
    } // end of getIntInput

    private boolean login(String id, String pw) {
        for (User user : users) {
            if (user != null) {
                if (user.getUserId().equals(id) && user.getPassword().equals(pw)) {
                    System.out.println("\n" + user.getUserName() + "님 환영합니다.\n");
                    return true;
                }
            }
        }

        return false;
    }
    
    public void main(String[] args) {
        String id = getInput("아이디 입력 >> ", "아이디를 입력해주세요!!");
        String pw = getInput("비밀번호 입력 >> ", "비밀번호를 입력해주세요!!");

        if (login(id, pw)) {
            boolean run = true;

            while (run) {            
                System.out.println("┏━━━━━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━┓");
                System.out.println("┃ 1. 도서등록 ┃ 2. 수정 ┃ 3. 삭제 ┃ 4. 목록 ┃ 5. 상세 조회 ┃ 6. 상세 목록 ┃ 9. 종료 ┃");
                System.out.println("┗━━━━━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━┛");
                System.out.print("메뉴 선택 >> ");
                
                int menu = getMenuSelection();
                
                switch (menu) {
                    case 1:
                        registerBook();
                        break;
                    case 2:
                        updateBook();
                        break;
                    case 3:
                        deleteBook();
                        break;
                    case 4:
                        listBooks();
                        break;
                    case 5:
                        showDetailBooks();
                        break;
                    case 6:
                        publisherListBooks();
                        break;
                    case 9:
                        run = false;
                        break;
                    default:
                        System.out.println("올바른 메뉴를 선택하세요.");
                }
            }
        }
        else {
            System.out.println("등록된 사용자가 아닙니다.");
        }
        
        scanner.close();
        System.out.println("Exit program");
    } // end of main

}
