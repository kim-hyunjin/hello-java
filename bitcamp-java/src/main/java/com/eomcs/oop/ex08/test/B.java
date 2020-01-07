package com.eomcs.oop.ex08.test;

public class B {
  // field
  static int a; //class field, static field
  String b;     //instance field, non-static field
  
  //method
  static void m1() {    //class method, static method
    
  }
  int m2() {            //instance method, non-static method
    return 0;
  }
  
  //initializer block
  static {}     // static block
  {}            // instance block
  
  //constructor
  B() {}
  
  //nested class
  static class L1 {}    //static nested class
  class L2 {}           //non-static nested class, inner class
}
