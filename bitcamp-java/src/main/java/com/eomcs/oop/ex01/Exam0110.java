package com.eomcs.oop.ex01;

//# 클래스 - 사용 전
//
public class Exam0110 {
  public static void main(String[] args) {
    // 낱개의 변수를 사용하여 한 사람의 성적 정보를 저장하라!
    // => 식탁에 밥, 국, 반찬1, 반찬2, 반찬3 을 각각 따로 가져오는 상황.
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;

    name = "홍길동";
    kor = 100;
    eng = 90;
    math = 85;
    sum = kor + eng + math;
    aver = (float)sum / 3;  // float/int = > float/float 암시적 형변환(type casting)
                            // 변수 sum의 데이터타입을 int에서 float로 바꾸는 것이 아니라 
                            // sum의 값을 float으로 던짐

    System.out.printf("이름: %s\n", name);
    System.out.printf("국어: %d\n", kor);
    System.out.printf("영어: %d\n", eng);
    System.out.printf("수학: %d\n", math);
    System.out.printf("합계: %d\n", sum);
    System.out.printf("평균: %.1f\n", aver);
  }
}