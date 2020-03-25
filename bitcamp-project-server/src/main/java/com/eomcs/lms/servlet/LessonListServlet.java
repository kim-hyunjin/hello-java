package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.RequestMapping;

@Component
public class LessonListServlet {

  LessonService lessonService;

  public LessonListServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @RequestMapping("/lesson/list")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>수업 목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>수업 목록</h1>");
    out.println("<form action='/lesson/search' accept-charset='UTF-8'>");
    out.println("<input type='search' placeholder='검색어를 입력하세요.' name='keyword'>");
    out.println("</form>");
    out.println("<a href='/lesson/addForm'>수업 등록</a><br>");
    out.println("<table border='1'>");
    out.println("<tr>");
    out.println("<th>번호</th>");
    out.println("<th>수업명</th>");
    out.println("<th>내용</th>");
    out.println("<th>기간</th>");
    out.println("<th>총 수업시간</th>");
    out.println("<th>일 수업시간</th>");
    out.println("</tr>");
    List<Lesson> lessons = lessonService.list();
    for (Lesson l : lessons) {
      out.printf(
          "<tr><td>%d</td><td><a href='/lesson/detail?no=%d'>%s</a></td><td>%s</td><td>%s ~ %s</td><td>%d</td><td>%d</td></tr>",
          l.getNo(), l.getNo(), l.getTitle(), l.getDescription(), l.getStartDate(), l.getEndDate(),
          l.getTotalHours(), l.getDayHours());
    }
    out.println("</body>");
    out.println("</html>");
  }
}
