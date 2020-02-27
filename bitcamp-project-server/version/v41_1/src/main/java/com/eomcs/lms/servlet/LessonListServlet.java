package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonListServlet implements Servlet {

  LessonDao lessonDao;

  public LessonListServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Lesson> lessons = lessonDao.findAll();
    for (Lesson le : lessons) { // 데이터를 한 개 가져왔으면 true를 리턴한다.
      out.printf("%d, %s, %s, %s~%s, %d, %d\n", //
          le.getNo(), //
          le.getTitle(), //
          le.getDescription(), //
          le.getStartDate(), //
          le.getEndDate(), //
          le.getTotalHours(), //
          le.getDayHours()//
      );
    }
  }
}
