//# 캡슐화 문법 사용 후 - 필드의 값을 변경할 때마다 특정 작업을 수행하게 만들기
package com.eomcs.oop.ex07.a;

class Score3 {
  String name;
  
  // 국영수 점수를 바꿀 때마다 자동으로 합계, 평균을 계산
  // 방법?
  // => 직접 필드의 값을 못바꾸게 하고 메서드를 통해 바꾸도록 한다.
  private int kor;
  private int eng;
  private int math;
  private int sum;
  private float avg;    
  
  // setter
  public void setScore(int kor, int eng, int math) {
    this.kor = kor;
    this.eng = eng;
    this.math = math;
    this.compute();
  }
  public void setKor(int kor) {
    this.kor = kor;
    this.compute();
  }
  public void setEng(int eng) {
    this.eng = eng;
    this.compute();
  }
  public void setMath(int math) {
    this.math = math;
    this.compute();
  }

  // getter
  public int getKor() { return this.kor; }
  public int getEng() { return this.eng; }
  public int getMath() { return this.math; }
  public int getSum() { return this.sum; }
  public float getAvg() { return this.avg; }
  
  void compute() {
    this.sum = this.kor + this.eng + this.math;
    this.avg = this.sum / 3f;
  }
}

public class Exam0310 {
  public static void main(String[] args) {
    Score3 s1 = new Score3();
    s1.name = "홍길동";
    s1.setScore(100, 90, 80);   // setter를 통해 값을 설정할 때마다 compute()자동 실행
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s1.name, s1.getKor(), s1.getEng(), s1.getMath(), s1.getSum(), s1.getAvg());
    // 필드가 private이기 때문에 getter를 사용해 값 조회
    
    s1.setEng(100);
    s1.setMath(100); //값을 변경하면 합계와 평균이 자동 갱신
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s1.name, s1.getKor(), s1.getEng(), s1.getMath(), s1.getSum(), s1.getAvg());
    
  }
}
