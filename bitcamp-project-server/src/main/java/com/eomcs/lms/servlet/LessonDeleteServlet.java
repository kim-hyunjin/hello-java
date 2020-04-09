package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.LessonService;

@WebServlet("/lesson/delete")
public class LessonDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      LessonService lessonService = iocContainer.getBean(LessonService.class);
      int no = Integer.parseInt(req.getParameter("no"));
      if (lessonService.delete(no) > 0) { // 삭제했다면,
        resp.sendRedirect("list");
      } else {
        throw new Exception("수업 삭제에 실패했습니다.");
      }

    } catch (Exception e) {
      req.setAttribute("error", e);
      req.setAttribute("url", "list");
      req.getRequestDispatcher("/error").forward(req, resp);
    }
  }
}
