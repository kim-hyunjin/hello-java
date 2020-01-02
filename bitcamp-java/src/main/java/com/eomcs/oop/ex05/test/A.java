package com.eomcs.oop.ex05.test;

public class A {
  int v1;
  A() {
    this(100); // 이 클래스의 다른 생성자 호출. 반드시 가장 첫번째 문장이어야 한다.
    System.out.println("A()");
  
  }
  A(int v1) {
    this.v1 = v1;
    System.out.println("A(int)");
  }
  
  
}
