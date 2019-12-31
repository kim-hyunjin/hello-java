//# 캡슐화 문법 사용 후 - 특정 필드를 직접 접근하지 못하게 막기
package com.eomcs.oop.ex07.a;

public class Exam0211 {
  public static void main(String[] args) {
    Score2 s1 = new Score2();
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 80;
    s1.compute();

    // 캡슐화 문법으로 sum과 avg의 값을 임의적으로 조작하는 것은 막았지만
    // 개발자가 국영수 점수를 변경한후 compute()를 호출하지 않는다면?
    s1.eng = 100;
    s1.math = 100;
    
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s1.name, s1.kor, s1.eng, s1.math, s1.getSum(), s1.getAvg());
    // 변경된 점수에 맞는 합계와 평균이 출력되지 않는다.
    // 해결책 => Exam0310.java
    
    
  }
}
