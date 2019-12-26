package com.eomcs.oop.ex02.study;

public class Test01 {
  public static void main(String[] args) {
    Score s = new Score();
    s.name = "홍길동";
    s.kor = 100;
    s.eng = 100;
    s.math = 80;
    
    s.calculate();
    
    System.out.println(s.sum);
    System.out.println(s.avg);
    
  }
}
