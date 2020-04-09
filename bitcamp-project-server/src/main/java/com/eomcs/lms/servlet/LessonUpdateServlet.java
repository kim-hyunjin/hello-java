package com.eomcs.lms.servlet;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/update")
public class LessonUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      req.setCharacterEncoding("utf-8");
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      LessonService lessonService = iocContainer.getBean(LessonService.class);
      Lesson lesson = new Lesson();
      lesson.setNo(Integer.parseInt(req.getParameter("no")));
      lesson.setTitle(req.getParameter("title"));
      lesson.setDescription(req.getParameter("description"));
      lesson.setStartDate(Date.valueOf(req.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(req.getParameter("endDate")));
      lesson.setTotalHours(Integer.parseInt(req.getParameter("totalHours")));
      lesson.setDayHours(Integer.parseInt(req.getParameter("dayHours")));

      if (lessonService.update(lesson) > 0) {
        resp.sendRedirect("list");
      } else {
        throw new Exception("수업 정보 변경에 실패했습니다.");
      }

    } catch (Exception e) {
      req.setAttribute("error", e);
      req.setAttribute("url", "list");
      req.getRequestDispatcher("/error").forward(req, resp);
    }
  }
}
