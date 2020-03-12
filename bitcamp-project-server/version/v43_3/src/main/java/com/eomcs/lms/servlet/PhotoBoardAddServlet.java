package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.sql.PlatformTransactionManager;
import com.eomcs.sql.TransactionTemplate;
import com.eomcs.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {
  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  LessonDao lessonDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, LessonDao lessonDao,
      PhotoFileDao photoFileDao, PlatformTransactionManager txManager) {

    this.photoBoardDao = photoBoardDao;
    this.lessonDao = lessonDao;
    this.photoFileDao = photoFileDao;
    // 트랜잭션 관리자를 사용하여 트랜잭션을 처리할 도우미 객체를 준비한다.
    this.transactionTemplate = new TransactionTemplate(txManager);
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목?"));

    Lesson lesson = lessonDao.findByNo(Prompt.getInt(in, out, "수업 번호?"));
    if (lesson == null) {
      out.println("수업 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setLesson(lesson);
    // 사용자로부터 사진 게시글에 첨부할 파일을 입력받는다.
    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);

    // 트랜잭션 도우미 객체를 이용하여 트랜잭션 작업을 처리해보자.
    // => 트랜잭션으로 묶어서 처리할 작업은 TransactionCallback 규칙에 따라
    // 객체를 만들어 parameter로 넘겨주면 된다.
    // execute()안에 TransactionCallback 인터페이스의 doInTransaction() 구현
    transactionTemplate.execute(() -> {
      if (photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("사진 게시물 등록에 실패했습니다.");
      }
      photoFileDao.insert(photoBoard);
      out.println("새 사진 게시물을 등록했습니다.");
      return null;
    });
  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {
    // 첨부파일을 입력받는다.
    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 엔터를 치면 파일 추가를 마칩니다.");
    ArrayList<PhotoFile> files = new ArrayList<>();
    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일?");
      if (filepath.length() == 0) {
        if (files.size() > 0) {
          break;
        } else {
          out.println("최소 한 개의 사진 파일을 등록해야합니다.");
          continue;
        }
      }
      files.add(new PhotoFile().setFilePath(filepath));
    }
    return files;
  }
}
