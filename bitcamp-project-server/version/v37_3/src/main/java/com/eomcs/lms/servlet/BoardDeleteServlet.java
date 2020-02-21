package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;

public class BoardDeleteServlet implements Servlet {
  // DAO 클래스를 구체적으로 지정하기 보다는
  // 인터페이스를 지정함으로써
  // 향후 다른 구현체로 교체하기 쉽도록 한다.
  BoardDao boardDao;

  public BoardDeleteServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!");
    int no = Integer.parseInt(in.nextLine());

    if (boardDao.delete(no) > 0) { // 삭제했다면,
      out.println("삭제했습니다.");
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
