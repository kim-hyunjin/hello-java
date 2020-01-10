package com.eomcs.corelib.ex01;

public class Exam0223 {
  static class Member {
    String name;
    String tel;

    @Override
    public String toString() {
      return name + "," + tel;
    }
  }

  public static void main(String[] args) {
    int i = 100;
    Member m = new Member();
    m.name = "홍길동";
    m.tel = "010-1111-2222";
    
    String str = new String("Hello");
    
    // 자바가 wrapper 클래스를 통한 auto-boxing, auto-unboxing 기능을 제공하므로
    // primitive data type 또는 클래스의 인스턴스 구분없이 값을 저장할 수 있다.
    Object obj;
    obj = m;
    obj = str;
    
    obj = i;
    Integer obj2 = (Integer)obj;
    
    System.out.println(i);
    System.out.println(obj);
    System.out.println(obj2);
  }
}






