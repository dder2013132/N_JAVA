package com.yedam.classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MethodExe2 {
    private List<Product> store;

    MethodExe2() { init(); }
    
    private void init() {
        store = new ArrayList<Product>();
        
        try {
            FileInputStream fis = new FileInputStream("c:/temp/store.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            store = (List<Product>) ois.readObject();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        try {
//            Scanner scn = new Scanner(new FileInputStream("C:/temp/product.txt"));
//            while (true) {
//                String[] product = scn.nextLine().split(" ");
//                store.add(new Product(product[0], product[1], Integer.parseInt(product[2])));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchElementException e) {
//            
//        }
        
//        store.add(new Product("A001", "지우개", 500));
//        store.add(new Product("B001", "샤프1000", 1000));
//        store.add(new Product("C001", "연필500", 800));
//        store.add(new Product("D001", "지우개", 1000));
    }

    boolean add(Product prd) {
        return store.add(prd);
    } // end of add methods
    
    List<Product> productList(Product prd) { 
        List<Product> list = new ArrayList<Product>();
        for (int i = 0; i < store.size(); i++) {
            if (prd.getProductName().equals("ALL") || store.get(i).getProductName().equals(prd.getProductName())) {
                if (store.get(i).getPrice() >= prd.getPrice())
                    list.add(store.get(i));
            }
        }
        
        return list;
    } // end of productList methods
    
    boolean remove(String code) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getProductCode().equals(code)) {
                store.remove(i);
                return true;
            }
        }
        
        return false;
    } // end of remove methods
    
    boolean modify(Product prod) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getProductCode().equals(prod.getProductCode())) { 
                
                // 상품명 수정
                if (prod.getProductName() != null) {
                    store.get(i).setProductName(prod.getProductName());
                }
                
                if (prod.getPrice() != 0) {
                    store.get(i).setPrice(prod.getPrice());
                }
                
                return true;
            }
        }
        
        return false;
    } // end of modify methods
    
    void save() {
        try {
            FileOutputStream fos = new FileOutputStream("C:/temp/store.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(store);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        try {
//            Writer writer = new FileWriter("C:/temp/product.txt");
//            for (Product p : store) {
////            while (true) {
//                String product = p.showList() + "\n";
//                writer.write(product);
//                writer.flush();
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
