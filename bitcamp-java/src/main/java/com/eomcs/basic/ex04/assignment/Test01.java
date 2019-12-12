// 과제 1 : 계산기 애플리케이션을 작성하라.
// - 실행
// 값1? 10
// 값2? 20
// 연산자(+,-,*,/)? +
// => 10 + 20 = 30 
//
package com.eomcs.basic.ex04.assignment;

import java.util.Scanner;

public class Test01 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    System.out.print("값1? ");
    double a = keyboard.nextDouble();

    System.out.print("값2? ");
    double b = keyboard.nextDouble();

    System.out.print("연산자(+,-,*,/)? ");
    String s = keyboard.next();

    switch (s) {
      case "+": a = a + b;
      break;
      case "-": a = a - b;
      break;
      case "*": a = a * b;
      break;
      case "/": a = a / b;
      break;
    }
    if (a == (long)a) {
      System.out.printf("=> a %s b = %d", s, (long)a);
    } else {
      System.out.printf("=> a %s b = %12.4f", s, a);
    }
    

  }
}
