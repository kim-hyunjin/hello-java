package com.eomcs.oop.ex03.test;

class Score {
  static int count;
  String name;
  int kor;
  int eng;
  int math;
  int sum;
  float avg;

  Score(String name, int kor, int eng, int math) {
    System.out.println("Score() - 2");
    this.name = name;   // 인스턴스 생성과 동시에 필드를 유효한 값으로 초기화시킬 수 있다.
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
    Score.increase();
  }
  
  
  static void increase() {  // 인스턴스 변수를 사용하지 않는 메서드는 static으로 만들자.
    Score.count++;
  }
  
  void compute() {    //  메서드 = 연산자 = operator
    this.sum = this.kor + this.eng + this.math;
    this.avg = this.sum / 3f;
  }
}

public class Test01 {
  public static void main(String[] args) {
    Score s1 = new Score("홍길동", 100, 90, 80);
    System.out.println("--------");
    
    Score s2 = new Score("임꺽정", 90, 80, 70);
    System.out.println("--------");
  }
}



