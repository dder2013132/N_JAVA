package com.yedam.bookApp;

public class Book {
    // Fields
    private String title;
    private String author;
    private String publisher;
    
    private int    price;
    private int    orderNo;
    
    private String bookCode; // book 탐색용
    
    // Constructors
    public Book() { }
    
    public Book(String title, String author, String publisher, int price) {
        this.title = title; // this: 생성된 현재 인스턴스
        this.author = author;
        this.publisher = publisher;
        this.price = price;        
    }
    
    public Book(String title, String author, String publisher, int price, int orderNo) {
//        this.title = title;
//        this.author = author;
//        this.publisher = publisher;
//        this.price = price;
        this(title, author, publisher, price); // 해당 매개변수들을 이용하는 생성자 호출
        this.orderNo = orderNo;
    }
    
    // Methods
    public String showBookInfo() {
        String msg = "NO. " + bookCode + " ┃ 제목: " + title + " ┃ 저자: " + author + " ┃ 출판사: " + publisher + " ┃ 가격: " + price;
        return msg;
    }
    
    public String showList() {
        return "┃ " + bookCode + " ┃ " + title + " ┃ " + author + " ┃ " + price + " ┃";
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        if (author.equals(""))
            return;
        this.author = author;    
    }
    
    public void setPublisher(String publisher) {
        if (publisher.equals(""))
            return;
        this.publisher = publisher;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getAuthor() {
        return this.author;
    }
    
    public String getPublisher() {
        return this.publisher;
    }
    
    public int getPrice() {
        return this.price;
    }
    
    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
    
    public String getBookCode() {
        return bookCode;
    }
    
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }
}
