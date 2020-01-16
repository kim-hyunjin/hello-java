// static nested class
package com.eomcs.oop.ex11.a;

class Exam0112_x {
  static int sValue;
  
  static class A {
    void m1() {
      
    }
  }
}

public class Exam0112 {
  public static void main(String[] args) {
    // 다른 클래스의 스태틱 멤버를 클래스 이름을 이용하여 사용할 수 있듯이,
    Exam0112_x.sValue = 100;
    // 그 클래스의 static nested class 또한 같은 방식으로 사용할 수 있다.
    Exam0112_x.A obj;
    obj = new Exam0112_x.A();
    
  }

}
