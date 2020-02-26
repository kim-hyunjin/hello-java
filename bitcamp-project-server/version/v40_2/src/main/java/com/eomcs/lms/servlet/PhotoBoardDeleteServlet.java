package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호?");

    try {
      if (photoFileDao.deleteAll(no) > 0) {
        out.println("삭제했습니다.");
      } else {
        throw new Exception("삭제에 실패했습니다.");
      }
    } catch (Exception e) {
      out.println(e.getMessage());
    }
  }
}
