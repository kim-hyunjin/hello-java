package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.ArrayList;

public class LessonHandler {

  ArrayList<Lesson> lessonList;
  public Scanner input;

  public LessonHandler(Scanner input) {
    this.input = input;
    this.lessonList = new ArrayList<>();
  }

  public LessonHandler(Scanner input, int capacity) {
    this.input = input;
    this.lessonList = new ArrayList<>(capacity);
  }

  public void addLesson() {
    Lesson le = new Lesson(); 

    System.out.print("번호를 입력하세요 : ");
    le.setNo(input.nextInt());
    input.nextLine();
    System.out.print("수업명을 입력하세요 : ");
    le.setTitle(input.nextLine());
    System.out.print("설명을 입력하세요 : ");
    le.setDescription(input.nextLine());
    System.out.print("시작일을 입력하세요 : ");
    le.setStartDate(input.nextLine());
    System.out.print("종료일을 입력하세요 : ");
    le.setEndDate(input.nextLine());
    System.out.print("총수업시간을 입력하세요 : ");
    le.setTotalHours(input.nextInt());
    input.nextLine();
    System.out.print("일수업시간을 입력하세요 : ");
    le.setDayHours(input.nextInt());
    input.nextLine();

    lessonList.add(le);
    System.out.println("저장되었습니다.");
  }

  public void listLesson() {
    // 수업 객체 목록을 받을 배열을 준비하고, toArray()를 실행한다.
    // toArray()의 리턴 값은 파라미터로 넘겨준 배열의 주소이다.
    Lesson[] arr = this.lessonList.toArray(new Lesson[this.lessonList.getSize()]);
    for (Lesson l : arr) {
      System.out.printf("%d, %s, %s ~ %s\n", 
          l.getNo(), l.getTitle(), l.getStartDate(), l.getEndDate());
    }
  }

  public void updateLesson() {
    System.out.print("강의 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Lesson oldLesson = this.lessonList.get(index);

    if (oldLesson == null) {
      System.out.println("인덱스 번호가 유효하지 않습니다.");
      return;
    }

    Lesson newLesson = new Lesson();
    System.out.println("--- 수정사항을 입력하세요 ---");
    System.out.print("수업명을 입력하세요 : ");
    String title = input.nextLine();
    if (title.length() == 0) {
      newLesson.setTitle(oldLesson.getTitle());
    } else {
      newLesson.setTitle(title);
    }
    System.out.print("설명을 입력하세요 : ");
    String description = input.nextLine();
    if (description.length() == 0) {
      newLesson.setDescription(oldLesson.getDescription());
    } else {
      newLesson.setDescription(description);
    }
    System.out.print("시작일을 입력하세요 : ");
    String start = input.nextLine();
    if (start.length() == 0) {
      newLesson.setStartDate(oldLesson.getStartDate());
    } else {
      newLesson.setStartDate(start);
    }
    System.out.print("종료일을 입력하세요 : ");
    String end = input.nextLine();
    if (end.length() == 0) {
      newLesson.setEndDate(oldLesson.getEndDate());
    } else {
      newLesson.setEndDate(end);
    }
    System.out.print("총수업시간을 입력하세요 : ");
    String temp = input.nextLine();
    int totalHour = Integer.parseInt(temp);
    if (temp.length() == 0) {
      newLesson.setTotalHours(oldLesson.getTotalHours());
    } else {
      newLesson.setTotalHours(totalHour);
    }
    System.out.print("일수업시간을 입력하세요 : ");
    String temp2 = input.nextLine();
    int dayHour = Integer.parseInt(temp2);
    if (temp2.length() == 0) {
      newLesson.setDayHours(oldLesson.getDayHours());
    } else {
      newLesson.setDayHours(dayHour);
    }
    
    this.lessonList.set(index, newLesson);
    System.out.println("강의 정보를 변경했습니다.");
  }
  
  public void deleteLesson() {
    System.out.print("강의 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Lesson oldLesson = this.lessonList.get(index);

    if (oldLesson == null) {
      System.out.println("인덱스 번호가 유효하지 않습니다.");
      return;
    }
    
    this.lessonList.remove(index);
    System.out.println("강의 정보를 삭제했습니다.");
  }
  
}

