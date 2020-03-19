// 프로퍼티값 자동 형변환
package com.eomcs.spring.ioc.ex04.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer =
        new ClassPathXmlApplicationContext("com/eomcs/spring/ioc/ex04/b/application-context.xml");

    // 프로퍼티의 타입이 int일 경우, XML에 작성한 문자열이 자동으로 int값으로 형변환된다.
    // 형변환할 수 없으면 예외 발생
    // 자동 형변환은 primitive type에 대해서만 가능하다.
    // 그외 타입에 대해서는 자동 형변환하지 않는다.
    // 형변환하고 싶으면 개발자가 형변환하는 클래스를 만들어 스프링 프레임워크에 등록해야 한다.
  }

}


