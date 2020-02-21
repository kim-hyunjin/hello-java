package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonUpdateServlet implements Servlet {

  LessonDao lessonDao;

  public LessonUpdateServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!");
    int no = Integer.parseInt(in.nextLine());

    Lesson old = lessonDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 수업 정보가 없습니다.");
      return;
    }
    out.println("변경을 원치 않는 경우 엔터");
    Lesson lesson = new Lesson();
    lesson.setNo(no);

    out.printf("수업명(%s)? \n!{}!\n", old.getTitle());
    String title = in.nextLine();
    if (title.length() == 0) {
      lesson.setTitle(old.getTitle());
    } else {
      lesson.setTitle(title);
    }

    out.printf("설명(%s)? \n!{}!\n", old.getDescription());
    String description = in.nextLine();
    if (description.length() == 0) {
      lesson.setDescription(old.getDescription());
    } else {
      lesson.setDescription(description);
    }

    out.printf("시작일(%s)? \n!{}!\n", old.getStartDate());
    String startDate = in.nextLine();
    if (startDate.length() == 0) {
      lesson.setStartDate(old.getStartDate());
    } else {
      lesson.setStartDate(Date.valueOf(startDate));
    }

    out.printf("종료일(%s)? \n!{}!\n", old.getEndDate());
    String endDate = in.nextLine();
    if (endDate.length() == 0) {
      lesson.setEndDate(old.getEndDate());
    } else {
      lesson.setEndDate(Date.valueOf(endDate));
    }

    out.printf("총수업시간(%d)? \n!{}!\n", old.getTotalHours());
    String totalHours = in.nextLine();
    if (totalHours.length() == 0) {
      lesson.setTotalHours(old.getTotalHours());
    } else {
      lesson.setTotalHours(Integer.parseInt(totalHours));
    }

    out.printf("일수업시간(%d)? \n!{}!\n", old.getDayHours());
    String dayHours = in.nextLine();
    if (dayHours.length() == 0) {
      lesson.setDayHours(old.getDayHours());
    } else {
      lesson.setDayHours(Integer.parseInt(dayHours));
    }

    if (lessonDao.update(lesson) > 0) {
      out.println("수업 정보를 변경했습니다.");
    } else {
      out.println("수업 변경에 실패했습니다.");
    }
  }
}
