// 메서드 오버로딩(overloading) - 사용 전
package com.eomcs.oop.ex06.b;

public class Exam0120 {

  static class Calculator {
    // 파라미터의 타입이나 개수가 다르더라도
    // 같은 일을 하는 메서드에 대해서는 같은 이름을 갖게 한다.
    static int plus(int a, int b) {
      return a + b;
    }

    static float plus(float a, float b) {
      return a + b;
    }
  }
  
  public static void main(String[] args) {
    // 호출하는 메서드 이름이 같지만
    // 아규먼트의 타입이나 개수에 따라 호출되는 메서드가 결정된다.
    // 
    int r1 = Calculator.plus(100, 200);
    float r2 = Calculator.plus(10.2f, 20.4f);
    System.out.printf("%d, %.1f", r1, r2);
  }
}
//오버로딩(overloading)?
//=> 파라미터의 형식은 다르지만 같은 기능을 수행하는 메서드에 대해 
// 같은 이름을 부여함으로써 프로그래밍의 일관성을 제공하기 위한 문법이다.  