/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Scanner;

/**
 *
 * @author reality
 */
public class MyUtil {

    private static Scanner sc = new Scanner(System.in);
    
    // Hàm xử lý việc nhập dữ liệu là chuỗi ký tự,
    // In ra thông báo và yêu cầu người dùng nhập vào ký tự, không được để trống
    public static String getString(String inputMsg, String errorMsg) {
        while (true) {
            try {
                String s;
                System.out.print(inputMsg);
                s = sc.nextLine().trim();
                if (s.length() == 0 || s.isEmpty())
                    throw new Exception();
                return s;

            } catch (Exception e) {
                System.out.print(errorMsg);
            }

        }

    }
    // Hàm yêu cầu người dùng nhập số thực lớn hơn 0
    public static double getADouble(String inputMsg, String errorMSg) {
        while (true) {
            try {
                double n;
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < 0)
                    throw new Exception();
                return n;
            } catch (Exception e) {
                System.out.print(errorMSg);
            }
            
            
        }
        
    }

}
