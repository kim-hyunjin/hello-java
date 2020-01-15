package com.eomcs.oop.ex09.e.project4;

import com.eomcs.oop.ex09.e.Computer;

// 4번째 프로젝트에서 터치 기능이 들어간 새 컴퓨터를 만들기로 했다.
// 방법2: 터치 기능을 기존 규칙(Computer)에 추가한다.
public class NewComputer2 implements Computer {
    // 새 컴퓨터를 만들 때는 기존 규칙만 따라도 되고,
    // => 즉 compute() 메서드만 구현해도 되고
    public void compute() {
        System.out.println("새 컴퓨터..");
    }
    
    // 새로 추가한 규칙을 정의하는 것은 개발자 마음이다.
    // => 즉 나중에 추가한 touch() 메서드를 구현하는 것은 개발자 마음!
    public void touch() {
        System.out.println("오호라.. 터치가 되네. 이거 서피스 프로인가?");
    }

}
/*
 * 방법 1처럼 기존 규칙을 상속 받아서 새 규칙을 만들면, 
 * 규칙이 늘어나 관리하기가 어려운 문제가 있다.
 * 규칙에 따라 구분해서 코딩해야 하기 때문에 프로그래밍 하기가 불편하다.
 * touch기능을 기존 규칙에 추가하는 방법이 있지만,
 * 문제는 기존 규칙(인터페이스)에 touch기능을 추가하는 순간 기존 클래스들에 오류가 발생한다.
 * 규칙을 변경하면 규칙에 따라 만든 모든 클래스를 변경해야 한다.
 * 
 */





