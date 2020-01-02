package com.eomcs.oop.ex03.test;

public class Car {
  ///필드///
  private boolean on;
  private int speed;
  private int distance;
  private String model;
  private String maker;
  private Engine engine;
  
  ////생성자///
  Car() {
    this.engine = new Engine(4, 16, 1990); //Car를 생성할 때 엔진을 만든다.
  }
  
  ///메서드///
  public void start() {
    this.on = true;
  }

  public void stop() {
    this.on = false;
  }
  
  void check() {
    if (this.engine.oilState == 0) {
      this.engine.cleanOil();
      System.out.println("엔진오일을 교환했습니다.");
    }
    System.out.println("점검 완료.");
  }
  
  public boolean getOn() {
    return this.on;
  }
  
  /*void cleanOil() {
    this.engine.oilState = 10;
  }*/ //엔진의 데이터를 직접 조작하는 메서드이므로 엔진 클래스로 옮겨야한다.
}
