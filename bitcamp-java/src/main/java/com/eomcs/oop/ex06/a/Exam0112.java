// 다형성 - 다형적 변수(polymorphic variables)
package com.eomcs.oop.ex06.a;

public class Exam0112 {

  public static void main(String[] args) {
    Vehicle v = new Vehicle();
    Bike b = new Bike();
    Car c = new Car();
    Sedan s = new Sedan();
    Truck t = new Truck();

    Bike b2 = null;
    // 하위 클래스의 레퍼런스로 상위 클래스의 인스턴스를 가리킬 수 없다.
    // => 상위 클래스의 인스턴스에는 하위 클래스의 인스턴스 변수가 없기 때문이다.
    b2 = v; // 컴파일 오류!

    // 만약 위의 코드가 가능하다면 다음과 같이 Bike레퍼런스로 Bike인스턴스 변수를 
    // 사용하려 할 것이다.
    b2.engine = true;
    // 그러나, b2가 실제 가리키는 것은 Bike의 인스턴스 변수가 없는 
    // Vehicle 인스턴스를 가리키기 때문에 Bike의 engine 변수를 사용할 수 없다.

    // 이렇게 개발자가 레퍼런스를 통해 존재하지 않는 인스턴스 멤버를 사용할 경우를
    // 미연에 방지하고자 컴파일단계에서 막는다.

    //b2 = c; // 컴파일 오류!
    //b2 = s; // 컴파일 오류!
    //b2 = t; // 컴파일 오류!
  }
}
