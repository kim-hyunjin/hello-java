//# 캡슐화 문법 사용 후 - 캡슐화, setter/getter
package com.eomcs.oop.ex07.a;

class Score4 {
  
  // 실무에서는 보통 필드의 직접 접근을 막는다.
  // 되도록 setter를 통해 값을 설정하도록 유도하고,
  // getter를 통해 값을 조회하도록 한다.
  
  ////// Field ///////
  private String name;
  private int kor;
  private int eng;
  private int math;
  private int sum;
  private float avg;    

  
  ///////////////////// Property ////////////////////////
  public void setName(String name) { this.name = name; }
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
  // sum, avg필드 같은 경우 값을 조회먄 해야하기 때문에 setter가 없다.
  public String getName() { return this.name; }
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

public class Exam0311 {
  public static void main(String[] args) {
    Score4 s1 = new Score4();
    s1.setName("홍길동");
    s1.setScore(100, 90, 80);
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s1.getName(), s1.getKor(), s1.getEng(), s1.getMath(), s1.getSum(), s1.getAvg());
    
    System.out.println("-----------------------------------");
    s1.setEng(100);
    s1.setMath(100);
    System.out.printf("%s, %d, %d, %d, %d, %.1f\n",
        s1.getName(), s1.getKor(), s1.getEng(), s1.getMath(), s1.getSum(), s1.getAvg());
    
  }
}
