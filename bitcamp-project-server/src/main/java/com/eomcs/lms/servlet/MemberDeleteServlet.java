package com.eomcs.lms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.service.MemberService;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    try {


      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);



      int no = Integer.parseInt(req.getParameter("no"));
      if (memberService.delete(no) > 0) { // 삭제했다면,
        resp.sendRedirect("list");

      } else {
        req.getSession().setAttribute("errorMessage", "회원 삭제에 실패했습니다.");
        req.getSession().setAttribute("url", "member/list");
        resp.sendRedirect("../error");
      }


    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
