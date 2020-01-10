// String - 문자열 비교
package com.eomcs.corelib.ex01;

public class Exam0120 {
  public static void main(String[] args) {
    String s1 = new String("Hello");
    String s2 = new String("Hello");
    String s3 = "Hello";

    // equals()?
    // => Object에 정의되어 있는 메서드이다
    //    인스턴스가 같은지 비교한다.
    if (s1.equals(s2))   // 같다.
      System.out.println("s1과 s2는 같다.");
    else 
      System.out.println("s1과 s2는 다르다.");

    if (s1.equals(s3))   // 같다.
      System.out.println("s1과 s3는 같다.");
    else 
      System.out.println("s1과 s3는 다르다.");
  }
  // s1, s2, s3가 서로 다른 인스턴스를 가리키는데 왜 결과가 같은가?
  // => String 클래스에서 equals()를 오버라이딩 했기 때문이다.
  // => 인스턴스를 비교하는 것이 아니라 문자열이 같은지 비교하도록 변경되었다.
  // => 따라서 s1, s2, s3가 "Hello"로 같기 때문에 equals()에 리턴값은 True!
  // 결론!
  // => String 인스턴스의 데이터가 같은지 비교할 때는 equals()라는 메서드를 사용하라!
}





