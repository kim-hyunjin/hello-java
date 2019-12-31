//# 캡슐화 문법 사용 후 - 특정 필드를 직접 접근하지 못하게 막기
package com.eomcs.oop.ex07.a;

class Score2 {
  String name;
  int kor;
  int eng;
  int math;
  
  // sum과 avg필드는 kor, eng, math 값을 연산한 결과를 보관하기 때문에
  // 직접 접근하여 값을 변경하는 것을 허용해서는 안된다.
  // 허용하는 순간 개발자의 잘못된 명령으로
  // 국영수 점수와 합계, 평균이 맞지 않는 문제가 발생할 수 있다. 
  private int sum;
  private float avg;    //외부접근을 막기위해 private 사용. => 캡슐화
  
  // 값은 변경하지 못하게 하더라도 이 값들을 조회할 수 있는 방법은 제공해야한다.
  public int getSum() {    // getter는 일반적으로 공개한다.
    return this.sum;
  }
  
  public float getAvg() {
    return this.avg;
  }
  
  void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.avg = this.sum / 3f;
  }
}

public class Exam0210 {
  public static void main(String[] args) {
    Score2 s1 = new Score2();
    s1.name = "홍길동";
    s1.kor = 100;
    s1.eng = 90;
    s1.math = 80;
    s1.compute();
    
//    s1.sum = 300;
//    s1.avg = 100f;
    
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s1.name, s1.kor, s1.eng, s1.math, s1.getSum(), s1.getAvg());
    
  }
}
