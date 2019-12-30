// 인스턴스 초기화 블록, 필드 초기화 문장, 생성자의 실행 순서
package com.eomcs.oop.ex03;

public class Exam0631 {

  static class A {
    A() {
      // 인스턴스 초기화 블록이나 필드 초기화 문장이 있다면
      // 다음과 같이 선언된 순서대로 생성자 첫 부분에 복사된다.
      // a = 200;
      // System.out.println("초기화 블록");
      // a = 100;
      System.out.println("A()");
    }

    A(int a) {
      // a = 200;
      // System.out.println("초기화 블록");
      // a = 100;
      System.out.println("A(int a)");
      this.a = a;
    }

    // 인스턴스 초기화 블록(initializer block)
    {
      a = 200;
      System.out.println("초기화 블록");
    }
    
    // 필드 초기화 문장(variable initializer)
    int a = 100;
  }

  public static void main(String[] args) {
    A obj1 = new A();
    System.out.println(obj1.a);
    System.out.println("-------------");
    A obj2 = new A(1111);
    System.out.println(obj2.a);
  }
}





