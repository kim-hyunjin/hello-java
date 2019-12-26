package com.eomcs.oop.ex01.assignment;

import java.util.Scanner;

public class Test001 {
  public static void main(String[] args) {
    System.out.println("성적을 입력하세요!");
    Scanner keyboard = new Scanner(System.in);
    class Score {
      String name;
      int kor;
      int eng;
      int math;
      int sum;
      float avg;
    }
    int arrSize = 10;
    Score[] arr = new Score[arrSize];
    int count = 0;
    int inputNumber = 0;

    System.out.print("입력 개수?> ");
    inputNumber = keyboard.nextInt();
    keyboard.nextLine();

    for (int i = 0; i < inputNumber; i++) {
      count++;
      Score s = new Score();
      System.out.print("이름: ");
      s.name = keyboard.nextLine();
      System.out.print("국어점수: ");
      s.kor = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("영어점수: ");
      s.eng = keyboard.nextInt();
      keyboard.nextLine();
      System.out.print("수학점수: ");
      s.math = keyboard.nextInt();
      keyboard.nextLine();
      System.out.println("--------------------------");
      s.sum = s.kor + s.eng + s.math;
      s.avg = s.sum / 3f;
      arr[i] = s;
    }
    for (int i = 0; i < count; i++) {
      Score s = arr[i];
      System.out.printf("%s %d %d %d %d %.1f\n", s.name, s.kor, s.eng, s.math, s.sum, s.avg);
    }
    keyboard.close();
  }
}
