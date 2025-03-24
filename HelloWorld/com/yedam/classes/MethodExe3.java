package com.yedam.classes;

public class MethodExe3 {
    String gugudan(int num) {
        String gugu = "";
        for (int i = 1; i <= 9; i++) {
            // System.out.println(num + " x " + i + " = " + (num * i));
            gugu += num + " x " + i + " = " + (num * i) + "\n";
        }
        // System.out.println();
        gugu += "\n";
        return gugu;
    }
    
    String gugudan(int num, int toNum) {
        String gugu = "";
        for (int i = num; i <= toNum; i++) {
            for (int j = 1; j <= 9; j++) {
                // System.out.println(i + " x " + j + " = " + (i * j));
                gugu += i + " x " + j + " = " + (i * j) + "\n";
            }
            // System.out.println();
            gugu += "\n";
        }
        return gugu;
    }
    
    void printStar(int cnt, String str) {
        for (int i = 0; i < cnt; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(str);
            }
            System.out.println();
        }
    }
    
    // 1 ~ 16 까지의 임의의 수를 중복 없이 할당
    void printCard() {
        int[] intArr = new int[16];
        boolean[] ck = new boolean[16];
        boolean run = true;
        int temp = 0;
        
        for (int i = 0; i < intArr.length; i++) {
            run = true;
            while (run) {
                temp = (int) (Math.random() * 16) + 1;
                if (!ck[temp - 1]) {
                    intArr[i] = temp;
                    run = false;
                    ck[temp - 1] = true;
                }
            }
        }
        
        for (int i = 0; i < intArr.length; i++) {
            System.out.printf("%3d", intArr[i]);
            if (i % 4 == 3) {
                System.out.println();
            }
        }
    }
    
    void printReverseStar(int cnt, String str) {
        for (int i = 0; i < cnt; i++) {
            for (int j = cnt - i - 1; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(str);
            }
            System.out.println();
        }
    }
    
    void printPyramidStar(int cnt, String str) {
        for (int i = 0; i < cnt; i++) {
            for (int j = cnt - i - 1; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(str);
            }
            for (int j = 0; j < i; j++) {
                System.out.print(str);
            }
            System.out.println();
        }
    }
    
    void printDiamondStar(int cnt, String str) {
        for (int i = 0; i < cnt / 2 + 1; i++) {
            for (int j = cnt / 2 - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(str);
            }
            for (int j = 0; j < i; j++) {
                System.out.print(str);
            }
            System.out.println();
        }
        
        for (int i = cnt / 2 + 1; i < cnt; i++) {
            for (int j = i; j >= cnt / 2 + 1; j--) {
                System.out.print(" ");
            }
            for (int j = i; j < cnt; j++) {
                System.out.print(str);
            }
            for (int j = i; j < cnt - 1; j++) {
                System.out.print(str);
            }
            System.out.println();
        }
    }
}
