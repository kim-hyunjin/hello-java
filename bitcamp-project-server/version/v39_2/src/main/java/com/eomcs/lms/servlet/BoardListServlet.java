package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;

public class BoardListServlet implements Servlet {
  // DAO 클래스를 구체적으로 지정하기 보다는
  // 인터페이스를 지정함으로써
  // 향후 다른 구현체로 교체하기 쉽도록 한다.
  BoardDao boardDao;

  public BoardListServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }


  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    // Application Server 아키텍처에서는
    // 클라이언트가 출력할 내용을 보내주는 것이 핵심이다.
    // 왜?
    // 클라이언트의 역할은 서버가 보낸 데이터를 단순히 출력하는 것이기 때문이다.
    List<Board> boards = boardDao.findAll();
    for (Board board : boards) {
      out.printf("%d, %s, %s, %d\n", board.getNo(), board.getTitle(), board.getDate(),
          board.getViewCount());
    }
  }
}
