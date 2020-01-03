// 다형성 - 다형적 변수(polymorphic variables)
package com.eomcs.oop.ex06.a;

public class Exam0113 {

    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        Bike b = new Bike();
        Car c = new Car();
        Sedan s = new Sedan();
        Truck t = new Truck();

        Bike b2 = null;
        // 부모가 같은 형제 클래스라도 가리킬 수 없다.
        // 왜?
        // Bike 클래스의 인스턴스 변수를 갖고 있지 않기 때문이다.
        //b2 = v; // 컴파일 오류!
        //b2 = c; // 컴파일 오류!
        //b2 = s; // 컴파일 오류!
        //b2 = t; // 컴파일 오류!
    }
}
