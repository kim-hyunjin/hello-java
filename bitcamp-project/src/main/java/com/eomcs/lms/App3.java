package com.eomcs.lms;

import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {
    
    Scanner keyboard = new Scanner(System.in);
    
    System.out.print("번호를 입력하세요: ");
    String number = keyboard.nextLine();
    
    System.out.print("내용을 입력하세요: ");
    String content = keyboard.nextLine();
    
    System.out.println();
    
    System.out.printf("번호: %s\n", number);
    System.out.printf("내용: %s\n", content);
    System.out.println("작성일: 2019-01-01");
    System.out.println("조회수: 0");
    
    keyboard.close();
  }

}
