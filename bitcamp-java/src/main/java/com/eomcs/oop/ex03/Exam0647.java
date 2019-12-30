// 실행순서 종합
package com.eomcs.oop.ex03;

public class Exam0647 {

  public static class A {
    static int a;
    static void m() {}
    
    A() {
      System.out.println("생성자");
    }
    
    {
      System.out.println("인스턴스 블록 1");
    }

    static {
      System.out.println("스태틱 블록 1");
    }

    {
      System.out.println("인스턴스 블록 2");
    }
    
    static {
      System.out.println("스태틱 블록 2");
    }
  }

  public static void main(String[] args) throws Exception {
    new A();
    System.out.println("----------------");
    new A();    // 인스턴스 블록만 새로 객체를 만들때마다 실행된다.
  }
}





