package com.eomcs.corelib.ex01;

public class Exam0224 {
  static class Member {
    String name;
    String tel;

    @Override
    public String toString() {
      return name + "," + tel;
    }
  }

  public static void main(String[] args) {
    // print()처럼 primitive 데이터 타입과 클래스를 구분하여 정의해야 한다.
    print(100);
    System.out.println();
    print(new Member());
    System.out.println();
    
    // auto-boxing, auto-unboxing 기능이 있기 때문에
    // printObj() 처럼 한개의 메서드로
    // primitive 데이터 타입과 클래스를 모두 처리할 수 있다.
    printObj(100);
    printObj(new Member());
    // 파라미터 타입이 Object이면 자바 컴파일러는 auto-boxing 코드로 변환한다.
    // 즉 Integer.valueOf(100)으로 바뀐다.

  }
  static void print(int i) {
    System.out.print("정수: ");
    System.out.print(i);
  }
  
  static void print(Member m) {
    System.out.print("회원: ");
    System.out.print(m);
  }
  
  static void printObj(Object o) {
    System.out.println(o);
  }
  
}






