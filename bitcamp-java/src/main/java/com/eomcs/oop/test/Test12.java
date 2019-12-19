package com.eomcs.oop.test;

// gym 회원 정보를 입력 받아 출력한다.
public class Test12 {
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
    
    // 인스턴스 3000개, 레퍼런스 3000개 만들기
    // 배열을 이용하여 쉽게 만들 수 있다.
    
    Member[] arr = new Member[3000]; // 멤버 인스턴스 주소를 담을 `레퍼런스를 3000개` 만듦.
                                     // => 멤버 인스턴스를 3000개 만드는 것이 아니다.
    
    // arr[0].no = 100; 컴파일에러!(runtime error)
    
    int count = 0;
    while(count < 3000) {
      arr[count] = new Member();    // 배열에 인스턴스 할당
      count++;
    }
    // 배열 문법을 이용하여 인스턴스를 3000개 만드는 방법은 없다!
    
    arr[0].no = 100; // OK!
  }
}
