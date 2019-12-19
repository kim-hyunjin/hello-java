package com.eomcs.oop.test;

// gym 회원 정보를 입력 받아 출력한다.
public class Test06 {
  public static void main(String[] args) {

    class Member {
    int no;
    String name;
    int birthYear;
    char gender;
    float height;
    float weight;
    boolean personalTraining;
    }

    Member m1;
    m1 = new Member();
    m1.no = 100;

    Member m2 = m1;
    
    m1 = new Member(); // 기존 레퍼런스에 새 인스턴스의 주소를 저장한다.
                       // 따라서 m1과 m2는 서로 다른 인스턴스의 주소를 가지게 된다.
    m1.no = 200;
    System.out.println("m1의 넘버: " + m1.no);
    System.out.println("m2의 넘버: " + m2.no);
  }
}
