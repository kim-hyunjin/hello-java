package com.eomcs.oop.ex02.study;

public class Calculator {
  
    // 메서드가 작업한 결과를 보관하기 위해 필드 문법을 도입
    static int result;  // 필드는 자동으로 초기화된다.

    static void plus(int value) {
      result += value;
    }
    
    static void minus(int value) {
      result -= value;
    }
    
    static void multiple(int value) {
      result *= value;
    }
    
    static void divide(int value) {
      result /= value;
    }

  }
