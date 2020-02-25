package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.DataLoaderListener;
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

    DataLoaderListener.con.setAutoCommit(false);
    // 사진 게시글을 삭제하기 전에 먼저 자식 테이블에 있는 첨부 파일을 삭제한다.
    try {
      if (photoFileDao.deleteAll(no) > 0) {
        DataLoaderListener.con.commit();
        out.println("삭제했습니다.");
      } else {
        throw new Exception("삭제에 실패했습니다.");
      }
    } catch (Exception e) {
      DataLoaderListener.con.rollback();
      out.println(e.getMessage());
    } finally {
      DataLoaderListener.con.setAutoCommit(true);
    }
  }
}
