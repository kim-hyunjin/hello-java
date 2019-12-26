package com.eomcs.oop.ex02.study;

public class Score {
  // 변수 선언을 통해 메모리 구조 설계 : 인스턴스 변수
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float avg;
  
  // 메서드를 통해 값을 계산하는 기능을 추가 : 연산자(operator) 정의
  // => 합계와 평균을 계산하는 기능을 추가한다.
  // => 메서드를 다음과 같이 호출할 때,
  //    인스턴스주소.메서드명();
  //    인스턴스 주소를 자동으로 받는 메서드 문법이 있다.
  //    "인스턴스 메서드"이다.
  // => 인스턴스 메서드는 static을 붙이지 않는다.
  // => 메서드를 호출할 때 넘겨준 인스턴스 주소는 내장 변수 this에 자동 저장된다.
  // 인스턴스 메서드는 인스턴스의 주소가 있어야 사용할 수 있다.
  public void calculate() {
    // 인스턴스 메서드는 인스턴스 주소를 받는 내장 변수(built-in)가 있다.
    // 그 변수의 이름은 this이다.
    this.sum = this.kor + this.eng + this.math;
    this.avg = this.sum / 3f;
  }
}
