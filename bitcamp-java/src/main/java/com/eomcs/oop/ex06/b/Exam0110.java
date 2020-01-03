// 메서드 오버로딩(overloading) - 사용 전
package com.eomcs.oop.ex06.b;

public class Exam0110 {

  static class Calculator {
    // 만약 같은 이름의 메서드를 여러 개 만들 수 없다면,
    // 다음과 같이 같은 일을 수행하는 메서드라도
    // 이름을 다르게 해야 한다.
    static int plusi(int a, int b) {
      return a + b;
    }
    static float plusf(float a, float b) {
      return a + b;
    }
  }
  
  public static void main(String[] args) {
    // 두 개의 정수를 더할 때는 plusi를 호출
    int r1 = Calculator.plusi(100, 200);
    // 두 개의 부동소수점을 더할 때는 plusf를 호출
    float r2 = Calculator.plusf(10.2f, 20.4f);
    System.out.printf("%d, %.1f", r1, r2);
    
    // 같은 + 연산을 하더라도 더하는 값의 타입에 따라 메서드 이름이 다르다면
    // 메서드를 사용하기가 번거롭다.
    // => 때문에 오버로딩 문법이 등장하게 되었다.
  }
}
