// 서브 인터페이스 구현 - 수퍼 인터페이스의 메서드까지 모두 구현해야 한다.
package com.eomcs.oop.ex09.c;

public class Exam01 implements B {
  public void m2() {} // B 인터페이스 뿐만아니라,
  public void m1() {} // B의 수퍼인터페이스의 메서드까지 구현해야 한다.

  // 클래스에서 추가한 메서드
  public void x1() {}
  public void x2() {}

  public static void main(String[] args) {
    Exam01 obj = new Exam01();

    // 클래스 타입 레퍼런스는 해당 클래스에 정의된 메서드를 호출할 수 있다.
    obj.m1();
    obj.m2();
    obj.x1();
    obj.x2();

    // 인터페이스 레퍼런스는 해당 인터페이스에 선언된 메서드만 호출할 수 있다.
    B objB = obj;
    objB.m2(); // B의 m2()
    objB.m1(); // B가 상속받은 A의 m1()
    // objB.x1();
    // objB.x2();

    A objA = obj;
    objA.m1(); // A의 m1()
    // objA.m2();

  }
}
