package com.eomcs.oop.ex08.test;

import com.eomcs.oop.ex08.test.J4.Member;

public class J4 {
  // 서브 클래스에서 반드시 재정의해야 하는 메서드는
  // 문법으로 강제시킬 수 있다. => 추상메서드
  
  abstract class Member {
    String email;
    String password;
    String name;
    String tel;

    abstract void print(); // 추상메서드는 구현되지 않았기 때문에 호출불가.
    // 추상클래스(또는 인터페이스)만 추상 메서드를 가질 수 있다.
    // 서브클래스가 구현하도록 강제시킨다.
  }

  // super 클래스의 추상 메서드를 구현하지 않으면 에러
  // 구현되지 않은 추상메서드가 있다면 추상클래스가 되어야 한다.
  abstract class Student extends Member {
    int grade;
    boolean working;

    }
  }

  class Teacher extends Member {
    int pay;
    String major;

    @Override
    void print() {
      System.out.println("강사정보");
    }
  }
}