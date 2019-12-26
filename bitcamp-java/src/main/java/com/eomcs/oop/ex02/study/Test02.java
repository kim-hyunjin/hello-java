package com.eomcs.oop.ex02.study;

public class Test02 {
  public static void main(String[] args) {
    /*
    int a = 700, b = 150;
    System.out.printf("%d + %d = %d\n", a, b, Calculator.plus(a, b));
    System.out.printf("%d - %d = %d\n", a, b, Calculator.minus(a, b));
    System.out.printf("%d * %d = %d\n", a, b, Calculator.multiple(a, b));
    System.out.printf("%d / %d = %d\n", a, b, Calculator.divide(a, b));
    */
    
    //  ((2 + 3 - 1) * 7) / 3 = ?
    Calculator.plus(2);
    Calculator.plus(3);
    Calculator.minus(1);
    Calculator.multiple(7);
    Calculator.divide(3);
    
    System.out.printf("결과: %d\n", Calculator.result);
    
    
    
  }

}
