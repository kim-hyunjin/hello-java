// 생성자 활용 예 - java.util.Calendar 클래스의 생성자
package com.eomcs.oop.ex04;

import java.util.Calendar;

public class Exam0130 {

  public static void main(String[] args) throws Exception {
    // 생성자가 있다하더라도 접근 권한이 없다면,
    // 생성자를 호출할 수 없다.
    // 이런 경우 new 명령으로 인스턴스를 생성할 수 없다.
    //
    // Calendar 클래스의 경우도 생성자를 protected로 막고 있다.
    //Calendar c = new Calendar(); // 컴파일 오류!
    Calendar c1 = Calendar.getInstance();  // 클래스 메서드를 통해 객체 생성
    Calendar c2 = Calendar.getInstance();
    
    // getInstance()를 호출할 때 생성되는 Calendar 객체는 실행할 때의 시각 정보를 갖고 있다. 
    if(c1.equals(c2)) {
      System.out.println("같다");
    } else {
      System.out.println("다르다");
    }
    System.out.println(c1.getTimeInMillis());
    System.out.println(c2.getTimeInMillis());
  }
}
// 이렇게 자바에서 생성자의 사용 권한을 막고
// 클래스 메서드를 통해 객체를 생성하도록 유도하는 이유 :
// 1) 같은 값을 갖는 객체를 쓸데없이 여러개 생성하지 못하도록 막아 메모리 절약
//    ==> Singleton 패턴
// 2) 객체 생성과정이 복잡할 때 객체 생성 코드를 단순하게 만들기 위해
//    ==> Factory Method 패턴
//
// 위의 경우는 Factory Method 패턴의 예













