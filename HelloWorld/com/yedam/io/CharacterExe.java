package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CharacterExe {
    static void write() {
        // 출력스트림(문자)
        try {
            Writer writer = new FileWriter("C:/temp/data.txt");
            writer.write('감');
            writer.write('귤');
            writer.write('배');

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void read() {
        // 입력스트림(문자)
        try {
            Reader reader = new FileReader("./src/com/yedam/io/StreamExe.java");
            while (true) {
                int data = reader.read();
                if (data == -1)
                    break;
                System.out.print(String.format("%c", data));
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void write2() {
        Scanner scn = new Scanner(System.in);
        
        try {
            Writer writer = new FileWriter("C:/temp/message.txt");
            while (true) {
                System.out.print("입력 >> ");
                String msg = scn.nextLine();
                if (msg.equals("quit")) break;
                writer.write(msg);
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scn.close();
    }

    public static void main(String[] args) {
        Scanner scn = null;
        
        try {
            scn = new Scanner(new FileInputStream("C:/temp/product.txt"));
            while (true) {
                String msg = scn.nextLine();
                String[] msgArr = msg.split(" ");
                System.out.println("상품코드: " + msgArr[0] + " 상품이름: " + msgArr[1] + "\t 가격: " + msgArr[2]);
            }
        } catch (IOException e) {
            
        } catch (NoSuchElementException e) {
            
        }
        scn.close();
        
        System.out.println("end of program");
    }
}
