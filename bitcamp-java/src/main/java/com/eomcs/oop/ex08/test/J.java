package com.eomcs.oop.ex08.test;

public class J {

  class Student {
    String email;
    String password;
    String name;
    String tel;
    int grade;
    boolean working;

    void print() {
      System.out.println("학생정보");
    }
  }

  class Teacher {
    String email;
    String password;
    String name;
    String tel;
    int pay;
    String major;

    void print() {
      System.out.println("강사정보");
    }
  }
}