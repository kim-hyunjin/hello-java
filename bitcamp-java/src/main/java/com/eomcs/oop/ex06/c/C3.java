package com.eomcs.oop.ex06.c;

public class C3 extends C {    

    // => 오버라이딩 메서드의 접근 범위를 확장하는 것은 괜찮다.
    @Override protected void m2() {} // default 보다 접근 범위를 확대했기 때문에 OK!
    @Override public void m3() {}    // protected => public
    
}

// 멤버의 접근 범위
// private      : 같은 클래스
// (default)    : 같은 클래스 + 같은 패키지
// protected    : 같은 클래스 + 같은 패키지 + 서브 클래스
// public       : 모두
//
// 결론!
// 수퍼 클래스의 메서드를 자식 클래스가 재정의할 때 
// 접근 범위를 확대할 순 있지만, 좁힐 수는 없다!







