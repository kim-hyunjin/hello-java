package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;
import com.eomcs.util.RequestMapping;

@Component
public class LessonSearchServlet {

  LessonService lessonService;

  public LessonSearchServlet(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @RequestMapping("/lesson/search")
  public void service(Map<String, String> map, PrintStream out) throws Exception {
    HashMap<String, Object> params = new HashMap<>();
    String keyword = URLDecoder.decode(map.get("keyword"), "UTF-8");
    if (keyword.length() > 0) {
      params.put("title", keyword);
    }
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>수업 검색 결과</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>수업 검색 결과</h1>");
    List<Lesson> lessons = lessonService.search(params);
    if (lessons == null) {
      out.println("<p>검색 결과가 없습니다.</p>");
    } else {
      for (Lesson l : lessons) {
        out.printf("<p>%s</p>\n", l.getTitle());
      }
    }
    out.println("<a href='/lesson/list'>수업목록으로 돌아가기</a>");
    out.println("</body>");
    out.println("</html>");
  }
}


