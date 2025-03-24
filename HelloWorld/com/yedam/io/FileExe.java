package com.yedam.io;

import java.io.File;
import java.io.FileWriter;

public class FileExe {
    public static void main(String[] args) {
        File file = new File("c:/temp/new.txt");
        File file1 = new File("c:/temp/images/img1/new");
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("생성됨");
            }
            if (!file1.exists()) {
                file1.mkdirs();
                System.out.println("폴더생성됨");
            }
            FileWriter fw = new FileWriter(file);
            fw.write("Hello\n");
            fw.write("World\n");
            fw.flush();
            fw.close();
            if(file.exists()) {
                file.delete();
                System.out.println("deleted");
            }
        } catch (Exception e) {
            
        }

        System.out.println("end of prog..");
    }
}
