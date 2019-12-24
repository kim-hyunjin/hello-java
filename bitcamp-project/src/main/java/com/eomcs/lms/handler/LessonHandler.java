package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;

public class LessonHandler {
  
  // 인스턴스 필드(non-static)
  // 각 수업 목록을 개별적으로 관리
  Lesson[] lessons = new Lesson[LESSON_SIZE];
  int lessonCount = 0;
  
  //  클래스 필드(static)
  //=> 공유할 변수
  static final int LESSON_SIZE = 100;
  public static Scanner keyboard;
  
  public static void addLesson(LessonHandler lessonHandler) {
    Lesson le = new Lesson(); 

    System.out.print("번호를 입력하세요 : ");
    le.no = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("수업명을 입력하세요 : ");
    le.title = keyboard.nextLine();
    System.out.print("설명을 입력하세요 : ");
    le.description = keyboard.nextLine();
    System.out.print("시작일을 입력하세요 : ");
    le.startDate = Date.valueOf(keyboard.next());
    keyboard.nextLine();
    System.out.print("종료일을 입력하세요 : ");
    le.endDate = Date.valueOf(keyboard.next());
    keyboard.nextLine();
    System.out.print("총수업시간을 입력하세요 : ");
    le.totalHours = keyboard.nextInt();
    keyboard.nextLine();
    System.out.print("일수업시간을 입력하세요 : ");
    le.dayHours = keyboard.nextInt();
    keyboard.nextLine();
    lessonHandler.lessons[lessonHandler.lessonCount++] = le;
    System.out.println("저장되었습니다.");
  }

  public static void listLesson(LessonHandler lessonHandler) {
    for (int i = 0; i < lessonHandler.lessonCount; i++) {
      Lesson l = lessonHandler.lessons[i];
      System.out.printf("%d, %s, %s ~ %s\n", 
          l.no, l.title, l.startDate, l.endDate);
    }
  }
}

