// Wrapper 클래스 - auto-boxing/auto-unboxing 
package com.eomcs.corelib.ex01;

public class Exam0221 {
  public static void main(String[] args) {
    // 자바는 primitive data type 값을 wrapper 클래스의 인스턴스에 바로 할당할 수 있다.
    
    Integer obj = 100;
    // obj는 레퍼런스인데 어떻게 가능한가?
    // => 내부적으로 Integer.valueOf(100)으로 객체가 생성되어 그 주소가 저장된다.
    // => int 값을 가지고 자동으로 Integer 랩퍼 객체를 만든다. 
    //    이렇게 객체 안에 값을 넣어 포장한다고 해서 "오토박싱"이라 부른다.
  }
}






