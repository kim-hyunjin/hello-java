// 다형성 - 다형적 변수의 활용
package com.eomcs.oop.ex06.a;

public class Exam0412 {
    public static void printSedan(Sedan sedan) {
        System.out.printf("모델명: %s\n", sedan.model);
        System.out.printf("cc: %d\n", sedan.cc);
        System.out.println("-------------------------");
    }
    public static void printTruck(Truck truck) {
        System.out.printf("모델명: %s\n", truck.model);
        System.out.printf("cc: %d\n", truck.cc);
        System.out.println("-------------------------");
    }
    
    public static void main(String[] args) {
        Sedan car1 = new Sedan();
        car1.model = "티코";
        car1.cc = 800;
        
        Truck car2 = new Truck();
        car2.model = "타이탄II";
        car2.cc = 10000;
        
        // Sedan 객체에서 model과 cc를 뽑아 출력할 때는 해당 메서드를 호출하고,
        printSedan(car1);
        // Truck도 그에 맞는 메서드를 호출한다.
        printTruck(car2);
        
        // 그런데 printSedan()과 printTruck()의 코드를 보면 거의 같다.
        // 같은 기능을 하는 코드를 여러 개 중복해서 두는 것은 유지보수에 좋지 않다.
        // 해결책?
        // => 두 클래스가 같은 조상을 가질 때는 다형적 변수를 활용하라!
    }
}




