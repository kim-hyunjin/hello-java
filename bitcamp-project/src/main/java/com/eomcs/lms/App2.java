package com.eomcs.lms;

import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);
    
    System.out.print("번호를 입력하세요: ");
    String number = keyboard.nextLine();
    
    System.out.print("이름을 입력하세요: ");
    String name = keyboard.nextLine();
    
    System.out.print("이메일을 입력하세요: ");
    String email = keyboard.nextLine();
    
    System.out.print("암호를 입력하세요: ");
    String password = keyboard.nextLine();
    
    System.out.print("사진을 입력하세요: ");
    String photo = keyboard.nextLine();
    
    System.out.print("전화번호를 입력하세요: ");
    String phoneNumber = keyboard.nextLine();
    
    System.out.println();
    
    System.out.printf("번호: %s\n", number);
    System.out.printf("이름: %s\n", name);
    System.out.printf("이메일: %s\n", email);
    System.out.printf("암호: %s\n", password);
    System.out.printf("사진: %s\n", photo);
    System.out.printf("전화번호: %s\n", phoneNumber);
    System.out.println("가입일: 2019-01-01");
    
    keyboard.close();
  }
}
