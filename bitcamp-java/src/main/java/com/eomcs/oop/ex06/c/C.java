package com.eomcs.oop.ex06.c;

public class C {                // 접근제어자
    private void m1() {}        // private : 현재 클래스에서만 접근 가능
    void m2() {}                // default : 현재 클래스 + 같은 패키지 소속 클래스
    protected void m3() {}      // protected : 현재 클래스 + 같은 패키지 소속 클래스 + 서브 클래스
    public void m4() {}         // public : 
}
