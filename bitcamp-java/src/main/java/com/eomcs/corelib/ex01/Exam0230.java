// Wrapper 클래스 - wrapper 객체의 값 비교 
package com.eomcs.corelib.ex01;

public class Exam0230 {
  public static void main(String[] args) {
    Integer obj1 = new Integer(100); // Heap에 인스턴스 생성
    Integer obj2 = new Integer(100); // Heap에 인스턴스 생성 
    System.out.println(obj1 == obj2); // false
    // => new 연산자를 통해 Integer 객체를 생성하면, Heap에 인스턴스를 생성한다.
    //    따라서 같은 값이라도 다른 인스턴스가 생성된다.
    
    Integer obj3 = 100; // constant pool에 생성
    Integer obj4 = 100; // constant pool에 생성된 기존 주소 리턴
    System.out.println(obj3 == obj4); // true
    // 같은 값을 가지는 Integer객체가 여러개 존재해야 할 필요가 없다.
    // 그래서 가능한 이 방법을 사용해야 한다.
    
    Integer obj5 = Integer.valueOf(100); // constant pool에 생성
    Integer obj6 = Integer.valueOf(100); // constant pool에 생성된 기존 주소 리턴
    System.out.println(obj5 == obj6); // true
    System.out.println(obj3 == obj5); // true
    System.out.println(obj4.equals(obj6)); // true
    // wrapper 클래스는 Object의 equals()를 오버라이딩하여 값을 비교할 수 있다.
  }
}






