package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.TransactionTemplate;
import com.eomcs.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;
  TransactionTemplate transactionTemplate;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.transactionTemplate = new TransactionTemplate(txManager);
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호?");
    transactionTemplate.execute(() -> {
      photoFileDao.deleteAll(no);
      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("삭제에 실패했습니다.");
      }
      out.println("삭제했습니다.");
      return null;
    });
  }
}