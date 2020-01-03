// Object 클래스의 메서드를 오버라이딩 하기 - hashCode()
package com.eomcs.oop.ex06.d;

public class Exam0220 {

  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;

    public Score(String name, int kor, int eng, int math) {
      this.name = name;
      this.kor = kor;
      this.eng = eng;
      this.math = math;
      this.sum = kor + eng + math;
      this.aver = this.sum / 3f;
    }

    // hashCode() 를 오버라이딩하면 원하는 값을 리턴할 수 있다.
    @Override
    public int hashCode() {
      // 무조건 모든 Score 인스턴스가 같은 해시코드를 갖게 하자.
      return 1000;
    }
  }

  public static void main(String[] args) {
    Score s1 = new Score("홍길동", 100, 100, 100);
    Score s2 = new Score("임꺽정", 90, 80, 70);
    Score s3 = new Score("김현진", 80, 70, 60);

    // 같은 값을 가질 때 같은 해시값을 리턴하도록 
    // hashCode()를 오버라이딩 했기 때문에
    // 다음 출력은 같은 해시 값을 출력할 것이다.
    System.out.printf("%d, %d, %d\n", s1.hashCode(), s2.hashCode(), s3.hashCode());

    // 해시코드?
    // => 데이터를 식별할 때 사용하는 고유 아이디이다.
    // => 보통 특별한 공식(MD4, MD5, SHA-1, SHA-256 등)으로 계산해서 나온 정수값을
    //    해시코드로 사용한다.
    // => 해시코드를 디지털 지문이라고 부른다.
    // hashCode()를 오버라이딩할 때?
    // 인스턴스가 다르더라도 같은 값을 갖는 경우, 똑같이 취급하기 위해 이 메서드를 재정의한다.
    // 따라서, 위의 예처럼 데이터가 같은지 따지지도 않고 모든 인스턴스에 대해 같은 해시코드를
    // 리턴하는 것은 아무 의미가 없다.
  }
}






