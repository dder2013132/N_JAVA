package com.yedam.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.yedam.classes.Product;

public class ObjectStreamexe {
    static void write() {
        List<Product> list = new ArrayList<Product>();

        list.add(new Product("A001", "연필", 1000));
        list.add(new Product("B001", "지우개", 500));

        try {
            FileOutputStream fos = new FileOutputStream("C:/temp/object.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("c:/temp/object.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            List<Product> list = (List<Product>) ois.readObject();
            for (Product p : list) {
                System.out.println(p.showList());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("EOP");
    }
}
