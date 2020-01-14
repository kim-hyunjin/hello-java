// 인터페이스 - caller와 callee 사이의 호출 규칙을 정의하는 문법
package com.eomcs.oop.ex09.a.after;

// caller : Exam01
// callee : Worker
// 문법 interface 사용규칙명 {...}
public interface Worker {

  // 호출 규칙?
  // => 메서드 형식을 의미한다.
  // => 메서드의 몸체는 정의하지 않는다.
  // => 메서드의 몸체는 이 규칙에 따라 만드는 클래스에서 정의하는 것이다.
  // 문법
  // => public abstract 리턴타입 메서드명(파라미터);
  // => public abstract 모두 생략 가능
  // => public 외에 다른 접근 범위는 사용할 수 없다.(규칙은 공개되어야 한다)
  void execute();
}
