// Wrapper 클래스 - auto-boxing/auto-unboxing 
package com.eomcs.corelib.ex01;

public class Exam0222 {
  public static void main(String[] args) {
    // 자바는 Wrapper 객체 값을 primitive data type 변수에 직접 할당할 수 있다.
    Integer obj = Integer.valueOf(300);
    int i = obj;  // 내부적으로 obj4.intValue()로 바뀐다.
    // 즉 obj에 들어있는 인스턴스 주소가 i에 저장되는 것이 아니라,
    // obj 인스턴스에 들어 있는 값을 꺼내 i에 저장하는 것이다.
    // => auto-unboxing
    //    Integer 객체에서 값을 꺼내 리턴한다.
    //    이렇게 객체 안에 들어 있는 값을 자동으로 꺼낸다고 해서 "오토언박싱"이라 부른다.
  }
}






