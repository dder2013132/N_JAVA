package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamExe {
    static void write() {
        // 출력스트림
        try {
            OutputStream os = new FileOutputStream("C:/temp/data.bin");
            os.write(72); // H
            os.write(69); // E
            os.write(76); // L
            os.write(76); // L
            os.write(79); // O
            os.flush();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    static void read() {
        // 입력 스트림
        try {
            InputStream is = new FileInputStream("C:/temp/data.bin");
            while (true) {
                int data = is.read();
                if (data == -1) {
                    break;
                }
                System.out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void copy() {
        // 복사
        try {
            InputStream is = new FileInputStream("C:/temp/outer-space-background.jpg");
            OutputStream os = new FileOutputStream("C:/temp/outer-space-background3.jpg");
            
            byte[] buf = new byte[100];
            while (true) {
                int data = is.read(buf);
                if (data == -1) {
                    break;
                }
                os.write(buf);
                os.flush();
            }
            
            is.close();
            os.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


        System.out.println("end of programs");
    } // end of main
}
