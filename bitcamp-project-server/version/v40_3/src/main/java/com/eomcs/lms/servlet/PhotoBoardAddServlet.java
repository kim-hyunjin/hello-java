package com.eomcs.lms.servlet;

import java.io.PrintStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.ConnectionFactory;
import com.eomcs.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {
  ConnectionFactory conFactory;
  PhotoBoardDao photoBoardDao;
  LessonDao lessonDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao, LessonDao lessonDao,
      PhotoFileDao photoFileDao, ConnectionFactory conFactory) {

    this.photoBoardDao = photoBoardDao;
    this.lessonDao = lessonDao;
    this.photoFileDao = photoFileDao;
    this.conFactory = conFactory;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목"));

    Lesson lesson = lessonDao.findByNo(Prompt.getInt(in, out, "수업 번호?"));
    if (lesson == null) {
      out.println("수업 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setLesson(lesson);

    Connection con = conFactory.getConnection();
    // => ConnectionFactory는 스레드에 보관된 Connection 객체를 찾을 것이다.
    // => 있으면 스레드에 보관된 Connection 객체를 리턴해 줄 것이고,
    // => 없으면 새로 만들어 리턴해 줄 것이다.
    // => 물론 새로 만든 Connection객체는 스레드에도 보관된다.

    con.setAutoCommit(false);

    try {
      if (photoBoardDao.insert(photoBoard) == 0) {
        throw new Exception("사진 게시물 등록에 실패했습니다.");
      }

      List<PhotoFile> photoFiles = inputPhotoFiles(in, out);

      for (PhotoFile photoFile : photoFiles) {
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
      }
      out.println("새 사진 게시물을 등록했습니다.");
      con.commit();

    } catch (Exception e) {
      con.rollback();
      out.println(e.getMessage());
    } finally {
      con.setAutoCommit(true);
    }
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
