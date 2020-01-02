package com.eomcs.oop.ex03.test;

public class Test03 {
  public static void main(String[] args) {

    Car sonata = new Car();
    Car porche = new Car();
    Car avante = null;
    
    sonata.start();
    porche.start();
    //avante.start(); // - java.lang.NullPointerException
    
    sonata.check();
    sonata.check();
    
    System.out.printf("sonata.on = %b\n", sonata.getOn());
    System.out.printf("porche.on = %b\n", porche.getOn());
  }
}
