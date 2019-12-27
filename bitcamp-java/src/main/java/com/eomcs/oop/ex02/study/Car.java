package com.eomcs.oop.ex02.study;

public class Car {
  static final int OIL = 1;
  static final int GAS = 2;
  
  String name;
  int type;
  String Color;
  int speed;
  int energy;
  int durability;
  int power;
  int cleanLevel;

  void speedUp() {
    //Car this = 메서드를 호출할 때 넘겨준 인스턴스 주소
    if (this.speed > 100) 
      return;
    this.speed += 10;
  }

  int comparePower(Car c) {
    if (this.power > c.power)
      return -1;
    else if (this.power == c.power)
      return 0;
    else
      return 1;
  }
}
