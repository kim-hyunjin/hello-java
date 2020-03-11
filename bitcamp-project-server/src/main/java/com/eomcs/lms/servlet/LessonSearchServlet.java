package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Prompt;

public class LessonSearchServlet implements Servlet {

  LessonDao lessonDao;

  public LessonSearchServlet(LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    HashMap<String, Object> map = new HashMap<>();

    String keyword = Prompt.getString(in, out, "수업명 검색:");
    if (keyword.length() > 0) {
      map.put("title", keyword);
    }

    Date date = Prompt.getDate(in, out, "시작일 검색:");
    if (date != null) {
      map.put("startDate", date);
    }

    date = Prompt.getDate(in, out, "종료일 검색:");
    if (date != null) {
      map.put("endDate", date);
    }

    int value = Prompt.getInt(in, out, "총수업시간 검색:");
    if (value > 0) {
      map.put("totalHours", value);
    }

    value = Prompt.getInt(in, out, "일수업시간 검색:");
    if (value > 0) {
      map.put("dayHours", value);
    }

    List<Lesson> lessons = lessonDao.findByKeyword(map);
    for (Lesson l : lessons) {
      out.printf("%d, %s, %s ~ %s, %d, %d\n", l.getNo(), l.getTitle(), l.getStartDate(),
          l.getEndDate(), l.getTotalHours(), l.getDayHours());
    }
  }
}
