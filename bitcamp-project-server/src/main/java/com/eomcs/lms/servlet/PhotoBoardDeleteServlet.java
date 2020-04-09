package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.PhotoBoardService;

@WebServlet("/photoboard/delete")
public class PhotoBoardDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {
      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      int no = Integer.parseInt(req.getParameter("no"));
      try {
        photoBoardService.delete(no);
        resp.sendRedirect("list?lessonNo="+Integer.parseInt(req.getParameter("lessonNo")));
      } catch (Exception e) {
        throw new Exception("사진 삭제에 실패했습니다.");
      }

    } catch (Exception e) {
      req.setAttribute("error", e);
      req.setAttribute("url", "list?lessonNo="+Integer.parseInt(req.getParameter("lessonNo")));
      req.getRequestDispatcher("/error").forward(req, resp);
    }
  }
}
